<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('concepts')" /></h4>
		</div>
		<div class="modal-body">

			<div class="col-md-12 form-group">
				<span class="label label-danger" id="conceptMessages"></span>
			</div>


			<s:form name="concept" theme="simple">
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('name')" /></label>
					<s:textfield name="name" cssClass="form-control"
						placeholder="%{getText('enter.name')}"></s:textfield>
				</div>
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('description')" /></label>

					<s:textarea name="description" cssClass="form-control"
						placeholder="%{getText('enter.desc')}"></s:textarea>
				</div>
				<span class="clearfix"> </span>


				<ul class="nav nav-tabs">
					<li class="active"><a href="#conceptRelations"
						data-toggle="tab"><s:property value="getText('relations')" /></a></li>
					<li><a href="#conceptProperties" data-toggle="tab"><s:property value="getText('properties')" /></a></li>
				</ul>


				<div class="tab-content">
					<div class="tab-pane active" id="conceptRelations">
					<br>
					
						<div class="col-md-6 form-group">
							<div class="btn-group">
								<button type="button" class="btn btn-default" onclick="addRow()"><s:property value="getText('add')" /></button>
							</div>
						</div>
						<div class="col-md-6 form-group">
							<label for="textbox1"><s:property value="getText('filter')" />:</label> <input type="text"
								id="conceptFilter">

						</div>

						<div class="col-md-12 form-group">
							<div id="tablecontent"></div>
						</div>
					</div>
					<div class="tab-pane" id="conceptProperties">
						<br>
						<div class="col-md-6 form-group">
							<div class="btn-group">
								<button type="button" class="btn btn-default" onclick="addPrRow()"><s:property value="getText('add')" /></button>
							</div>
						</div>
						<div class="col-md-12 form-group">
							<div id="conceptPropertiesContent"></div>
						</div>

					</div>
				</div>




				<s:hidden name="action"></s:hidden>
				<s:hidden name="id"></s:hidden>
				<s:hidden name="relationId"></s:hidden>
			</s:form>


		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" id="conceptBtnClose" data-dismiss="modal"><s:property value="getText('close')" /></button>
			<button type="button" class="btn btn-primary" id="conceptBtnSave"
				onclick="saveConcept();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
