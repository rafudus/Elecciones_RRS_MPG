<%-- 
    Document   : Censo
    Created on : 15-dic-2016, 21:04:39
    Author     : Rafael
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Votante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="style.css" title="style">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Censo</title>
    </head>
    <body>
        <header></header>
        <div>
            <h1>Registro de censo</h1>
        <%HttpSession ses = request.getSession();%>
        <%ArrayList<Votante> v = (ArrayList<Votante>) ses.getAttribute("votantes");%>
        <%Iterator<Votante> It=v.iterator();%>
        <table>
            <tr><td>NIF</td><td>Clave</td><td>¿Ha Votado?</td></tr>
            <%while(It.hasNext()){
                Votante vot = It.next();
                out.println("<tr><td>"+vot.getNif()+"</td><td>"+vot.getClave()+"</td><td>"+vot.getVotado()+"</td></tr>");
            }
                %>
        </table>
        <a href="../index.jsp"><button>Volver</button></a>
        </div>
    </body>
</html>
