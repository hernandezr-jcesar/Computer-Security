<?php
	$nombre = "chapy";
	// un nombre de usuario
	$query = "SELECT * FROM clientes WHERE usuario='$nombre'";
	echo "SQL Normal: ".$query."<br />";
	$inyeccion = "' OR 1'";
	// entrada del usuario utiliza SQL Injection
	$danio = "SELECT * FROM clientes WHERE usuario='$inyeccion'"; // consulta no segura
	echo "La inyecci&oacute;n: ".$danio;
	// muestra la nueva consulta con inyección.
?>
