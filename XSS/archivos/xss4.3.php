<?php
    $correo = $_POST['input'];
    $url = $_POST['input2'];

    if(isset($_POST['btn'])){
        var_dump(filter_var($correo, FILTER_VALIDATE_EMAIL));
    }
    if(isset($_POST['btn2'])){
        var_dump(filter_var($url, FILTER_VALIDATE_URL, FILTER_FLAG_PATH_REQUIRED));
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio 2</title>
</head>
<body>    
    <form method="POST">
        <input type="text" name="input" placeholder="Correo">
        <button type="submit" name="btn">Enter</button>

        <input type="text" name="input2" placeholder="URL">
        <button type="submit" name="btn2">Enter 2</button>
    </form>
</body>
</html>