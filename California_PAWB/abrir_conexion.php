<?php 
  
  $server = '127.0.0.1';
  $user = 'root';
  $password = '';
  $bd = 'ferre_cali';

  $conexion = mysqli_connect($server, $user, $password, $bd);

 if($conexion === false){
    die("ERROR: no se puede extablecer la conexion . " . mysqli_connect_error());
}

 ?>

