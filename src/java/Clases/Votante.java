/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Rafael
 */
public class Votante {
    private String nif;
    private String clave;
    private String votado;

    public Votante(String nif, String clave) {
        this.nif = nif;
        this.clave = clave;
    }
    
    public Votante(String nif, String clave, String votado) {
        this.nif = nif;
        this.clave = clave;
        this.votado = votado;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getVotado() {
        return votado;
    }

    public void setVotado(String votado) {
        this.votado = votado;
    }
    
    
}
