<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<br>
<br>

<div class="col-md-9">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h5 class="panel-title"><s:property value="getText('knowledge.editor')" /></h5>
		</div>
		<div class="panel-body">
		
					<div id="cy"></div>

			<div class="cytoscape-navigator" style="width: 100px; height: 100px; ">
				<canvas></canvas>
				<div class="cytoscape-navigatorView"></div>
				<div class="cytoscape-navigatorOverlay"></div>
			</div>
		</div>
	</div>
</div>
<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h5 class="panel-title"><s:property value="getText('add.edit')" /> <s:property value="getText('elements')" /></h5>
		</div>
		<div class="panel-body">
			<a
				href="concept.action?action=showAddEdit&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModal"><s:property value="getText('add.edit.concepts')" /></a> <br>
			<a
				href="pedagogicalresource.action?action=showAddEdit&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModalPR"><s:property value="getText('add.edit.pr')" /></a> <br> <a
				href="gameresource.action?action=showAddEdit&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModalGR"><s:property value="getText('add.edit.gr')" /></a> <br> <a
				href="concept.action?action=showAddEditRelation&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModalCCR"><s:property value="getText('add.edit.ccr')" /></a> <br> <a
				href="pedagogicalresource.action?action=showAddEditRelation&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModalPCR"><s:property value="getText('add.edit.pcr')" /></a> <br> <a
				href="gameresource.action?action=showAddEditRelation&projectId=<s:property
								value="%{#session['projectId']}" />"
				data-toggle="modal" data-target="#myModalGPR"><s:property value="getText('add.edit.pgr')" /></a>
		</div>
	</div>
</div>

