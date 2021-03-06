function initializePr() {

	$('#myModalPR')
			.on(
					'shown.bs.modal',
					function(e) {
						e.stopPropagation();
					
						$("#pedagogicalresource").validate({
							rules : {
								name : "required"
							},
							messages : {
								name : "Name is required"
							}
						});
						prEditableGrid = new EditableGrid("DemoGridJSON1");
						prEditableGrid.tableLoaded = function() {
							//f.stopPropagation();
							//if ($('#tablecontentPR')) 
							if(prEditableGrid.getColumnCount() == 0) return;
							
							if(!checkExists('#tablecontentPR'))
								return;
							
							
								prEditableGrid.renderGrid("tablecontentPR",
										"testgrid");
								
								//alert("in main grid");
								
								

								$("#prFilter").keyup(
										function() {
											prEditableGrid
													.filter($("#prFilter")
															.val());
										});

								prEditableGrid
										.setCellRenderer(
												"action",
												new CellRenderer(
														{
															render : function(
																	cell, value) {

																cell.innerHTML = "<a onclick=\"if (confirm('Are you sure you want to delete this? ')) { prEditableGrid.remove("
																		+ cell.rowIndex
																		+ ");} \" style=\"cursor:pointer\">"
																		+ "<i class=\'fa   fa-eraser fa-lg\'></i></a>";

																prEditableGrid
																		.filter("");

															}
														}));

								prEditableGrid
										.setCellRenderer(
												"select",
												new CellRenderer(
														{
															render : function(
																	cell, value) {

																cell.innerHTML = "<input type=\"checkbox\" name=\"prCheck\" value=\""
																		+ prEditableGrid
																				.getRowId(cell.rowIndex)
																		+ "\">";
															}
														}));
							
						};
						var cid = $("#pedagogicalresource_id").val();
						prEditableGrid
								.loadJSON('pedagogicalresource.action?action=showJSON&id='
										+ cid + '&projectId=' + projectId);

						prPropertiesGrid = new EditableGrid("CPGridJSON1");
						prPropertiesGrid.tableLoaded = function() {
							//f.stopPropagation();
							//if ($('#prPropertiesContent')) 
							if(prPropertiesGrid.getColumnCount() == 0) return;
							if(!checkExists('#prPropertiesContent'))
								return;
							
								prPropertiesGrid.renderGrid(
										"prPropertiesContent", "testgrid");
								
								

								prPropertiesGrid
										.setCellRenderer(
												"select",
												new CellRenderer(
														{
															render : function(
																	cell, value) {
																// this action
																// will
																// remove the
																// row,
																// so first find
																// the
																// ID of the row
																// containing
																// this
																// cell
																cell.innerHTML = "<input type=\"checkbox\" name=\"prPropertySelect\" value=\""
																		+ prPropertiesGrid
																				.getRowId(cell.rowIndex)
																		+ "\">";
															}
														}));

								prPropertiesGrid
										.setCellRenderer(
												"action",
												new CellRenderer(
														{
															render : function(
																	cell, value) {

																// this action
																// will
																// remove the
																// row,
																// so first find
																// the
																// ID of the row
																// containing
																// this
																// cell
																cell.innerHTML = "<a onclick=\"if (confirm('Are you sure you want to delete this element ? ')) { prPropertiesGrid.remove("
																		+ cell.rowIndex
																		+ ");} \" style=\"cursor:pointer\">"
																		+ "<i class=\'fa   fa-eraser fa-lg\'></i></a>";
															}
														}));
							

						};

						prPropertiesGrid
								.loadJSON('pedagogicalresource.action?action=showProperties&id='
										+ cid);

					});

}

var prRowIID = 0;
function addRowPR() {
	try {
		var string = '{"select":"0","concept":"0","values":"0","rk":"0","action":" "}';

		var jSon = JSON.parse(string);

		prEditableGrid.insertAfter(0, prRowIID--, jSon);

	} catch (e) {
		alert(e);
	}
}

var prPrRowIID = 0;
function addPrRowPR() {
	try {
		var string = '{"select":"0","name":"","value":""}';

		var jSon = JSON.parse(string);

		prPropertiesGrid.insertAfter(0, prPrRowIID--, jSon);

	} catch (e) {
		alert(e);
	}
}

function deleteRow(rowIndex) {
	alert(rowIndex);
	if (confirm("Are you sure?")) {
		alert(rowIndex);
	}
}

function savePR() {
	if (!$("#pedagogicalresource").valid())
		return false;
	var saveText = $("#prBtnSave").text();
	var nameData = {
		name : $("#pedagogicalresource_name").val(),
		id : $("#pedagogicalresource_id").val(),
		action : "checkName",
		projectId : projectId
	};

	$
			.ajax({
				url : "pedagogicalresource.action",
				type : 'POST',
				data : nameData,
				context : document.body,
       		beforeSend: function(){
          			// $("#loading").dialog('open').html("<p>Please Wait...</p>");
          			$("#prBtnSave").text('Please wait');
          			$("#prBtnSave").prop("disabled",true);
          			$("#prBtnClose").prop("disabled",true);
        	}
			})
			.done(
					function(data) {
						$("#prBtnSave").text(saveText);
	          			$("#prBtnSave").prop("disabled",false);
	          			$("#prBtnClose").prop("disabled",false);
						var obj = JSON.parse(data);
						if (!obj.messages) {
							alert("Pedagogical resource with the same name already exists");
							return false;
						}

						try {
							var superValues = [];
							for ( var i = 0; i < prEditableGrid.getRowCount(); i++) {
								var values = prEditableGrid.getRowValues(i);
								if (values.concept == 0 || values.concept == "") {
									alert("You have to select a concept for relation");
									return false;
								}
								superValues
										.push(prEditableGrid.getRowValues(i));
							}

							var superPrValues = [];
							for ( var i = 0; i < prPropertiesGrid.getRowCount(); i++) {
								superPrValues.push(prPropertiesGrid
										.getRowValues(i));
							}

							var formData = {
								name : $("#pedagogicalresource_name").val(),
								description : $(
										"#pedagogicalresource_description")
										.val(),
								id : $("#pedagogicalresource_id").val(),
								json : JSON.stringify(superValues),
								action : "addEdit",
								typesId : $("#types").val(),
								prJson : JSON.stringify(superPrValues),
								projectId : projectId
							};

							$
									.ajax({
										url : "pedagogicalresource.action",
										type : 'POST',
										data : formData,
										context : document.body
									})
									.done(
											function(data) {

												var obj = JSON.parse(data);
												$("#prMessages").html(
														obj.messages);
												$("#pedagogicalresource_id")
														.val(obj.id);

												var node = cy
														.filter('node[id = "pr_'
																+ obj.id + '"]');
												if (node.length == 0) {

													cy
															.add(createNode(
																	2,
																	obj.id,
																	$(
																			"#pedagogicalresource_name")
																			.val(),
																	0, 0));
												} else {
													node[0]
															.data(
																	'name',
																	$(
																			"#pedagogicalresource_name")
																			.val());
												}

											}).fail(function(a, b, c) {
										alert(a + "\n" + b + "\n" + c);
									});

						} catch (e) {
							alert(e);
						}
					});

}