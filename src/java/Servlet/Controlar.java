/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Clases.*;
import DAO.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rafael
 */
public class Controlar extends HttpServlet {

    private Connection Conexion;

    @Override
    public void init() throws ServletException {
        try {
            ConexionBD ConexBD = ConexionBD.GetConexion();
            Conexion = ConexBD.GetCon();
        } catch (ClassNotFoundException | SQLException cnfe) {
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        Operaciones operacion = new Operaciones();

        HttpSession ses = request.getSession(true);

        String eluser = request.getParameter("user");
        String lapass = request.getParameter("pass");

        ses.setAttribute("nif", eluser);
        ses.setAttribute("clave", lapass);
        ses.setAttribute("conex", Conexion);

        Votante vot = new Votante(eluser, lapass);
        ArrayList par = operacion.crearObjPartido(Conexion);
        ArrayList v = operacion.crearvotantes(Conexion);

        switch (request.getParameter("submit")) {

            case "alta": {
                if (!operacion.comprobar(Conexion, vot)) {
                    operacion.alta(Conexion, vot);
                    ses.setAttribute("msg", "Se ha registrado correctamente");
                    response.sendRedirect("Vistas/Aviso.jsp");
                } else {
                    ses.setAttribute("msg", "El usuario introducido ya está registrado");
                    response.sendRedirect("Vistas/Aviso.jsp");
                }
            }
            break;

            case "votar": {
                if (operacion.comprobar(Conexion, vot)) {
                    if (operacion.comprobarvotar(Conexion, vot)) {
                        ses.setAttribute("partidos", par);
                        response.sendRedirect("Vistas/VotarPartidos.jsp");
                    } else {
                        ses.setAttribute("msg", "Ya se ha votado con este usuario");
                        response.sendRedirect("Vistas/Aviso.jsp");
                    }
                } else {
                    ses.setAttribute("msg", "El usuario introducido no existe");
                    response.sendRedirect("Vistas/Aviso.jsp");
                }
            }
            break;

            case "baja": {
                if (operacion.comprobar(Conexion, vot)) {
                    if (operacion.comprobarvotar(Conexion, vot)) {
                        if (operacion.comprobarbaja(Conexion, vot)) {
                            operacion.baja(Conexion, vot);
                            ses.setAttribute("msg", "Se ha dado de baja correctamente");
                            response.sendRedirect("Vistas/Aviso.jsp");
                        } else {
                            ses.setAttribute("msg", "La contraseña es incorrecta");
                            response.sendRedirect("Vistas/Aviso.jsp");
                        }
                    } else {
                        ses.setAttribute("msg", "El usuario ha votado y no puede darse de baja");
                        response.sendRedirect("Vistas/Aviso.jsp");
                    }
                } else {
                    ses.setAttribute("msg", "El usuario introducido no existe");
                    response.sendRedirect("Vistas/Aviso.jsp");
                }
            }
            break;

            case "cierre": {
                ses.setAttribute("partidos", par);
                response.sendRedirect("Vistas/Cierre.jsp");
            }
            break;

            case "censo": {
                ses.setAttribute("votantes", v);
                response.sendRedirect("Vistas/Censo.jsp");
            }
            break;

            default:
                throw new AssertionError();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controlar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
