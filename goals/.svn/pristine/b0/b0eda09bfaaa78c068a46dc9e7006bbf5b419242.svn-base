<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><s:property value="getText('welcome')" /> <s:property value="%{#session['loginName']}" />
</title>
<style type="text/css">
.highlight {
	background-color: whitesmoke !important;
}
</style>
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

<script src="script/jquery-2.1.0.js"></script>
<script src="script/bootstrap.js"></script>
<script src="script/bootbox.js"></script>
<script src="script/jquery.dataTables.js"></script>
<script src="script/chosen.jquery.js"></script>
<script src="script/cytoscape.js"></script>
<script src="script/cytoscape.js-panzoom.js"></script>
<script src="script/cytoscape.js-navigator.js"></script>
<script src="script/cytoscape.js-cxtmenu.js"></script>
<script src="script/dagre.js"></script>
<script src="script/jquery.validate.js"></script>
<script src="script/bootstrap-datepicker.js"></script>
<script src="script/waitToExist.js"></script>



<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/chosen.css" />

<script src="script/editablegrid-2.0.1.js"></script>
<%-- <script src="script/demo.js"></script> --%>

<link rel="stylesheet" type="text/css" href="css/editablegrid-2.0.1.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<link rel="stylesheet" href="css/cytoscape.js-panzoom.css">
<link rel="stylesheet" href="css/cytoscape.js-navigator.css">



<!-- Latest compiled and minified CSS -->


<!-- Latest compiled and minified JavaScript -->


<style type="text/css">
.chosen-container {
	width: 220px !important;
}

.chosen-choices {
	min-width: 100px;
}

.col-xs-15,.col-sm-15,.col-md-15,.col-lg-15 {
	position: relative;
	min-height: 1px;
	padding-right: 10px;
	padding-left: 10px;
}

.col-xs-15 {
	width: 20%;
	float: left;
}

@media ( min-width : 768px) {
	.col-sm-15 {
		width: 20%;
		float: left;
	}
}

@media ( min-width : 992px) {
	.col-md-15 {
		width: 20%;
		float: left;
	}
}

@media ( min-width : 1200px) {
	.col-lg-15 {
		width: 20%;
		float: left;
	}
}

table.testgrid {
	border-collapse: collapse;
	border: 1px solid #CCB;
	width: 800px;
}

table.testgrid td,table.testgrid th {
	padding: 5px;
	border: 1px solid #E0E0E0;
}

table.testgrid th {
	background: #E5E5E5;
	text-align: left;
}

input.invalid {
	background: red;
	color: #FDFDFD;
}

#cy {
	height: 600px;
	width: 900px;
	position: absolute;
}

#cyGraph {
	height: 600px;
	width: 900px;
	position: absolute;
}

.neighbor{


	background-color : #6FB1FC
}
</style>
<script type="text/javascript">
	var editableGrid;
	var editableGridSE;
	var editableGridPresentation;
	var editableGridLearner;
	var conceptPropertiesGrid;
	var prEditableGrid;
	var prPropertiesGrid;
	var grEditableGrid;
	var grPropertiesGrid;
	var editableGridCCR;
	var editableGridPCR;
	var editableGridGPR;
	var presentationTable;
	var projectId = '<s:property value="%{#session[\'projectId\']}" />';
</script>
<script src="script/my/global.js"></script>
<script src="script/my/concept.js"></script>
<script src="script/my/pr.js"></script>
<script src="script/my/gr.js"></script>
<script src="script/my/CCR.js"></script>
<script src="script/my/PCR.js"></script>
<script src="script/my/GPR.js"></script>
<script src="script/my/presentation.js"></script>
<script src="script/my/learner.js"></script>
<script src="script/my/ScenarioGenerator.js"></script>
<script src="script/my/GeneratedScenario.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('[data-toggle="tabajax"]')
								.click(
										function(e) {

											var $this = $(this), loadurl = $this
													.attr('href'), targ = $this
													.attr('data-target');

											$
													.get(
															loadurl,
															function(data) {
																$(targ).html(
																		data);
																if (targ == "#presentations") {
																	presentationTable = $(
																			'#presentationTable')
																			.DataTable(
																					{
																						"columns" : [
																								{
																									"width" : "5%"
																								},
																								{
																									"width" : "40%"
																								},
																								{
																									"width" : "40%"
																								},
																								{
																									"width" : "15%"
																								} ]
																					});

																	initializePresentation();
																} else if (targ == "#learners") {
																	learnerTable = $(
																			'#learnerTable')
																			.DataTable(
																					{
																						"columns" : [
																								{
																									"width" : "5%"
																								},
																								{
																									"width" : "15%"
																								},
																								{
																									"width" : "15%"
																								},
																								{
																									"width" : "10%"
																								},
																								{
																									"width" : "10%"
																								},
																								{
																									"width" : "10%"
																								},
																								{
																									"width" : "20%"
																								},
																								{
																									"width" : "15%"
																								} ]
																					});

																	initializeLearner();
																} else if (targ == "#se") {
																	// we build and load the metadata in Javascript

																	editableGridSE = new EditableGrid(
																			"ScenarioGRID");
																	editableGridSE.tableLoaded = function() {
																		this
																				.renderGrid(
																						"scenarioTable",
																						"testgrid");
																		editableGridSE
																				.setCellRenderer(
																						"action",
																						new CellRenderer(
																								{
																									render : function(
																											cell,
																											value) {

																										cell.innerHTML = "<a onclick=\" editableGridSE.remove("
																												+ cell.rowIndex
																												+ "); \" style=\"cursor:pointer\">"
																												+ "<i class=\'fa   fa-eraser fa-lg\'></i></a>";
																									}
																								}));
																	};
																	var pID = '<s:property value="%{#session[\'projectId\']}" />';
																	editableGridSE
																			.loadJSON('concept.action?action=showJSONGenerator&projectId='
																					+ pID);

																} else if (targ == "#ke") {
																	$this
																			.tab('show');
																	intializeCyto();
																	addId();
																	$(
																			'#cy .cytoscape-navigator')
																			.width(
																					300);

																}

															});

											$this.tab('show');
											return false;
										});

						$('#ke_tab').click();

						$('body').on('hidden.bs.modal', '.modal', function() {
							$(this).removeData('bs.modal');
						});

						initializeConcept();
						initializePr();
						initializeGr();
						initializeCCR();
						initializePCR();
						initializeGPR();

					});

	var rowIIDSG = 0;

	function doAddConceptSG() {
		try {
			var string = '{"concept":"0","values":"0","action":" "}';

			var jSon = JSON.parse(string);

			editableGridSE.insertAfter(0, rowIIDSG--, jSon);

		} catch (e) {
			alert(e);
		}
	}

	function generateScenario() {
		var superValues = [];
		
		if(editableGridSE.getRowCount() == 0)
		{
			alert("<s:property value="getText('error.msg.min.one.ped.objective.required')" />");
			return false;
		}
		for ( var i = 0; i < editableGridSE.getRowCount(); i++) {
			var values = editableGridSE.getRowValues(i);
			if (values.concept == 0 || values.concept == ""
					|| values.values == 0 || values.values == "") {
				alert("<s:property value="getText('error.msg.concept.value.required')" />");
				return false;
			}
			superValues.push(values);
		}

		var formData = {
			learnerId : $("#learnerList").val(),
			presentationId : $("#presentationList").val(),
			projectId : projectId,
			json : JSON.stringify(superValues),
			action : "generateScenario"
		};

		$.ajax({
			url : "scenario.action",
			type : 'POST',
			data : formData,
			context : document.body
		}).done(function(data) {
		
			var obj = JSON.parse(data);
			$("#textScenario").html(obj.html.replace("\n", "<br />"));
			
			intializeCytoScenario();
			addData(obj.data);

		}).fail(function(a, b, c) {
			alert(a + "\n" + b + "\n" + c);
		});
	}
	
		function deleteLearner(id) {

		bootbox.confirm("<s:property value="getText('delete.confirm.question')" /> ",
				function(result) {
					if (result) {
						$.ajax({
							url : "learner.action?action=deleteWorkspace&id=" + id+"&projectId="+projectId,
							context : document.body
						}).done(function(data) {

							bootbox.alert({
								message : '<s:property value="getText('learner.delete.success')" />',
								title : '<s:property value="getText('learner.delete.message')" />',
								closeButton : true
							});

							var rowId = $('#row_' + id);
							learnerTable.row(rowId).remove().draw();

						}).error(function(a, b, c) {
							alert(a + "\n" + b + "\n" + c);
						});
					}
				});

		return;

	}
</script>
</head>
<body>


	<div class="navbar navbar-default" style="width:100%">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><s:property
						value="%{#session['loginName']}" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#"><s:property value="getText('profile')" /></a></li>
					<li><a href="user.action?action=logout"><s:property value="getText('sign.out')" /></a></li>
				</ul>



				<ul class="nav navbar-nav navbar-right">
					<li><a class="navbar-brand" href="#"><s:property
								value="%{#session['projectName']}" /></a></li>
					<li><a href="project.action?action=exit"><s:property value="getText('exit')" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>




	<ul class="nav nav-pills tabs-up" id="friends">
		<li class="active"><a
			href="project.action?action=showKE&id=<s:property value="%{#session['projectId']}" />"
			data-target="#ke" class="media_node active span" id="ke_tab"
			data-toggle="tabajax" rel="tooltip"> <s:property value="getText('knowledge.editor')" /> </a></li>
		<li><a
			href="presentation.action?action=show&projectId=<s:property value="%{#session['projectId']}" />"
			data-target="#presentations" class="media_node span"
			id="presentations_tab" data-toggle="tabajax" rel="tooltip">
				<s:property value="getText('presentations')" /></a></li>
		<li><a href="learner.action?action=showWorkspace"
			data-target="#learners" class="media_node span" id="learners_tab"
			data-toggle="tabajax" rel="tooltip"><s:property value="getText('learners')" /></a></li>
		<li><a href="adaptationknowledge.action?action=show"
			data-target="#ae" class="media_node span" id="ae_tab"
			data-toggle="tabajax" rel="tooltip"><s:property value="getText('adaptation.knowledge')" /></a></li>
		<li><a
			href="concept.action?action=showGenerator&projectId=<s:property value="%{#session['projectId']}" />"
			data-target="#se" class="media_node span" id="se_tab"
			data-toggle="tabajax" rel="tooltip"><s:property value="getText('scenario.generator')" /></a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="ke"></div>
		<div class="tab-pane" id="presentations"></div>
		<div class="tab-pane  urlbox span8" id="learners"></div>
		<div class="tab-pane" id="ae"></div>
		<div class="tab-pane  urlbox span8" id="se"></div>
	</div>

	<a data-toggle="modal" data-target="#myModal" id="conceptLink"></a>
	<a data-toggle="modal" data-target="#myModalPR" id="prLink"></a>
	<a data-toggle="modal" data-target="#myModalGR" id="grLink"></a>
	<a data-toggle="modal" data-target="#myModalCCR" id="crLink"></a>
	<a data-toggle="modal" data-target="#myModalPCR" id="pcrLink"></a>
	<a data-toggle="modal" data-target="#myModalGPR" id="gprLink"></a>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="myModalPR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="myModalGR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="myModalCCR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="myModalPCR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

	<div class="modal fade" id="myModalGPR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

</body>
</html>