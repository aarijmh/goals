function initializeGr() {

	$('#myModalGR')
			.on(
					'loaded.bs.modal',
					function(e) {
						e.stopPropagation();
						
						$("#gameresource").validate({
							rules : {
								name : "required"
							},
							messages : {
								name : "Name is required"
							}
						});

						grEditableGrid = new EditableGrid("DemoGridJSON");
						grEditableGrid.tableLoaded = function() {
							if(grEditableGrid.getColumnCount() == 0) return;
							if(!checkExists('#tablecontentGR'))
								return;
							
								grEditableGrid.renderGrid("tablecontentGR", "testgrid");

							$("#grFilter").keyup(function() {
								grEditableGrid.filter($("#grFilter").val());
							});
							

							grEditableGrid
									.setCellRenderer(
											"action",
											new CellRenderer(
													{
														render : function(cell,
																value) {

															cell.innerHTML = "<a onclick=\"if (confirm('Are you sure you want to delete this? ')) { grEditableGrid.remove("
																	+ cell.rowIndex
																	+ ");} \" style=\"cursor:pointer\">"
																	+ "<i class=\'fa   fa-eraser fa-lg\'></i></a>";

															grEditableGrid
																	.filter("");

														}
													}));

							grEditableGrid
									.setCellRenderer(
											"select",
											new CellRenderer(
													{
														render : function(cell,
																value) {

															cell.innerHTML = "<input type=\"checkbox\" name=\"grCheck\" value=\""
																	+ grEditableGrid
																			.getRowId(cell.rowIndex)
																	+ "\">";
														}
													}));
							
						
						};
						var cid = parseInt($("#gameresource_id").val());
						grEditableGrid
								.loadJSON('gameresource.action?action=showJSON&id='
										+ cid + '&projectId=' + projectId);

						grPropertiesGrid = new EditableGrid("CPGridJSON");
						grPropertiesGrid.tableLoaded = function() {
							
							if(grPropertiesGrid.getColumnCount() == 0) return;
							if(!checkExists('#grPropertiesContent'))
								return;
							
					grPropertiesGrid.renderGrid("grPropertiesContent", "testgrid");

							grPropertiesGrid
									.setCellRenderer(
											"select",
											new CellRenderer(
													{
														render : function(cell,
																value) {
															// this action will
															// remove the row,
															// so first find the
															// ID of the row
															// containing this
															// cell
															cell.innerHTML = "<input type=\"checkbox\" name=\"grPropertySelect\" value=\""
																	+ grPropertiesGrid
																			.getRowId(cell.rowIndex)
																	+ "\">";
														}
													}));

							grPropertiesGrid
									.setCellRenderer(
											"action",
											new CellRenderer(
													{
														render : function(cell,
																value) {

															// this action will
															// remove the row,
															// so first find the
															// ID of the row
															// containing this
															// cell
															cell.innerHTML = "<a onclick=\"if (confirm('Are you sure you want to delete this element ? ')) { grPropertiesGrid.remove("
																	+ cell.rowIndex
																	+ ");} \" style=\"cursor:pointer\">"
																	+ "<i class=\'fa   fa-eraser fa-lg\'></i></a>";
														}
													}));
							
						

						};

						grPropertiesGrid
								.loadJSON('pedagogicalresource.action?action=showProperties&id='
										+ cid);

					});

}

var grRowIID = 0;
function addRowGR() {
	try {
		var string = '{"select":"0","pr":"0","values":"0","action":" "}';

		var jSon = JSON.parse(string);

		grEditableGrid.insertAfter(0, grRowIID--, jSon);

	} catch (e) {
		alert(e);
	}
}

var prGrRowIID = 0;
function addPrRowGR() {
	try {
		var string = '{"select":"0","name":"","value":""}';

		var jSon = JSON.parse(string);

		grPropertiesGrid.insertAfter(0, prGrRowIID--, jSon);

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

function saveGR() {
	if (!$("#gameresource").valid())
		return false;
	var saveText = $("#grBtnSave").text();
	var nameData = {
		name : $("#gameresource_name").val(),
		id : $("#gameresource_id").val(),
		action : "checkName",
		projectId : projectId
	};

	$.ajax({
				url : "gameresource.action",
				type : 'POST',
				data : nameData,
				context : document.body,
       		beforeSend: function(){
          			// $("#loading").dialog('open').html("<p>Please Wait...</p>");
          			$("#grBtnSave").text('Please wait');
          			$("#grBtnSave").prop("disabled",true);
          			$("#grBtnClose").prop("disabled",true);
        	}
			})
			.done(
					function(data) {
						$("#grBtnSave").text(saveText);
	          			$("#grBtnSave").prop("disabled",false);
	          			$("#grBtnClose").prop("disabled",false);
						var obj = JSON.parse(data);
						if (!obj.messages) {
							alert("Game resource with the same name already exists");
							return false;
						}

						try {
							var superValues = [];
							for ( var i = 0; i < grEditableGrid.getRowCount(); i++) {
								var values = grEditableGrid.getRowValues(i);
								if (values.pr == 0 || values.pr == "") {
									alert("You have to select a pedagogical resource for relation");
									return false;
								}
								superValues
										.push(grEditableGrid.getRowValues(i));
							}

							var superPrValues = [];
							for ( var i = 0; i < grPropertiesGrid.getRowCount(); i++) {
								superPrValues.push(grPropertiesGrid
										.getRowValues(i));
							}

							var formData = {
								name : $("#gameresource_name").val(),
								value : $("#gameresource_value").val(),
								id : $("#gameresource_id").val(),
								json : JSON.stringify(superValues),
								action : "addEdit",
								typesId : $("#types").val(),
								prJson : JSON.stringify(superPrValues),
								projectId : projectId
							};

							$
									.ajax({
										url : "gameresource.action",
										type : 'POST',
										data : formData,
										context : document.body
									})
									.done(
											function(data) {

												var obj = JSON.parse(data);
												$("#grMessages").html(
														obj.messages);
												$("#gameresource_id").val(
														obj.id);

												var node = cy
														.filter('node[id = "gr_'
																+ obj.id + '"]');
												if (node.length == 0) {

													cy
															.add(createNode(
																	3,
																	obj.id,
																	$(
																			"#gameresource_name")
																			.val(),
																	0, 0));
												} else {
													node[0]
															.data(
																	'name',
																	$(
																			"#gameresource_name")
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