<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Elecciones</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="Vistas/style.css" title="style">
    </head>
    <body>
    <header>
        
    </header>
        <div id="prim">
            <form action="Controlar">
                <input type="text" name="user" required="required" placeholder="NIF">
                <input type="password" name="pass" required="required" placeholder="Clave"><br><br>
                <button onclick="submit" name="submit" value="alta">Alta</button>
                <button onclick="submit" name="submit" value="votar">Acceso a votaci&oacute;n</button>
                <button onclick="submit" name="submit" value="baja">Baja</button>
            </form>
            <form action="Controlar">
                <button onclick="submit" name="submit" value="cierre">Cierre de Escrutinio</button>
                <button onclick="submit" name="submit" value="censo">Censo</button>
            </form>
        </div>
    </body>
</html>
