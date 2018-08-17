

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Productos</title>
    <link rel="stylesheet" type="text/css" media="screen" href="productos.css" />
</head>
<body>
    <header>
        <div class="cent_contenido">
            <div id="logo"><img id="logo" src="logo_cali.png" alt="Ferreteria California"/></div>
            <nav>
                <form action="" method="post">
                    <a href="index.html">Inicio</a>
                    <a href="conocenos.html">Conocenos</a>
                    <a href="contacto.html">Contacto</a>
                    <a href="redireccion.php">Comprar</a>
                    <a href="">Productos</a>  
                    <a href="login.php">Iniciar sesion</a>  
                </form> 
                     
            </nav>
        </div>
    </header>


    <div class="productos">
        <h1><span style="color: #f56f3a">Catalogo de&nbsp;</span><span style="color:whitesmoke">Productos</span></h1>
        <div class="row">
            <?php 
                include ('abrir_conexion.php');
                $sql = "SELECT nombre, marca, precio_unitario, imagen   FROM productos";
                $consulta = mysqli_query($conexion,$sql) or die (mysqli_error());
                while ($f=mysqli_fetch_array($consulta)) {
            ?>
                    <div class="col-xs-10 col-sm-6 col-md-4 imagenes">
                        <div class="card">
                            <center> <img src="./productos/<?php echo $f["imagen"]; ?>" alt="imagen de un serrote"></center>
                            <h4 align="center"><?php echo $f["nombre"]; ?></h4>
                            <b><p align="center"><?php echo $f["marca"]; ?></p></b>
                            <b><p align="center">$<?php echo $f["precio_unitario"]; ?></p></b>
                        </div>
                    </div> 
            <?php 
                }

            ?>
              
                  
        </div>
    </div>
    <footer>
            <div class="cent_contenido2">
                <br>
                <p>© Ferreteria California</p>
                <p>Colonia “El Paraíso”</p>
                <p>Calle Octaviano Barredo S/N</p>
                <p>Tulancingo, Hidalgo</p>
                <p>México</p>
                </div>
            <div class="cent_contenido3">
                <p>© SoluSoft </p>
                <p>Islas Santos -- Escamilla Luqueño</p>
            </div>
            <div class="cent_contenido3">
                <img  id="whats" src="whatsapp-logo-PNG-Transparent.png" width="30px" height="30px"/><p>7751307645<br>7751432678</p>
            </div>
        </footer>
</body>
</html>
