function initializeGPR() {

	$('#myModalGPR')
			.on(
					'loaded.bs.modal',
					function(e) {
						e.stopPropagation();
						

						editableGridGPR = new EditableGrid("DemoGridJSON");
						editableGridGPR.tableLoaded = function() {

							try{
			/*				$('#tablecontentGPR').waitUntilExists(function(e){
								editableGridGPR.renderGrid("tablecontentGPR", "testgrid");
								});*/
							
								if(!checkExists('#tablecontentGPR'))
									return;
							
								editableGridGPR.renderGrid("tablecontentGPR", "testgrid");
								
							}
							catch(e)
							{
								alert(e);
							}
							
							/*if($('#tablecontentGPR'))
								this.renderGrid("tablecontentGPR", "testgrid");
				*/
								
			/*				$( "#tablecontentGPR" ).load(function() {
									this.renderGrid("tablecontentGPR", "testgrid");
								});*/
							
						};
						var cid = parseInt($("#gr_relationId").val());
						editableGridGPR.loadJSON('gameresource.action?action=showJSONRelation&relationId='
										+ cid + '&projectId=' + projectId);

					});

}

function saveConceptGPR() {

	var saveText = $("#gprBtnSave").text();
	try {
		var superValues = [];
		for ( var i = 0; i < editableGridGPR.getRowCount(); i++) {
			var values = editableGridGPR.getRowValues(i);
			if(values.pr == 0 ||  values.gr == 0  || values.gr == "" ||  values.pr == "" )
			{
				alert("Game resource and/or Pedagogical resource cannot be empty");
				return false;
			}
			superValues.push(editableGridGPR.getRowValues(i));
		}

		var formData = {
			relationId : $("#gr_relationId").val(),
			json : JSON.stringify(superValues),
			action : "addEditRelation",
			projectId : projectId
		};

		$.ajax({
			url : "gameresource.action",
			type : 'POST',
			data : formData,
			context : document.body,
       		beforeSend: function(){
      			// $("#loading").dialog('open').html("<p>Please Wait...</p>");
      			$("#gprBtnSave").text('Please wait');
      			$("#gprBtnSave").prop("disabled",true);
      			$("#gprBtnClose").prop("disabled",true);
    	}
		}).done(function(data) {
			$("#gprBtnSave").text(saveText);
  			$("#gprBtnSave").prop("disabled",false);
  			$("#gprBtnClose").prop("disabled",false);
			var obj = JSON.parse(data);
			$("#gprMessages").html(obj.messages);
			$("#gr_relationId").val(obj.relationId);
			
			var node = cy.filter('edge[id = "gpr_' + obj.relationId + '"]');
			if (node.length != 0) {
				node.remove();
			} 
			cy.add({
				group : "edges",
				data : {
					weight : 75,
					id : 'gpr_'+this.id,
					name : this.name,
					source : 'pr_' + obj.source.toString(),
					target : 'gr_' + obj.target.toString(),
					type : 6
				}
			});
			
		}).fail(function(a, b, c) {
			alert(a + "\n" + b + "\n" + c);
		});

	} catch (e) {
		alert(e);
	}
}