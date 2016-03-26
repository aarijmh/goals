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
<title>Welcome <s:property value="%{#session['loginName']}" />
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
<script src="script/jquery.dataTables.js"></script>
<script src="script/bootbox.js"></script>
<script src="script/chosen.jquery.js"></script>
<script src="script/jquery.validate.js"></script>
<script src="script/bootstrap-datepicker.js"></script>

<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css" />
<link rel="stylesheet" type="text/css" href="css/chosen.css" />
<link rel="stylesheet" type="text/css" href="css/datepicker3.css" />

<!-- Latest compiled and minified CSS -->


<!-- Latest compiled and minified JavaScript -->


<style type="text/css">
.chosen-container {
	width: 220px !important;
}

.chosen-choices {
	min-width: 100px;
}
</style>


<script type="text/javascript">
	var table;
	$(document).ready(function() {

		$('body').on('hidden.bs.modal', '.modal', function() {
			$(this).removeData('bs.modal');
		});

		$('#myModal').on('shown.bs.modal', function(e) {
			e.stopPropagation();
			$('#learner_datebirth').datepicker({
				format : "dd/mm/yyyy",
				startDate : "01/01/1990"
			});
			$("#projectCombo").chosen();

			$("#user").validate({
				rules : {
					name : "required",
					login : "required",
					email : {
						required : true,
						email : true
					}
				},
				messages : {
					name : "<s:property value="getText('error.msg.first.name.required')" />",
					datebirth : "<s:property value="getText('Please enter your date of birth')" />",
					email : "<s:property value="getText('Please enter your email')" />"
				}
			});

		});

		table = $('#learnerTable').DataTable({
			"columns" : [ {
				"width" : "5%"
			}, {
				"width" : "15%"
			}, {
				"width" : "15%"
			}, {
				"width" : "10%"
			}, {
				"width" : "10%"
			}, {
				"width" : "10%"
			}, {
				"width" : "15%"
			} ]
		});

	});

	function saveLearner() {

		if (!$("#user").valid())
			return false;
		document.user.action.value = "addEdit";
		//alert( $('#projectCombo').val().join(","));

		var allVals = '';
		$('#projectCombo :selected').each(function() {
			allVals += "projectIds=" + $(this).val() + "&"; //prepare the string
		});
		if (allVals.length > 0) {
			allVals = allVals.substring(0, allVals.length - 1); //remove last '&'
		}

		//alert(allVals);

		var posting = $.post("user.action?" + allVals, {
			action : 'addEdit',
			name : $('#user_name').val(),
			organization : $('#user_organization').val(),
			email : $('#user_email').val(),
			login : $('#user_login').val(),
			password : $('#user_password').val(),
			resetPassword : $('#user_resetPassword').val(),
			sendByEmail : $('#user_sendByEmail').val(),
			id : $('#user_id').val()
		});

		/* Put the results in a div */
		posting
				.done(function(data) {
					var obj = $.parseJSON(data);

					var a = '<a href="user.action?action=showAddEdit&id='
							+ obj.id
							+ '" data-toggle="modal" data-target="#myModal"><i class="fa  fa-edit"></i></a> <a href="javascript:deleteProject('
							+ obj.id + ')"><i class="fa  fa-trash-o"></i></a>';

					if (obj.newObject == "true") {

						var addedRow = table.row
								.add([
										'<input name="userIds" type="checkbox" value="'+obj.id+'"/>',
										$('#user_name').val(),
										$('#user_email').val(),
										$('#user_login').val(),
										$('#user_organization').val(),
										'Teacher', a ]);
						$(addedRow.node()).attr('id', 'row_' + obj.id);

						table.draw();

					} else {
						var rowId = $('#row_' + $('#user_id').val());

						try {
							var row = table.row(rowId);

							var aPos = row.data();
							aPos[1] = $('#user_name').val();
							aPos[2] = $('#user_email').val();
							aPos[3] = $('#user_login').val();
							aPos[4] = $('#user_organization').val();
							aPos[5] = 'Teacher';

							row.data(aPos);
						} catch (e) {
							alert(e);
						}

					}
					$("#errors").html("<s:property value="getText('user.saved.success')" />");
					//$('#myModal').modal('hide');
				});
	}

	function deleteProject(id) {

		bootbox.confirm("<s:property value="getText('delete.confirm.question')" />",
				function(result) {
					if (result) {
						$.ajax({
							url : "user.action?action=delete&id=" + id,
							context : document.body
						}).done(function(data) {

							bootbox.alert({
								message : '<s:property value="getText('user.delete.success')" />',
								title : '<s:property value="getText('user.delete.message')" />',
								closeButton : true
							});

							var rowId = $('#row_' + id);
							table.row(rowId).remove().draw();

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
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><s:property
						value="%{#session['loginName']}" /></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#"><s:property value="getText('profile')" /></a></li>
					<li><a href="user.action?action=logout"><s:property value="getText('sign.out')" /></a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<ul class="nav nav-pills">
		<li><a href="project.action?action=loggedAdmin" style="height: 5px; "><s:property value="getText('projects')" /></a></li>

		<li class="active"><a href="#"><s:property value="getText('users')" /></a></li>
	</ul>


	<div class="row">
		<div class="col-sm-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><s:property value="getText('users')" /></h3>
				</div>
				<div class="panel-body">
					<div style="width: 80%; float:right;">
						<a href="user.action?action=showAddEdit&id=0"
							data-toggle="modal" data-target="#myModal"><i
							class="fa  fa-file fa-lg"></i></a> <a href="removeElements()"><i
							class="fa   fa-eraser fa-lg"></i></a>
					</div>
					<div style="width: 100%; margin: 20px;padding: 20px;">

						<table id="learnerTable">
							<thead>
								<tr>
									<th><s:property value="getText('select')" /></th>
									<th><s:property value="getText('name')" /></th>
									<th><s:property value="getText('email')" /></th>
									<th><s:property value="getText('login')" /></th>
									<th><s:property value="getText('organization')" /></th>
									<th><s:property value="getText('type')" /></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="userList">
									<tr id="row_<s:property value='id' />">
										<td><input type="checkbox"
											value="<s:property value='id' />" /></td>
										<td><s:property value="name" /></td>
										<td><s:property value="email" /></td>
										<td><s:property value="login" /></td>
										<td><s:property value="organization" /></td>
										<td> <s:if test="%{type==2}"><s:property value="getText('teacher')" /></s:if><s:else><s:property value="getText('learner')" /></s:else></td>
										<td><a
											href="user.action?action=showAddEdit&id=<s:property value='id' />"
											data-toggle="modal" data-target="#myModal"><i
												class="fa  fa-edit"></i></a> <a
											href="javascript:deleteProject(<s:property value='id' />)"><i
												class="fa  fa-trash-o"></i></a></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col-sm-4 -->

	</div>



	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>

</body>
<script type="text/javascript">
	
</script>
</html>