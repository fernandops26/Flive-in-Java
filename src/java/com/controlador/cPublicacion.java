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
    
     private List<Album> arrAlbumSinDetalle;//Lista de albumes sin detalle,usado en una nueva publicacion
     private Publicacion objNPublicacion;//Objeto utilizado para la nueva publicacion
    private UploadedFile archivo; //Archivo del FileUpload de la nueva publicacion
    private List<Publicacion> arrPublicaciones;//Lista de publicaciones de un album, usado al ver las publicaciones de un album
    private int codAlbumParaPublicaciones=-1;
    //Vista de un Album(cualquier usuario autor o visitante)
    private Album objAlbumActualDetallado;
    private int codUsuarioDePerticion;
   
    
    /**
     * Creates a new instance of cAlbum
     */
    public cPublicacion() {
        this.objAlbumActualDetallado=new Album();
        this.arrAlbumSinDetalle=new ArrayList<Album>();
        this.objNPublicacion=new Publicacion();
        this.codAlbumParaPublicaciones=-1;
    }
    
    /**
     * Metodo para llenar la lista de albumes
     * @return La lista de albumes que pertenecen al usuario de la session
     **/
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

    public List<Publicacion> getArrPublicaciones() {
        return arrPublicaciones;
    }

    public void setArrPublicaciones(List<Publicacion> arrPublicaciones) {
        this.arrPublicaciones = arrPublicaciones;
    }
    
    
    
    
    /**
     * Metodo utilizado para almacenar una nueva publicacion
     **/
   public void publicarPost(){
       
       ImagenLogica imL=new ImagenLogica();
       
       //Pasa al objeto la imagen del fileUpload
       imL.getObjImagen().setImagen(this.archivo);
       
       //Guarda la imagen del nuevo post y se obtiene el nombre final de la imagen
       String nombreImagenPost=imL.guardarImgPost();
       
       //Inserta el nombre de la imagen al objeto de la nueva publicacion
       this.objNPublicacion.setImagenPub(nombreImagenPost);
       
       //Ingresa la nueva publicacion en la BD
       if(new PublicacionLogica().publicarPost(objNPublicacion)){
           //Mensaje satisfactorio
           Mensaje.msg("Ey Fliver!","Publicación exitosa"); 
           //Limpia el objeto
           this.retaurarPublicacion();
       }else{
           //Mensaje de error
           Mensaje.msg("Ups Fliver","Algo salió mal"); 
       }
       
       
   }
   
   //Restaura el objeto publicacion
   public void retaurarPublicacion(){
       this.objNPublicacion=new Publicacion();
   }
   
   
   //Ver publicacion de un album
   /**
     * @param codAlbum Codigo del album 
    **/
//    public void verPublicacionesDeAlbum(int codAlbum){
//        //Evalua si el codigo es mayor a 0, es decir un codigo existente
//        if(codAlbum>0){
//            //Asigna todas las publicaciones encontradas al arreglo de publicaciones
//            this.arrPublicaciones=new PublicacionLogica().buscarPublicacionesDeAlbum(codAlbum);
//            
//            //Redirige al usuario a la vista del las publicaciones del album
//            new SessionLogica().redirigirA("/user/vistaAlbum.xhtml");
//        } else {
//        }
//    }
    
    public void verPublicacionesDeAlbumes(){
        if(this.codAlbumParaPublicaciones!=-1){
            
             this.objAlbumActualDetallado=new AlbumLogica().buscarAlbDetPorCodigoAlbum(this.codAlbumParaPublicaciones);
             System.out.println(this.objAlbumActualDetallado.getNombreAlb());
            this.arrPublicaciones= new PublicacionLogica().buscarPublicacionesDeAlbum(this.codAlbumParaPublicaciones);
            
           
        }else{
            //Redireccionar pagina de error
        }
    }
    
    
    

    public int getCodAlbumParaPublicaciones() {
        return codAlbumParaPublicaciones;
    }

    public void setCodAlbumParaPublicaciones(int codAlbumParaPublicaciones) {
        this.codAlbumParaPublicaciones = codAlbumParaPublicaciones;
    }

    public Album getObjAlbumActualDetallado() {
        return objAlbumActualDetallado;
    }

    public void setObjAlbumActualDetallado(Album objAlbumActualDetallado) {
        this.objAlbumActualDetallado = objAlbumActualDetallado;
    }

    public int getCodUsuarioDePerticion() {
        return codUsuarioDePerticion;
    }

    public void setCodUsuarioDePerticion(int codUsuarioDePerticion) {
        this.codUsuarioDePerticion = codUsuarioDePerticion;
    }

    
    public boolean verfificarDueñoDelbumOPublicacion(){
        if(this.codUsuarioDePerticion==new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil()){
            return true;
        }else{
            return false;
        }
    }
    
    
}
