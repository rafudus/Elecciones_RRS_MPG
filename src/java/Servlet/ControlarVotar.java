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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rafael
 */
public class ControlarVotar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html;charset=UTF-8");
        Partido par;
        Operaciones operacion = new Operaciones();
        HttpSession ses = request.getSession();
        String nif = (String) ses.getAttribute("nif");
        String clave = (String) ses.getAttribute("clave");
        Votante vot = new Votante(nif, clave);
        String siglas = request.getParameter("partido");
        Connection conex = (Connection) ses.getAttribute("conex");
        par=operacion.crearObjPartidoSiglas(conex, siglas);

        if (operacion.comprobarvotar(conex, vot)) {
            try {
                conex.setAutoCommit(false);
                /* registro el voto */
                operacion.votar(conex, vot);
                /* cuento el voto */
                operacion.registrarvoto(conex, par);
                conex.commit();
            } catch (SQLException SQLE1) {
                if (conex != null) {
                    try {
                        conex.rollback();
                    } catch (SQLException SQLE2) {
                    }
                }
            }
            ses.setAttribute("msg", "Gracias por votar: " + par.getNombre());
            response.sendRedirect("Vistas/Aviso.jsp");
        } else {
            ses.setAttribute("msg", "Ya se ha votado con este usuario");
            response.sendRedirect("Vistas/Aviso.jsp");
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
            Logger.getLogger(ControlarVotar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControlarVotar.class.getName()).log(Level.SEVERE, null, ex);
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
