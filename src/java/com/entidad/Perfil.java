/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ferna
 */
public class Perfil implements Serializable{
    private int codPerfil;
    private String nombrePer;
    private String apellidosPer;
    private String imagenPer;
    private Date f_creacionPer;
    private Provincia objProvincia;
    private List<Seguidor> listaSeguidores;
    private List<Notificacion> listaNotificaciones;
    
    public Perfil(){
        this.objProvincia=new Provincia();
    }

    public List<Notificacion> getListaNotificaciones() {
        return listaNotificaciones;
    }

    public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
        this.listaNotificaciones = listaNotificaciones;
    }

    public List<Seguidor> getListaSeguidores() {
        return listaSeguidores;
    }

    public void setListaSeguidores(List<Seguidor> listaSeguidores) {
        this.listaSeguidores = listaSeguidores;
    }
    
    public int getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(int codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNombrePer() {
        return nombrePer;
    }

    public void setNombrePer(String nombrePer) {
        this.nombrePer = nombrePer;
    }

    public String getApellidosPer() {
        return apellidosPer;
    }

    public void setApellidosPer(String apellidosPer) {
        this.apellidosPer = apellidosPer;
    }

   

    public String getImagenPer() {
        return imagenPer;
    }

    public void setImagenPer(String imagenPer) {
        this.imagenPer = imagenPer;
    }

    public Date getF_creacionPer() {
        return f_creacionPer;
    }

    public void setF_creacionPer(Date f_creacionPer) {
        this.f_creacionPer = f_creacionPer;
    }

    public Provincia getObjProvincia() {
        return objProvincia;
    }

    public void setObjProvincia(Provincia objProvincia) {
        this.objProvincia = objProvincia;
    }
    
    
}
