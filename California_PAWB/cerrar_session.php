<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Cerrar sesion</title>
</head>
<body>
	<?php 
		session_start();

		session_destroy();
	 ?>
	 <?php 
	 header("location: login.php")

	  ?>
	
</body>
</html>