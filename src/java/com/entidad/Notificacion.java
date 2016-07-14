/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ferna
 */
public class Notificacion implements Serializable {
    private int codNotificacion;
    private Publicacion objPublicacion;
    private int leidoNot;
    private Perfil objPerfil;
    private Perfil objPerfilOrigen;
    private TipoNotificacion objTipoNotificacion;
    private Date fecha;
    
    public Notificacion(){
        this.objPerfil=new Perfil();
        this.objPerfilOrigen=new Perfil();
        this.objPublicacion=new Publicacion();
        this.objTipoNotificacion=new TipoNotificacion();
    }

    public int getCodNotificacion() {
        return codNotificacion;
    }

    public void setCodNotificacion(int codNotificacion) {
        this.codNotificacion = codNotificacion;
    }

    public int getLeidoNot() {
        return leidoNot;
    }

    public void setLeidoNot(int leidoNot) {
        this.leidoNot = leidoNot;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public Publicacion getObjPublicacion() {
        return objPublicacion;
    }

    public void setObjPublicacion(Publicacion objPublicacion) {
        this.objPublicacion = objPublicacion;
    }

    public Perfil getObjPerfilOrigen() {
        return objPerfilOrigen;
    }

    public void setObjPerfilOrigen(Perfil objPerfilOrigen) {
        this.objPerfilOrigen = objPerfilOrigen;
    }

    

    public TipoNotificacion getObjTipoNotificacion() {
        return objTipoNotificacion;
    }

    public void setObjTipoNotificacion(TipoNotificacion objTipoNotificacion) {
        this.objTipoNotificacion = objTipoNotificacion;
    }
    
    
    
}
