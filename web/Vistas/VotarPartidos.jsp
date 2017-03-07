<%-- 
    Document   : VotarPartidos
    Created on : 15-dic-2016, 21:03:39
    Author     : Rafael
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Clases.Partido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css" title="style">
        <title>Partidos</title>
    </head>
    <header></header>
    <body>
        <div>
            <h1>Escoge un partido</h1>
            <form action="../ControlarVotar">
                <%HttpSession ses = request.getSession();%>
                <%ArrayList<Partido> par = (ArrayList<Partido>) ses.getAttribute("partidos");%>

                <table>

                    <tr>
                        <td><%out.println("<img src=" + par.get(0).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(0).getSiglas() + " required>");%><%out.println(par.get(0).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(1).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(1).getSiglas() + " >");%><%out.println(par.get(1).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(2).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(2).getSiglas() + " >");%><%out.println(par.get(2).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(3).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(3).getSiglas() + " >");%><%out.println(par.get(3).getNombre());%></td>
                    </tr>
                    <tr>
                        <td><%out.println("<img src=" + par.get(4).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(4).getSiglas() + " >");%><%out.println(par.get(4).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(5).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(5).getSiglas() + " >");%><%out.println(par.get(5).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(6).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(6).getSiglas() + " >");%><%out.println(par.get(6).getNombre());%></td>
                        <td><%out.println("<img src=" + par.get(7).getLogo() + ">");%><br><%out.println("<input type='radio' name='partido' value=" + par.get(7).getSiglas() + " >");%><%out.println(par.get(7).getNombre());%></td>
                    </tr>
                    <tr>
                        <td colspan="2"><a href="javascript:history.back()"><input type="button" value="Atr&aacute;s"</a>
                        </td><td colspan="2"><button onclick="submit" name="submit" value="votar">Votar</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
