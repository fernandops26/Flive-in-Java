/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.AlbumLogica;
import com.bean.CategoriaLogica;
import com.bean.ImagenLogica;
import com.bean.PublicacionLogica;
import com.entidad.Album;
import com.entidad.Publicacion;
import com.bean.SessionLogica;
import com.entidad.Categoria;
import com.util.Mensaje;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;


/**
 *
 * @author ferna
 **/

public class cAlbum implements Serializable {
    private List<Album> arrAlbumsUsuario;//Lista de albumes del usuario, usado para ver todos los albumes del usuario actual
    private Album objAlbumEliminar;
    private int numPostEliminados;
    private Album objAlbumNuevo;
    private Album objAlbumEditar;

   public cAlbum(){
      this.objAlbumEliminar=new Album();
      this.objAlbumNuevo=new Album();
      this.objAlbumEditar=new Album();
      this.numPostEliminados=0;
   }
   
   /**
    * Lista los albumes del usuario detalladamente
     * @return La lista de albumes encontrados
    **/
   public List<Album> listarAlbumDetallado(){
       //Obtiene el codigo del perfil de usuario de la session actual
       int codPerfil=new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil();
       //Asigna en la lista todos los albumes del usuario actual encontrados en la BD
       this.arrAlbumsUsuario=new AlbumLogica().BuscarAlbumDetalladoUsuario(codPerfil);
       return this.arrAlbumsUsuario;
   }

    public List<Album> getArrAlbumsUsuario() {
        return arrAlbumsUsuario;
    }

    public void setArrAlbumsUsuario(List<Album> arrAlbumsUsuario) {
        this.arrAlbumsUsuario = arrAlbumsUsuario;
    }

    public Album getObjAlbumEliminar() {
        return objAlbumEliminar;
    }

    public void setObjAlbumEliminar(Album objAlbumEliminar) {
        this.objAlbumEliminar = objAlbumEliminar;
    }

   
    
    
    public void insertarValorAEliminar(int codAlbum,String nombre){
        this.objAlbumEliminar.setCodAlbum(codAlbum);
        this.objAlbumEliminar.setNombreAlb(nombre);
        System.out.println(codAlbum);
        System.out.println(nombre);
    }
    
    public void insertarValorAActualizar(int codAlbum){
        this.objAlbumEditar=new AlbumLogica().buscarAlbDetPorCodigoAlbum(codAlbum);
    }
    
    public void eliminarAlbum(){
        List<Publicacion> arrPublic=new PublicacionLogica().buscarPublicacionesDeAlbum(this.objAlbumEliminar.getCodAlbum());
        ImagenLogica imL;
        int cont=0;
        for(int i = 0; i < arrPublic.size(); i++) {
            imL=new ImagenLogica();
            imL.getObjImagen().setNombreArchivo(arrPublic.get(i).getImagenPub());
            if(imL.eliminarImagenesDePost()){
                cont++;
            }
            imL=null;
        }
        this.numPostEliminados=cont;
        if(cont==arrPublic.size() && new AlbumLogica().eliminarAlbum(this.objAlbumEliminar.getCodAlbum())){
            new SessionLogica().redirigirA("/user/misAlbum.xhtml");
            Mensaje.msg("Eliminación de Album", "Realizado correctamente");
        }else{
             Mensaje.msg("Upps!", "Se produjo algun error ");
        }
        
    }
    
    public void actualizarAlbum(){
        if(new AlbumLogica().actualizarAlbum(this.objAlbumEditar)){
            this.restaurarValoresActualziación();
            Mensaje.msg("Actualizacion de Album", "Realizado correctamente");
        }else{
            Mensaje.msg("Upps!", "Se produjo algun error ");
        }
    }
    
    public void crearAlbum(){
        this.objAlbumNuevo.getObjPerfil().setCodPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
        if(new AlbumLogica().crearAlbum(this.objAlbumNuevo)){
            new SessionLogica().redirigirA("/user/misAlbum.xhtml");
            Mensaje.msg("Nuevo Album", "Creado satisfactoriamente");
            
        }else{
            Mensaje.msg("Upps!", "Se produjo algún error");
        }
    }
    
    public void restaurarValoresEliminacion(){
        this.objAlbumEliminar=new Album();
        this.numPostEliminados=0;
    }
    
    public void restaurarValoresActualziación(){
        this.objAlbumEliminar=new Album();
        this.numPostEliminados=0;
    }
    public void restaurarValoresNuevoAlbum(){
        this.objAlbumNuevo=new Album();
    }
    

    public List<Categoria> cargarCategorias(){
        return new CategoriaLogica().ListarCategorias();
    }

    public Album getObjAlbumNuevo() {
        return objAlbumNuevo;
    }

    public void setObjAlbumNuevo(Album objAlbumNuevo) {
        this.objAlbumNuevo = objAlbumNuevo;
    }

    public int getNumPostEliminados() {
        return numPostEliminados;
    }

    public void setNumPostEliminados(int numPostEliminados) {
        this.numPostEliminados = numPostEliminados;
    }

    public Album getObjAlbumEditar() {
        return objAlbumEditar;
    }

    public void setObjAlbumEditar(Album objAlbumEditar) {
        this.objAlbumEditar = objAlbumEditar;
    }

    
    
    
    
    
   
   
    
}
