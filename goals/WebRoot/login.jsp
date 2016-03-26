<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<title>GOALS - Generator Of Adaptive Learning Scenarios</title>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/welcome.css" />
<link rel="stylesheet" type="text/css" href="css/screen.css" />
<script src="script/jquery-2.1.0.js"></script>
<script src="script/jquery.validate.js"></script>
<script src="script/additional-methods.js"></script>
<script type="text/javascript">
$().ready(function() {

$("#user").validate({
		rules: {
			login: {
				required: true,
				minlength: 6
			},
			password: {
				required: true,
				minlength: 5
			}
		},
		messages: {
			login: {
				required: '<s:property value="getText('error.msg.name.required')" />',
				minlength: '<s:property value="getText('error.msg.name.min.length')" />'
			},
			password: {
				required: '<s:property value="getText('error.msg.password.required')" />',
				minlength: '<s:property value="getText('error.msg.password.min.length')" />'
			}
		}
	});


});

</script>
</head>
<body>
	<header>
		<!-- header -->
		<a href="/"><img src="images/logo.png"></a>
	</header>
	<div>

 <s:url id="indexEN" namespace="/" action="locale" >
      <s:param name="request_locale" >en</s:param>
   </s:url>

   <s:url id="indexFR" namespace="/" action="locale" >
      <s:param name="request_locale" >fr</s:param>
   </s:url>

   <s:a href="%{indexEN}" >English</s:a>
   <s:a href="%{indexFR}" >Français</s:a>
   
		<section class="main">
			<s:form action="user" theme="simple" cssClass="form-1">

				<p class="field">
				
					<s:textfield name="login" placeholder="%{getText('user.name')}"  >
						
					</s:textfield>
					<i class="fa fa-user fa-lg"></i>
				</p>
				<p class="field">
				
					<input type="password" name="password" placeholder="<s:property value="getText('user.password')" />">
					<i class="fa fa-lock fa-lg"></i>
				</p>
				<p class="submit">

					<button type="submit" onclick="" >
						<i class="fa fa-arrow-right fa-lg"></i>
					</button>
				</p>
				
				<s:hidden name="action" value="login"></s:hidden>

			</s:form>
		</section>

	</div>
</body>

<script type="text/javascript">

	function submit()
	{
	alert(user.action);
		user.action = 'login';
	 	user.submit();
	}
</script>
</html>