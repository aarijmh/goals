<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('learner.add.edit')" /></h4>
		</div>
		<div class="col-md-12 form-group">
					<label for="textbox1"><s:property value="getText('fields.obligatory')" /></label>
					
				</div>
		<div class="modal-body">
			<div class="col-md-12 form-group">
				<span class="label label-danger" id="learnerMessages"></span>
			</div>
			<s:form name="learner" theme="simple">
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('name')" /> *</label>
					<s:textfield name="name" cssClass="form-control"
						placeholder="%{getText('enter.name')}"></s:textfield>
				</div>
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('d.o.b')" /></label>
					
					<input type="text" name="datebirth" value="<s:property value='datebirth' />" class="form-control" id="learner_datebirth"/>

				</div>
				<span class="clearfix"> </span>

				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('email')" /> *</label>
					<s:textfield name="email" cssClass="form-control"
						placeholder="%{getText('enter.email')}"></s:textfield>
				</div>

				<div class="col-md-6 form-group">
					<label for="textbox1"> <s:property value="getText('address')" /></label>
					<s:textfield name="adresse" cssClass="form-control"
						placeholder="%{getText('enter.address')}"></s:textfield>
				</div>
				<span class="clearfix"> </span>
				<div class="col-md-6 form-group">
					<label for="textbox1"> <s:property value="getText('organization')" /> *</label>
					<s:textfield name="organization" cssClass="form-control"
						placeholder="%{getText('enter.organization')}"></s:textfield>
				</div>
				<div class="col-md-6 form-group">

					<label for="textbox1"><s:property value="getText('description')" /></label>
					<s:textarea name="description" cssClass="form-control"
						placeholder="%{getText('enter.desc')}"></s:textarea>
				</div>
				<span class="clearfix"> </span>
				<div class="col-md-6 form-group">

					<label for="textbox1"><s:property value="getText('associated.projects')" /></label>
					<s:select id="projectCombo" name="projectIds" listKey="id" listValue="name" list="projectList" multiple="true" cssClass="multiselect" value="projectIds"></s:select>
				</div>
				<s:hidden name="action"></s:hidden>
				<s:hidden name="id"></s:hidden>
			</s:form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="cancelBtn"><s:property value="getText('close')" /></button>
			<button type="button" class="btn btn-primary" id="savBtn"
				onclick="saveLearner();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
