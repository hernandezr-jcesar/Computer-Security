<?php
    if (isset($_POST["nombre"])){
    $fp = fopen('datos.txt', 'a');
    fwrite($fp, $_POST["nombre"]);
    fclose($fp);
    }
?>
<form action="" method="post">
    <br>Enviar saludo:<input type="text" name="nombre" size="50" value="" />
    <input type="submit" />
</form>
    <br/><br/>
Saludando:

<?php 
    include('datos.txt');
?>