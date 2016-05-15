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
public class Gustar implements Serializable{
    private int codGustar;
    private Perfil objPerfil;
    private Publicacion objPublicacion;
    
    public Gustar(){
        this.objPerfil=new Perfil();
        this.objPublicacion=new Publicacion();
    }

    public int getCodGustar() {
        return codGustar;
    }

    public void setCodGustar(int codGustar) {
        this.codGustar = codGustar;
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
    
    
}
