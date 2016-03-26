function initializePCR() {
	$('#myModalPCR')
			.on(
					'shown.bs.modal',
					function(e) {
						e.stopPropagation();

						editableGridPCR = new EditableGrid("DemoGridJSON");
						editableGridPCR.tableLoaded = function() {
						
							
					/*		$('#tablecontentPCR').waitUntilExists(function(e){
								editableGridPCR.renderGrid("tablecontentPCR", "testgrid");
								});
							*/
							if(!checkExists('#tablecontentPCR'))
								return;
							
								editableGridPCR.renderGrid("tablecontentPCR", "testgrid");
						};
						var cid = $("#pr_relationId").val();
						editableGridPCR.loadJSON('pedagogicalresource.action?action=showJSONRelation&relationId='
										+ cid + '&projectId=' + projectId);

					});

}

function saveConceptPCR() {

	var saveText = $("#pcrBtnSave").text();
	try {
		var superValues = [];
		for ( var i = 0; i < editableGridPCR.getRowCount(); i++) {
			var values = editableGridPCR.getRowValues(i);
			if(values.pr == 0 ||  values.concept == 0  || values.concept == "" ||  values.pr == "" )
			{
				alert("Concept and/or Pedagogical resource cannot be empty");
				return false;
			}
			superValues.push(editableGridPCR.getRowValues(i));
		}

		var formData = {
			relationId : $("#pr_relationId").val(),
			json : JSON.stringify(superValues),
			action : "addEditRelation",
			projectId : projectId
		};

		$.ajax({
			url : "pedagogicalresource.action",
			type : 'POST',
			data : formData,
			context : document.body,
       		beforeSend: function(){
      			// $("#loading").dialog('open').html("<p>Please Wait...</p>");
      			$("#pcrBtnSave").text('Please wait');
      			$("#pcrBtnSave").prop("disabled",true);
      			$("#pcrBtnClose").prop("disabled",true);
    	}
		}).done(function(data) {
			$("#pcrBtnSave").text(saveText);
  			$("#pcrBtnSave").prop("disabled",false);
  			$("#pcrBtnClose").prop("disabled",false);
			var obj = JSON.parse(data);
			$("#pcrMessages").html(obj.messages);						
			
			if(obj.ErrorCode == 1)
			{
				return false;
			}
			
			
			$("#pr_relationId").val(obj.relationId);
			
			var node = cy.filter('edge[id = "pcr_' + obj.relationId + '"]');
			if (node.length != 0) {
				node.remove();
			} 
			cy.add({
				group : "edges",
				data : {
					weight : 75,
					id : 'pcr_'+this.id,
					name : this.name,
					source : 'con_' + obj.source.toString(),
					target : 'pr_' + obj.target.toString(),
					type : 5
				}
			});
			
		}).fail(function(a, b, c) {
			alert(a + "\n" + b + "\n" + c);
		});

	} catch (e) {
		alert(e);
	}
}