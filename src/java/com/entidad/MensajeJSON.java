/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;

/**
 *
 * @author ferna
 */
public class MensajeJSON implements Serializable {
    private String nombrePerOrigen;
    private String accion;
    private int tipo;
    private String urlImagen;

    public MensajeJSON(String nombrePerOrigen, String accion,int tipo,String urlImagen) {
        this.nombrePerOrigen = nombrePerOrigen;
        this.accion = accion;
        this.tipo=tipo;
        this.urlImagen=urlImagen;
    }

    public MensajeJSON() {
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
    
    public String getNombrePerOrigen() {
        return nombrePerOrigen;
    }

    public void setNombrePerOrigen(String nombrePerOrigen) {
        this.nombrePerOrigen = nombrePerOrigen;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    
}
