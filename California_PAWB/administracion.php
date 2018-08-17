<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>ZonaVIP</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="administracion.css">
	<style>body {
  		background: #0f1216;
		}</style>
</head>
<body>

	<?php 

	session_start();

	if (!isset($_SESSION["usuario"])) {
		header("location:login.php");# code...
	}

	?>
	<h1 color="white">ZONA VIP</h1>
	<?php 
		printf("usuario: %s", $_SESSION["usuario"]);

	 ?>
	 <br><a href="cerrar_session.php"><button type="button" class="btn btn-outline-danger">Cerrar sesion</button></a>
	
	
</body>
</html>