/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.AlbumLogica;
import com.bean.ImagenLogica;
import com.bean.PublicacionLogica;
import com.bean.SessionLogica;
import com.entidad.Album;
import com.entidad.Imagen;
import com.entidad.Publicacion;
import com.util.Mensaje;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ferna
 */
@ManagedBean(name="cPublicacion")
@ViewScoped
public class cPublicacion implements Serializable{
     private List<Album> arrAlbumSinDetalle;
     private Publicacion objNPublicacion;
    private UploadedFile archivo;
    /**
     * Creates a new instance of cAlbum
     */
    public cPublicacion() {
        this.arrAlbumSinDetalle=new ArrayList<Album>();
        this.objNPublicacion=new Publicacion();
    }
    
    public List<Album> doCargarAlbumSinDetalle(){
        int codPerfil=new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil();
        this.arrAlbumSinDetalle=new AlbumLogica().BuscarAlbumUsuario(codPerfil);
        return this.arrAlbumSinDetalle;
    }
    

    public List<Album> getArrAlbumSinDetalle() {
        return arrAlbumSinDetalle;
    }

    public void setArrAlbumSinDetalle(List<Album> arrAlbumSinDetalle) {
        this.arrAlbumSinDetalle = arrAlbumSinDetalle;
    }

    public Publicacion getObjNPublicacion() {
        return objNPublicacion;
    }

    public void setObjNPublicacion(Publicacion objPublicacion) {
        this.objNPublicacion = objPublicacion;
    }

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }
    
    
    
   public void publicarPost(){
       ImagenLogica imL=new ImagenLogica();
       imL.getObjImagen().setImagen(this.archivo);
       String nombreImagenPost=imL.guardarImgPost();
       
       this.objNPublicacion.setImagenPub(nombreImagenPost);
       
       if(new PublicacionLogica().publicarPost(objNPublicacion)){
           Mensaje.msg("Ey Fliver!","Publicación exitosa"); 
           this.objNPublicacion=new Publicacion();
       }else{
           Mensaje.msg("Ups Fliver","Algo salió mal"); 
       }
       
       
   }
   
   public void retaurarPublicacion(){
       this.objNPublicacion=new Publicacion();
   }
    
}
