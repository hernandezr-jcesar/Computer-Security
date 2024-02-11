<?php
	session_start();
	if ( !isset($_SESSION['USER_NAME']) ) {
		echo "Su identificaci&oacute;n...";
		header( "Location: identificar.php" );
	}else{
		$db = new mysql('localhost', 'root', '', 'demo');
		if( $db->connect_errno > 0 ){
		die('No pudo conectarse a la base de datos ['.$db->connect_error.']');
		}
		if( $_SERVER['REQUEST_METHOD'] == "POST" ) {
			$query = "UPDATE usuarios SET nombre='".$_POST['disp_name']."' WHERE usuario='".$_SESSION['USER_NAME'] "';";
			$db->query($query);
		}else{
			if( strcmp($_SESSION['USER_NAME'], 'admin') == 0 ){
				echo "Bienvenido administrador!<br /><br />";
				echo "Usuarios:<br/>";
				$query = "SELECT nombre FROM usuarios WHERE usuario!='admin'";
				if( !$result = $db->query($query) ){
					die('Error al actualizar ['.$db->error.']');
				}
				while( $row = $result->fetch_assoc() ){
					echo $row[nombre].'<br />';
				}
			}else{
				echo "<form id='tags' name='tags' action='home.php' method='POST'>";
				echo "Actualizar el nombre:<input type='text' id='disp_name' name='disp_name' value=''>";
				echo "<input type='submit' value='Actualizar'>";
			}
		}
	}
?>
