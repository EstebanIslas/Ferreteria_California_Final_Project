<?php
//include("abrir_conexion.php");

include("abrir_conexion.php");

if (!empty($_POST["username"]) && !empty($_POST["contrasena"])) {
	$nombre = $_POST["username"];
	$password = $_POST["contrasena"];

	$sql = "SELECT username, contrasena FROM usuarios WHERE username = '".$nombre."' and contrasena = '".$password."'";
	$consulta = mysqli_query($conexion,$sql ) or die("");

	$resultado = mysqli_num_rows($consulta);

	if ($resultado === 1) {
			
		session_start();
		$_SESSION["usuario"] = $_POST["username"];


		echo'<script type="text/javascript">
        alert("Bienvenido");
        window.location.href="index.php";
        </script>';



	}else{
		
		echo'<script type="text/javascript">
        alert("Usuario o contraseña incorrectos");
        window.location.href="index.html";
        </script>';
	}
}	



?>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="estilo_login.css">

	
</head>
 <body>
<img src="logo_cali.png" width="240px" height="135px">
<a href="index.html" class="btn orange">Menu principal</a>
	<form class="login" method="POST" action="login.php">
	    <h1 class="login-title">Inicio de sesion</h1>
	    <input type="text" class="login-input" placeholder="Nombre de usuario" autofocus name="username" required>
	    <input type="password" class="login-input" placeholder="Password" name="contrasena" required>
	   	<input type="submit" value="Ingresar" class="login-button">
	   	<br>
    </form>
    <form class="login2" method="POST" action="login.php">
    	<h2 class="login-title" >Registro de usuario</h2>
        <input type="text" name="username" class="login-input" required="" placeholder="Nombre de usuario">
        <input type="password" name="contrasena" class="login-input" required="" placeholder="Contraseña">
        <input type="password" name="confirm" class="login-input" required="" placeholder="Confirme su contraseña">
        <input type="submit" class="login-button"  name="btn_registrar" value="Registrar">
    </form>
        
  

    
	<?php 

		include 'abrir_conexion.php'; //abre la conexion mandando traer el archivo 

		if (isset($_POST['btn_registrar'])) { // evalua si el btn de registrar fue presionado



			$usuario = $_POST['username'];      //define las variables obteniendo lo que contienen los text con el metpdo _POST
			$contrasena = $_POST['contrasena'];
			$confirmacion = $_POST['confirm'];


			//almacena el string de la insercion agregando los campos a insertar con las variables declaradas
			$insertar = "INSERT INTO usuarios(username,contrasena) VALUES ('$usuario','$contrasena')";  


			if ($contrasena === $confirmacion) {  //evalua si las contraseñas coinciden o son iguales

				$insercion = mysqli_query($conexion, $insertar); //ejecuta la insercion pasandole la variable conexion y la variable string $insertar

				if ($insercion) { //evalua si la insercion se ejecuto correctamente y envia alerta de java script de registro satisfactorio

					echo'<script type="text/javascript">
			        alert("Registro satisfactorio");
			        </script>';
				}
			}else{ //envia alerta java script en caso de que las contraseñas no conicidan 
					echo'<script type="text/javascript">
			        alert("Las contraseñas no coinciden");
			        </script>';
			}
			# code...
		}

	 ?>

	

</body>
</html>