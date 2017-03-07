<%-- 
    Document   : Error
    Created on : 19-dic-2016, 1:52:47
    Author     : Rafael
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" title="style">
        <title>Aviso</title>
    </head>
    <header></header>
    <body>
        <div id="err">
            <h1>Mensaje:</h1>
            <p>
                <% HttpSession ses = request.getSession();%>
                <% out.println(ses.getAttribute("msg"));%>
            </p>
            <a href="../index.jsp"><button>Volver</button></a>
        </div>
    </body>
</html>
