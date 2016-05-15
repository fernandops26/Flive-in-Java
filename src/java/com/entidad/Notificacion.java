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
public class Notificacion implements Serializable {
    private int codNotificacion;
    private String contenidoNot;
    private boolean leidoNot;
    private Perfil objPerfil;
    private Perfil objPerfilOrien;
    private TipoNotificacion objTipoNotificacion;
    
    public Notificacion(){
        this.objPerfil=new Perfil();
        this.objPerfilOrien=new Perfil();
        this.objTipoNotificacion=new TipoNotificacion();
    }

    public int getCodNotificacion() {
        return codNotificacion;
    }

    public void setCodNotificacion(int codNotificacion) {
        this.codNotificacion = codNotificacion;
    }

    public String getContenidoNot() {
        return contenidoNot;
    }

    public void setContenidoNot(String contenidoNot) {
        this.contenidoNot = contenidoNot;
    }

    public boolean isLeidoNot() {
        return leidoNot;
    }

    public void setLeidoNot(boolean leidoNot) {
        this.leidoNot = leidoNot;
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public Perfil getObjPerfilOrien() {
        return objPerfilOrien;
    }

    public void setObjPerfilOrien(Perfil objPerfilOrien) {
        this.objPerfilOrien = objPerfilOrien;
    }

    public TipoNotificacion getObjTipoNotificacion() {
        return objTipoNotificacion;
    }

    public void setObjTipoNotificacion(TipoNotificacion objTipoNotificacion) {
        this.objTipoNotificacion = objTipoNotificacion;
    }
    
    
    
}
