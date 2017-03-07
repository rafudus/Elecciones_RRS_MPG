<%-- 
    Document   : Cierre
    Created on : 15-dic-2016, 21:04:26
    Author     : Rafael
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Partido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" title="style">
        <title>Cierre de Escrutinio</title>
    </head>
    <body>
        <header></header>
        <div>
            <h1>Resultado del Escrutinio</h1>
            <%HttpSession ses = request.getSession();%>
            <%ArrayList<Partido> par = (ArrayList<Partido>) ses.getAttribute("partidos");%>
            <%Iterator<Partido> It = par.iterator();%>
            <table>
                <tr><td>Logo</td><td>Nombre</td><td>Siglas</td><td>Número de Votos</td></tr>
                <%while (It.hasNext()) {
                        Partido p = It.next();
                        out.println("<tr><td><img src=" + p.getLogo() + " width='50' height='auto'></td><td>" + p.getNombre() + "</td><td>" + p.getSiglas() + "</td><td>" + p.getVotos() + "</td></tr>");
                    }
                %>
            </table>
        </div>
    </body>
</html>