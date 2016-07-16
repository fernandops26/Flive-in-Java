/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.AlbumLogica;
import com.bean.GustarLogica;
import com.bean.NotificacionLogica;
import com.bean.PerfilLogica;
import com.bean.PublicacionLogica;
import com.bean.SeguidorLogica;
import com.bean.SessionLogica;
import com.entidad.Gustar;
import com.entidad.Notificacion;
import com.entidad.Perfil;
import com.entidad.Seguidor;
import com.util.Mensaje;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ferna
 */
public class cPerfil implements Serializable{
    private int codPerfilPeticicion=-1;
    private Perfil objPerfilAVer;
    private Seguidor objNSeguidor;//Objeto utlizado para agregar una nueva relacion de seguidor
    private List<Gustar> listaLikesDePerfil;
    
    
    public cPerfil (){
        this.objPerfilAVer=new Perfil();
    }

    public Perfil getObjPerfilAVer() {
        return objPerfilAVer;
    }

    public void setObjPerfilAVer(Perfil objPerfilAVer) {
        this.objPerfilAVer = objPerfilAVer;
    }

    public int getCodPerfilPeticicion() {
        return codPerfilPeticicion;
    }

    public void setCodPerfilPeticicion(int codPerfilPeticicion) {
        this.codPerfilPeticicion = codPerfilPeticicion;
    }

   
    public void evaluarYCargarDatos(){
        System.out.println("Codigo de peticion");
        System.out.println(this.codPerfilPeticicion);
       if(this.codPerfilPeticicion!=-1){
           this.objPerfilAVer=new PerfilLogica().buscarPerfilPorCodigo(this.codPerfilPeticicion);
           if(this.objPerfilAVer!=null){
               this.objPerfilAVer.setListaAlbumes(new AlbumLogica().BuscarAlbumDetalladoUsuario(this.codPerfilPeticicion));
               this.objPerfilAVer.setListaPublicaciones(new PublicacionLogica().buscarPublicacionesDePerfil(this.codPerfilPeticicion));
               this.objPerfilAVer.setListaSeguidores(new SeguidorLogica().buscarSeguidoresDePerfil(this.codPerfilPeticicion));
               this.objPerfilAVer.setListaPerfilesSiguiendo(new SeguidorLogica().buscarAQuienSigueUnPerfil(this.codPerfilPeticicion));
               this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
           }else{
               new SessionLogica().redirigirA("/user/404.xhtml");
           }
           
       }else{
           new SessionLogica().redirigirA("/user/404.xhtml");
       }
    }
    
    
    public boolean verificarPropietarioDeUnPerfil (){
        if(this.codPerfilPeticicion==new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil()){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verificarRelacionSeguidor(){
        return new SeguidorLogica().verificarRelacionSeguidor(codPerfilPeticicion, new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
    }
    
    public void nuevoSeguidor(){
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        cNotificacion beanNot = (cNotificacion) facesContext.getApplication().
                getVariableResolver().resolveVariable(facesContext, "cNotificacion");
        
        if(new SeguidorLogica().nuevoSeguidor(this.codPerfilPeticicion, new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil())){
            
               this.objPerfilAVer.setListaSeguidores(new SeguidorLogica().buscarSeguidoresDePerfil(this.codPerfilPeticicion));
               beanNot.notificacionSeguidor(this.codPerfilPeticicion);
            Mensaje.js("Empezaste a seguir a un Fliver");
        }else{
            Mensaje.js("Ocurrio un error");
        }
    }
    
    public void eliminarSeguidor(){
        if(new SeguidorLogica().dejarDeSeguir(this.codPerfilPeticicion, new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil())){
            this.objPerfilAVer.setListaPerfilesSiguiendo(new SeguidorLogica().buscarAQuienSigueUnPerfil(this.codPerfilPeticicion));
            new NotificacionLogica().eliminarNotificacionSeguidor(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPerfilPeticicion);
            Mensaje.js("Dejaste de seguir a un Fliver");
        }else{
            Mensaje.js("Ocurrió un error");
        }
    }
    
    public boolean verificarLike(int codPublicacion){
        for (int i = 0; i < this.listaLikesDePerfil.size(); i++) {
             if(this.listaLikesDePerfil.get(i).getObjPublicacion().getCodPublicacion()==codPublicacion){
                 return true;
             }
        }
        
        return false;
    }
    
    public int posicionLike(int codPublicacion){
        for (int i = 0; i < this.listaLikesDePerfil.size(); i++) {
             if(this.listaLikesDePerfil.get(i).getObjPublicacion().getCodPublicacion()==codPublicacion){
                 return i;
             }
        }
        
        return -1;
    }
    
    public void actualizarLikes(){
        this.objPerfilAVer.setListaPublicaciones(new PublicacionLogica().buscarPublicacionesDePerfil(this.codPerfilPeticicion));
        this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
           
    }
    
    
    public void toggleLike(int codPublicacion){

        FacesContext facesContext = FacesContext.getCurrentInstance();
        cNotificacion beanNot = (cNotificacion) facesContext.getApplication().
                getVariableResolver().resolveVariable(facesContext, "cNotificacion");
    
        if(this.verificarLike(codPublicacion)){
            new GustarLogica().eliminarLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            this.actualizarLikes();
            
            new NotificacionLogica().eliminarNotificacionLike(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            Mensaje.js("Dejo de gustarte una publicacion");
            
           
        }else{
            new GustarLogica().nuevoLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            
            //Nueva notificacion
            this.actualizarLikes();
            beanNot.notificacionLike(codPublicacion);
            
            Mensaje.js("Te gusto una publicación");
        }
    }
    
    
}
