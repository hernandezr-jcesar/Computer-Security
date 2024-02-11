<?php
    // $nuevo = htmlspecialchars("<a href='test'>Prueba</a>", ENT_QUOTES);
    // echo $nuevo; // &lt;a href=&#039;test&#039;&gt;Test&lt;/a&gt;

    if(isset($_POST['btn'])){
        // $input = htmlspecialchars($_POST['input'], ENT_QUOTES);
        $input = htmlentities($_POST['input'], ENT_QUOTES);
        echo $input;
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
        <input type="text" name="input" placeholder="Enter">
        <button type="submit" name="btn">Enter</button>
    </form>
</body>
</html>
