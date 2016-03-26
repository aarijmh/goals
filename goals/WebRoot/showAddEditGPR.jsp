<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h4 class="modal-title" id="myModalLabel"><s:property value="getText('pedagogical.resource')" /> - <s:property value="getText('game.resource.relation')" /></h4>
		</div>
		<div class="modal-body">

			<div class="col-md-12 form-group">
				<span class="label label-danger" id="gprMessages"></span>
			</div>


						<div class="col-md-12 form-group">
							<div id="tablecontentGPR"></div>
						</div>
				<s:hidden name="action"></s:hidden>
				<s:hidden name="relationId" id="gr_relationId"></s:hidden>


		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="gprBtnClose"><s:property value="getText('close')" /></button>
			<button type="button" class="btn btn-primary" id="gprBtnSave"
				onclick="saveConceptGPR();"><s:property value="getText('save.changes')" /></button>
		</div>
	</div>
</div>
