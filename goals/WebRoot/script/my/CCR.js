function initializeCCR() {

	$('#myModalCCR')
			.on(
					'shown.bs.modal',
					function(e) {
						
						e.stopPropagation();
						

						editableGridCCR = new EditableGrid("DemoGridJSON");
						editableGridCCR.tableLoaded = function() {
							
					/*		$('#tablecontentCCR').waitUntilExists(function(e){
								editableGridCCR.renderGrid("tablecontentCCR", "testgrid");
								});*/
							
							if(!checkExists('#tablecontentCCR'))
								return;
								
								editableGridCCR.renderGrid("tablecontentCCR", "testgrid");
						};
						var cid = $("#relationId").val();
						editableGridCCR
								.loadJSON('concept.action?action=showJSONRelation&relationId='
										+ cid + '&projectId=' + projectId);

					});

}

function saveConceptCCR() {

	var saveText = $("#ccrBtnSave").text();
	try {
		var superValues = [];
		for ( var i = 0; i < editableGridCCR.getRowCount(); i++) {
			var values = editableGridCCR.getRowValues(i);
			if(values.conceptTo == 0 ||  values.conceptFrom == 0  || values.conceptTo == "" ||  values.conceptFrom == "" )
			{
				alert("ConceptTo and/or ConceptFrom cannot be empty");
				return false;
			}
			
			if(values.relation == 0 ||  values.functions == 0  || values.relation == "" ||  values.functions == "" )
			{
				alert("Relation and/or Functions cannot be empty");
				return false;
			}
			
			superValues.push(editableGridCCR.getRowValues(i));
		}

		var formData = {
			relationId : $("#relationId").val(),
			json : JSON.stringify(superValues),
			action : "addEditRelation",
			projectId : projectId
		};

		$.ajax({
			url : "concept.action",
			type : 'POST',
			data : formData,
			context : document.body,
       		beforeSend: function(){
      			// $("#loading").dialog('open').html("<p>Please Wait...</p>");
      			$("#ccrBtnSave").text('Please wait');
      			$("#ccrBtnSave").prop("disabled",true);
      			$("#ccrBtnClose").prop("disabled",true);
    	}
		}).done(function(data) {
			$("#ccrBtnSave").text(saveText);
  			$("#ccrBtnSave").prop("disabled",false);
  			$("#ccrBtnClose").prop("disabled",false);
			var obj = JSON.parse(data);
			$("#conceptMessages").html(obj.messages);
			
			if(obj.ErrorCode == 1)
			{
				return false;
			}
			$("#relationId").val(obj.relationId);

			var node = cy.filter('edge[id = "ccr_' + obj.relationId + '"]');
			if (node.length != 0) {
				node.remove();
			} 
			cy.add({
				group : "edges",
				data : {
					weight : 75,
					id : 'ccr_'+obj.relationId,
					name : this.name,
					source : 'con_' + obj.source.toString(),
					target : 'con_' + obj.target.toString(),
					type : 4
				}
			});

		}).fail(function(a, b, c) {
			alert(a + "\n" + b + "\n" + c);
		});

	} catch (e) {
		alert(e);
	}
}