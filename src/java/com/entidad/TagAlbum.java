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
public class TagAlbum implements Serializable{
    private int codTaglbum;
    private String nombreTagAlbum;
    private Album objAlbum;
    
    public TagAlbum(){
        this.objAlbum=new Album();
    }

    public int getCodTaglbum() {
        return codTaglbum;
    }

    public void setCodTaglbum(int codTaglbum) {
        this.codTaglbum = codTaglbum;
    }

    public String getNombreTagAlbum() {
        return nombreTagAlbum;
    }

    public void setNombreTagAlbum(String nombreTagAlbum) {
        this.nombreTagAlbum = nombreTagAlbum;
    }

    public Album getObjAlbum() {
        return objAlbum;
    }

    public void setObjAlbum(Album objAlbum) {
        this.objAlbum = objAlbum;
    }
    
    
    
}
