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
public class TagPublicacion implements Serializable{
    private int codTagPublicacion;
    private String nombreTagPub;
    private Publicacion objPublicacion;
    
    public TagPublicacion(){
        this.objPublicacion=new Publicacion();
    }

    public int getCodTagPublicacion() {
        return codTagPublicacion;
    }

    public void setCodTagPublicacion(int codTagPublicacion) {
        this.codTagPublicacion = codTagPublicacion;
    }

    public String getNombreTagPub() {
        return nombreTagPub;
    }

    public void setNombreTagPub(String nombreTagPub) {
        this.nombreTagPub = nombreTagPub;
    }

    public Publicacion getObjPublicacion() {
        return objPublicacion;
    }

    public void setObjPublicacion(Publicacion objPublicacion) {
        this.objPublicacion = objPublicacion;
    }
    
    
}
