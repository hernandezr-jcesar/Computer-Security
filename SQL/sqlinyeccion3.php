<?php //Nota: conectar a la base de datos MySQL para usar la funcion.
	$mysqli = mysqli_connect("localhost","root","","Tema_sql");	
	$inyeccion = "' OR 1'";
	$inyeccion = $mysqli->real_escape_string($inyeccion);
	$danio = "SELECT * FROM clientes WHERE usuario='$inyeccion'";
	echo "Inyecci&oacute;n incorrecta escapada: <br />".$inyeccion."<br /><br />";
	$inyeccion = "'; DELETE FROM clientes WHERE 1 or usuario='";
	$inyeccion = $mysqli->real_escape_string($inyeccion);
	$danio = "SELECT * FROM clientes WHERE usuario='$inyeccion'";
	echo "Inyecci&oacute;n da&ntilde;ina escapada: <br />".$danio;
?>
