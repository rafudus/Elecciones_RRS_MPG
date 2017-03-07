/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Rafael
 */
import java.sql.*;
import Clases.*;
import java.util.ArrayList;

public class Operaciones {

    public boolean comprobar(Connection c, Votante v) throws SQLException {
        boolean esta = false;
        String dni = v.getNif();

        String query = "SELECT nif FROM votante WHERE nif=?";

        PreparedStatement prepStm = c.prepareStatement(query);
        prepStm.setString(1, dni);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            esta = true;
        }
        return esta;
    }

    public void alta(Connection c, Votante v) throws SQLException {
        String dni = v.getNif();
        String password = v.getClave();

        String query = "insert into votante values (null, ?, AES_ENCRYPT(?,'rafael'), 'N')";
        PreparedStatement prepStm = c.prepareStatement(query);
        prepStm.setString(1, dni);
        prepStm.setString(2, password);
        prepStm.executeUpdate();
    }

    public boolean comprobarvotar(Connection c, Votante v) throws SQLException {
        boolean esta = false;
        String dni = v.getNif();

        String query = "SELECT nif FROM votante WHERE nif=? and votado='N'";

        PreparedStatement prepStm = c.prepareStatement(query);
        prepStm.setString(1, dni);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            esta = true;
        }
        return esta;
    }

    public ArrayList<Partido> crearObjPartido(Connection c) throws SQLException {
        ArrayList<Partido> par = new ArrayList<Partido>();
        String siglas;
        String nombre;
        String logo;
        int votos;
        String query = "SELECT * FROM partido";

        PreparedStatement prepStm = c.prepareStatement(query);
        ResultSet rs = prepStm.executeQuery();

        while (rs.next()) {
            siglas = rs.getString("siglas");
            nombre = rs.getString("nombre");
            logo = rs.getString("logo");
            votos = rs.getInt("votos");
            par.add(new Partido(siglas, nombre, logo, votos));
        }
        return par;
    }

    public Partido crearObjPartidoSiglas(Connection c, String siglas) throws SQLException {
        Partido par = null;
        String nombre;
        String logo;
        int votos;
        String query = "SELECT * FROM partido WHERE siglas=?";

        PreparedStatement prepStm = c.prepareStatement(query);
        prepStm.setString(1, siglas);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            nombre = rs.getString("nombre");
            logo = rs.getString("logo");
            votos = rs.getInt("votos");
            par = new Partido(siglas, nombre, logo, votos);
        }
        return par;
    }

    public int votar(Connection c, Votante v) throws SQLException {
        int resultado;
        try {
            Statement s = c.createStatement();
            resultado = s.executeUpdate("update votante set votado='S' where nif='" + v.getNif() + "';");
        } catch (SQLException SQLE) {
            throw new SQLException(SQLE);
        }
        if (resultado == 0) {
            throw new SQLException();
        }
        return resultado;
    }

    public int registrarvoto(Connection c, Partido p) throws SQLException {
        int resultado;
        try {
            Statement s = c.createStatement();
            resultado = s.executeUpdate("update partido set votos=votos+1 where siglas='" + p.getSiglas() + "';");
        } catch (SQLException SQLE) {
            throw new SQLException(SQLE);
        }
        if (resultado == 0) {
            throw new SQLException();
        }
        return resultado;
    }

    public boolean comprobarbaja(Connection conexion, Votante votante) throws SQLException {
        boolean esta = false;
        String dni = votante.getNif();
        String password = votante.getClave();

        String query = "SELECT nif FROM votante WHERE nif=? and AES_DECRYPT(clave,'rafael')=?";

        PreparedStatement prepStm = conexion.prepareStatement(query);
        prepStm.setString(1, dni);
        prepStm.setString(2, password);

        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            esta = true;
        }
        return esta;
    }

    public void baja(Connection Conexion, Votante Votante) throws SQLException {
        String dni = Votante.getNif();
        String password = Votante.getClave();

        String query = "delete from votante where nif=? and AES_DECRYPT(clave,'rafael')=?";
        PreparedStatement prepStm = Conexion.prepareStatement(query);
        prepStm.setString(1, dni);
        prepStm.setString(2, password);
        prepStm.executeUpdate();
    }

    public ArrayList<Votante> crearvotantes(Connection c) throws SQLException {
        ArrayList<Votante> v = new ArrayList<Votante>();
        String nif;
        String clave;
        String votado;
        String query = "SELECT nif,AES_DECRYPT(clave,'rafael'),votado FROM votante";

        PreparedStatement prepStm = c.prepareStatement(query);
        ResultSet rs = prepStm.executeQuery();

        while (rs.next()) {
            nif = rs.getString("nif");
            
            clave = rs.getString("AES_DECRYPT(clave,'rafael')");
            
            votado = rs.getString("votado");
            v.add(new Votante(nif, clave, votado));
        }
        return v;
    }
}
