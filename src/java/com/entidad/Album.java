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
public class Album implements Serializable {
    private int codAlbum;
    private String nombreAlb;
    private String descripcionAlb;
    private Date f_creacionAlb;
    private Perfil objPerfil;
    private Categoria objCategoria;
    private List<TagAlbum> listaTagAlbum;
    private int n_publicaciones;
    
    public Album(){
        this.objPerfil=new Perfil();
        this.objCategoria=new Categoria();
    }

    public int getCodAlbum() {
        return codAlbum;
    }

    public void setCodAlbum(int codAlbum) {
        this.codAlbum = codAlbum;
    }

    public String getNombreAlb() {
        return nombreAlb;
    }

    public void setNombreAlb(String nombreAlb) {
        this.nombreAlb = nombreAlb;
    }

    public String getDescripcionAlb() {
        return descripcionAlb;
    }

    public void setDescripcionAlb(String descripcionAlb) {
        this.descripcionAlb = descripcionAlb;
    }

    public Date getF_creacionAlb() {
        return f_creacionAlb;
    }

    public void setF_creacionAlb(Date f_creacionAlb) {
        this.f_creacionAlb = f_creacionAlb;
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public int getN_publicaciones() {
        return n_publicaciones;
    }

    public void setN_publicaciones(int n_publicaciones) {
        this.n_publicaciones = n_publicaciones;
    }

    public Categoria getObjCategoria() {
        return objCategoria;
    }

    public void setObjCategoria(Categoria objCategoria) {
        this.objCategoria = objCategoria;
    }
    
    
    
    
    
}
