<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../../css/main.css"
	media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Sign on</h1>
	<div id='signon-container'>
		<form action="j_spring_security_check" method="post">
			User name: <input type="text" name="j_username" autofocus="autofocus"><br>
			Password: <input type="text" name="j_password"><br> 
			<input	type="submit" value="Submit"> <br>
		</form>
	</div>

</body>
</html>