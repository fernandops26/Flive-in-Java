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
public class Publicacion implements Serializable {
    private int codPublicacion;
    private String tituloPub;
    private String imagenPub;
    private Date f_creacionPub;
    private int n_likesPub;
    private Album objAlbum;
    private List<TagPublicacion> listaTagPublicacion;
    private List<Comentario> listaComentario;
    private List<Gustar> listaGustar;
    
    //falta la lsita de gustar
    
    public Publicacion(){
        this.objAlbum= new Album();
    }

    public List<Gustar> getListaGustar() {
        return listaGustar;
    }

    public void setListaGustar(List<Gustar> listaGustar) {
        this.listaGustar = listaGustar;
    }

    public int getCodPublicacion() {
        return codPublicacion;
    }

    public void setCodPublicacion(int codPublicacion) {
        this.codPublicacion = codPublicacion;
    }

    public String getTituloPub() {
        return tituloPub;
    }

    public void setTituloPub(String tituloPub) {
        this.tituloPub = tituloPub;
    }

    public String getImagenPub() {
        return imagenPub;
    }

    public void setImagenPub(String imagenPub) {
        this.imagenPub = imagenPub;
    }

    public Date getF_creacionPub() {
        return f_creacionPub;
    }

    public void setF_creacionPub(Date f_creacionPub) {
        this.f_creacionPub = f_creacionPub;
    }

    public int getN_likesPub() {
        return n_likesPub;
    }

    public void setN_likesPub(int n_likesPub) {
        this.n_likesPub = n_likesPub;
    }

    public Album getObjAlbum() {
        return objAlbum;
    }

    public void setObjAlbum(Album objAlbum) {
        this.objAlbum = objAlbum;
    }

    public List<TagPublicacion> getListaTagPublicacion() {
        return listaTagPublicacion;
    }

    public void setListaTagPublicacion(List<TagPublicacion> listaTagPublicacion) {
        this.listaTagPublicacion = listaTagPublicacion;
    }

    public List<Comentario> getListaComentario() {
        return listaComentario;
    }

    public void setListaComentario(List<Comentario> listaComentario) {
        this.listaComentario = listaComentario;
    }
    
    
    
    
}
