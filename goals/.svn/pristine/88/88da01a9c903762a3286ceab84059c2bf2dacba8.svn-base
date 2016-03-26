<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

	<br>
	<br>

	<div class="row" style="width:90%">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title"><s:property value="getText('learners')" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-4 control-label" for="selectbasic"><s:property value="getText('learner')" /></label>
						<div class="col-md-8">
							<s:select list="learnerList" listKey="id" listValue="name"
								cssClass="form-control" id="learnerList"></s:select>
						</div>
					</div>
					<br> <br>
					<div class="form-group" style="margin-top: 20px;">
						<label class="col-md-4 control-label" for="selectbasic"><s:property value="getText('presentations')" /></label>
						<div class="col-md-8">
							<s:select list="presentationList" listKey="id" listValue="name"
								cssClass="form-control" id="presentationList"></s:select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title"><s:property value="getText('ped.objectives')" /></h5>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-3">
							<button type="button" class="btn btn-default"
								onclick="doAddConceptSG()"><s:property value="getText('add')" /></button>
						</div>
						<div class="col-md-9">
							<div id="scenarioTable"></div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<div class="col-md-3">


			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title"><s:property value="getText('scenario.generator')" /></h5>
				</div>
				<div class="panel-body">
					<button type="button" class="btn btn-default" onclick="generateScenario()"><s:property value="getText('generate')" /></button>
					<button type="button" class="btn btn-default"><s:property value="getText('generate.game')" /></button>
				</div>
				<br> <br>

			</div>
		</div>

	</div>


	<div class="row" style="width:90%">
		<div class="col-md-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title"><s:property value="getText('textual.representation')" /></h5>
				</div>
				<div class="panel-body" id="textScenario">
				
				</div>
			</div>
		</div>
		<div class="col-md-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h5 class="panel-title"><s:property value="getText('graphical.representation')" /></h5>
				</div>
				<div class="panel-body" id="graphicalScenario">
					<div id="cyGraph"></div>
				</div>
			</div>
		</div>
	</div>
