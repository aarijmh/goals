<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('pedagogical.resources')" /></h4>
		</div>
		<div class="modal-body">

			<div class="col-md-12 form-group">
				<span class="label label-danger" id="prMessages"></span>
			</div>


			<s:form name="pedagogicalresource" theme="simple">
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('name')" /></label>
					<s:textfield name="name" cssClass="form-control"
						placeholder="%{getText('enter.name')}"></s:textfield>
				<label for="textbox1"><s:property value="getText('types')" /></label>
				<s:select list="typesList" listKey="id" listValue="name" value="typesId" key="typesId" id="types" cssClass="form-control"></s:select>						
				</div>
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('description')" /></label>

					<s:textarea name="description" cssClass="form-control"
						placeholder="%{getText('enter.desc')}"></s:textarea>
				</div>
				<span class="clearfix"> </span>


				<ul class="nav nav-tabs">
					<li class="active"><a href="#prRelations"
						data-toggle="tab"><s:property value="getText('relations')" /></a></li>
					<li><a href="#prProperties" data-toggle="tab"><s:property value="getText('properties')" /></a></li>
				</ul>


				<div class="tab-content">
					<div class="tab-pane active" id="prRelations">
					<br>
					
						<div class="col-md-6 form-group">
							<div class="btn-group">
								<button type="button" class="btn btn-default" onclick="addRowPR()"><s:property value="getText('add')" /></button>
							</div>
						</div>
						<div class="col-md-6 form-group">
							<label for="textbox1"><s:property value="getText('filter')" />:</label> <input type="text"
								id="prFilter">

						</div>

						<div class="col-md-12 form-group">
							<div id="tablecontentPR"></div>
						</div>
					</div>
					<div class="tab-pane" id="prProperties">
						<br>
						<div class="col-md-6 form-group">
							<div class="btn-group">
								<button type="button" class="btn btn-default" onclick="addPrRowPR()"><s:property value="getText('add')" /></button>
							</div>
						</div>
						<div class="col-md-12 form-group">
							<div id="prPropertiesContent"></div>
						</div>

					</div>
				</div>




				<s:hidden name="action"></s:hidden>
				<s:hidden name="id"></s:hidden>
			</s:form>


		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="prBtnClose"><s:property value="getText('close')" /></button>
			<button type="button" class="btn btn-primary" id="prBtnSave"
				onclick="savePR();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
