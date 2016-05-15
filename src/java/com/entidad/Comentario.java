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
public class Comentario implements Serializable{
    private int codComentario;
    private Date f_creacionCom;
    private String textoCom;
    private Publicacion objPublicacion;
    private Perfil objPerfil;
    
    public Comentario(){
        this.objPerfil=new Perfil();
        this.objPublicacion=new Publicacion();
    }

    public int getCodComentario() {
        return codComentario;
    }

    public void setCodComentario(int codComentario) {
        this.codComentario = codComentario;
    }

    public Date getF_creacionCom() {
        return f_creacionCom;
    }

    public void setF_creacionCom(Date f_creacionCom) {
        this.f_creacionCom = f_creacionCom;
    }

    public String getTextoCom() {
        return textoCom;
    }

    public void setTextoCom(String textoCom) {
        this.textoCom = textoCom;
    }

    public Publicacion getObjPublicacion() {
        return objPublicacion;
    }

    public void setObjPublicacion(Publicacion objPublicacion) {
        this.objPublicacion = objPublicacion;
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }
    
    
}
