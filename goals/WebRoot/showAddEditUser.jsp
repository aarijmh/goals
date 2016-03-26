<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('user')" /> <s:property value="getText('add.edit')" /></h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12 form-group">
				<span class="label label-danger" id="errors"></span>
			</div>
			<s:form name="user" theme="simple">
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('name')" /></label>
					<s:textfield name="name" cssClass="form-control"
						placeholder="%{getText('enter.name')}"></s:textfield>
				</div>
				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('email')" /></label>
					<s:textfield name="email" cssClass="form-control"
						placeholder="%{getText('enter.email')}"></s:textfield>
				</div>
				<span class="clearfix"> </span>

				<div class="col-md-6 form-group">
					<label for="textbox1"><s:property value="getText('login')" /></label>
					<s:textfield name="login" cssClass="form-control"
						placeholder="%{getText('enter.email')}"></s:textfield>
				</div>

				<div class="col-md-6 form-group">
					<label for="textbox1"> <s:property value="getText('password')" /></label>
					<s:password name="password" cssClass="form-control"
						placeholder="%{getText('enter.password')}"></s:password>
					<s:property value="getText('reset.password')" /> : <s:checkbox name="resetPassword" ></s:checkbox><br>
					<s:property value="getText('send.password.email')" />: <s:checkbox name="sendByEmail"></s:checkbox>
				</div>
				<span class="clearfix"> </span>
				<div class="col-md-6 form-group">
					<label for="textbox1"> <s:property value="getText('organization')" /></label>
					<s:textfield name="organization" cssClass="form-control"
						placeholder="%{getText('enter.organization')}"></s:textfield>
				</div>
				<div class="col-md-6 form-group">

					<label for="textbox1"><s:property value="getText('type')" /></label>
					<select id="user_type" name="type" class="form-control">
						<option value="2" ><s:property value="getText('teacher')" /></option>
					</select>
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
			<button type="button" class="btn btn-default" data-dismiss="modal"><s:property value="getText('close')" /></button>
			<button type="button" class="btn btn-primary"
				onclick="saveLearner();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
