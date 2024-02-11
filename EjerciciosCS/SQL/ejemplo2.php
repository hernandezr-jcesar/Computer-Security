<?php
    $inyeccion = "'; DELETE FROM clientes WHERE 1 or usuario = '"; // se debería verificar
    $danio = "SELECT * FROM clientes WHERE usuario = '$inyeccion'"; // esta inyección.
    echo "Inyecci&oacute;n: ".$danio; // La nueva inyección incluye DELETE
?>