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
public class TipoNotificacion implements Serializable {
    private int codTipoNotificacion;
    private String nombreTipoNot;
    
    public TipoNotificacion(){
        
    }

    public int getCodTipoNotificacion() {
        return codTipoNotificacion;
    }

    public void setCodTipoNotificacion(int codTipoNotificacion) {
        this.codTipoNotificacion = codTipoNotificacion;
    }

    public String getNombreTipoNot() {
        return nombreTipoNot;
    }

    public void setNombreTipoNot(String nombreTipoNot) {
        this.nombreTipoNot = nombreTipoNot;
    }
    
    
}
