<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ferretería California</title>
    <link rel="stylesheet" href="Estilo_Ferre.css">
</head>
<body>
    <header>
        <div class="cent_contenido">
            <div id="logo"><img id="logo" src="logo_cali.png" alt="Ferreteria California"/></div>
            <nav>
                    <a href="">Inicio</a>
                    <a href="">Conocenos</a>
                    <a href="">Ventas</a>
                    <a href="">Compras</a>
                    <a href="">Productos</a>            
            </nav>
        </div>
    </header>
    <!-- ############################# Cuerpo del Formulario #################################### -->
    <div id="general">
        <br>
        <h1><span style="color: #f56f3a">Registro&nbsp;</span><span style="color:whitesmoke">de Empleados</span></h1>
        <br>     
        <div id="Formulario">
            <div id="Informacion">
                <form method="POST" action="registro_ventas.php">
                    <h3>Ingrese la información de los Empleados</h3><br><br>
                    <p><label for="curp">Curp:</label> 
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="curp" name="curp" type="text" placeholder="Ingrese un número" /></p><br>
                    <p><label for="nombre">Nombre:</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" id="nombre" name="nombre" placeholder="Ingrese una Fecha" /></p><br>
                    <p><label for="ape_pat">Apellido Paterno: 
                        &nbsp;&nbsp;&nbsp;
                        <input type="text" id="ape_pat" name="ape_pat" placeholder="Nombres de Productos" /></p><br>
                    <p><label for="ape_mat">Apellido Materno: 
                        &nbsp;&nbsp;
                        <input id="ape_mat" name="ape_mat" type="text" placeholder="CURP de Empleado" /></p><br>
                    <p><label for="calle">Calle:  
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="calle" name="calle" type="text" placeholder="Total monetario" /></p><br>
                        
                    <p><label for="numero">Número:  
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="numero" name="numero" type="text" placeholder="Total monetario" /></p><br>

                    <p><label for="colonia">Colonia:  
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="colonia" name="colonia" type="text" placeholder="Total monetario" /></p><br>

                    <p><label for="tel">Telefono:  
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="tel" name="tel" type="text" placeholder="Total monetario" /></p><br>

                    <p><label for="edad">Edad:  
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="edad" name="edad" type="text" placeholder="Total monetario" /></p><br>

                    <span id="botones"><input class="btn_boton" type="submit" value="Insertar" name="btn_insertar"/>&nbsp;&nbsp;</span>
                    <span id="botones"><input class="btn_boton" type="submit" value="Editar" name="btn_editar"/>&nbsp;&nbsp;</span>
                    <span id="botones"><input class="btn_boton" type="submit" value="Eliminar" name="btn_eliminar"/>&nbsp;&nbsp;</span>
                    <span id="botones"><input class="btn_boton" type="reset" value="Cancelar" />&nbsp;&nbsp;</span>

                    
                </form>
            </div>
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
    </footer>

    <?php

        include("abrir_conexion.php");


        // Guarda en variables los valores que se obtienen del formulario con los input

        $folio = $_POST['folio'];
        $fecha = $_POST['fecha'];
        $nombre = $_POST['nombre'];
        $curp = $_POST['curp'];
        $total = $_POST['total'];
        
        if(isset($_POST['btn_insertar']))
        {
    
            $folio = $_POST['folio'];
            $fecha = $_POST['fecha'];
            $nombre = $_POST['nombre'];
            $curp = $_POST['curp'];
            $total = $_POST['total'];
            
            if($folio=="" || $fecha=="" || $nombre=="" || $total=="")
            {
                echo "<h1>Existen campos obligatorios vacios.....</h1>";
            }
            else
            {
                mysqli_query($conexion, "INSERT INTO $tabla_db1 
                (folio, fecha, nombre, curp, total)
                VALUES
                ('$folio', '$fecha', '$nombre', '$curp', '$total')");
                echo "<h1>Registro Exitoso!!!</h1>";
                
            }
        }


        if(isset($_POST['btn_editar']))
                {
                    
                    $folio = $_POST['folio'];
                    $fecha = $_POST['fecha'];
                    $nombre = $_POST['nombre'];
                    $curp = $_POST['curp'];
                    $total = $_POST['total'];
                    
                    if($folio=="" || $fecha=="" || $nombre=="" || $total=="")
                    {
                        echo "Existen campos obligatorios vacios.....";
                    }
                    else
                    {
                        $existe = 0;
                        $resultados = mysqli_query($conexion,"SELECT * FROM $tabla_db1 WHERE folio = '$folio'");
                        while($consulta = mysqli_fetch_array($resultados))
                        {
                            $existe++;
                        }
                        if ($existe == 0){echo "El folio no existe intente de nuevo.....";}
                        
                        else{
                            $_UPDATE_SQL="UPDATE $tabla_db1 SET 

                            folio='$folio',
                            fecha='$fecha',
                            nombre='$nombre',
                            curp='$curp',
                            total='$total'
                            
                            WHERE folio='$folio'";
    
                            mysqli_query($conexion, $_UPDATE_SQL);
                            echo "Actualización Exitosa!!!"; 
                        }
                    }
                }

        if(isset($_POST['btn_eliminar']))
        {
            $doc = $_POST['folio'];
            $existe=0;
            
            if($folio=="")
            {
                echo "El folio se encuentra vacio.....";
            }
            else
            {
                $resultados = mysqli_query($conexion,"SELECT * FROM $tabla_db1 WHERE folio = '$folio'");
                while($consulta = mysqli_fetch_array($resultados))
                {
                    $existe++;
                }
                if ($existe == 0){echo "El folio no existe intente de nuevo.....";}
                else{
                    $_DELETE_SQL = "DELETE FROM $tabla_db1 WHERE folio = $folio";
                    mysqli_query($conexion, $_DELETE_SQL);
                    echo "Actualización Exitosa!!!";
                }
            }
        }


    ?>

</body>
</html>