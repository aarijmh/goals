<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div id="loading" title="my ajax loading dialog"> 
    <p>Please wait ...</p>
</div>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('project')" /> - <s:property value="getText('add.edit')" /></h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12 form-group">
				<span class="label label-danger" id="projectMessages"></span>
			</div>
			<s:form name="project" theme="simple">
				<div class="form-group">
					<label for="name"><s:property value="getText('name')" /></label> 
						<s:textfield name="name" cssClass="form-control"  placeholder="%{getText('enter.name')}" ></s:textfield>
				</div>
				<div class="form-group">
					<label for="name"><s:property value="getText('author')" /></label> <s:textfield name="ownerName" cssClass="form-control" readonly="true"></s:textfield>
				</div>
				<div class="form-group">
					<label for="name"><s:property value="getText('number.of.learners')" /></label> <s:textfield name="numberOfLearners" cssClass="form-control" readonly="true"></s:textfield>
				</div>
				<div class="form-group">
					<label for="description"><s:property value="getText('description')" /></label> <s:textarea name="description" cssClass="form-control"></s:textarea>
				</div>
				<s:hidden name="action"></s:hidden>
				<s:hidden name="id"></s:hidden>
			</s:form>
		</div>
		<div class="modal-footer">
			<button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal"><s:property value="getText('close')" /></button>
			<button type="button" id="savBtn" class="btn btn-primary" onclick="saveProject();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
