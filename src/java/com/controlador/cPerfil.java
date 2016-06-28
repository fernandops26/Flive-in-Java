/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.AlbumLogica;
import com.bean.GustarLogica;
import com.bean.PerfilLogica;
import com.bean.PublicacionLogica;
import com.bean.SessionLogica;
import com.entidad.Gustar;
import com.entidad.Perfil;
import com.util.Mensaje;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ferna
 */
public class cPerfil implements Serializable{
    private int codPerfilPeticicion=-1;
    private Perfil objPerfilAVer;
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
           System.out.println("Linea 48");
           this.objPerfilAVer=new PerfilLogica().buscarPerfilPorCodigo(this.codPerfilPeticicion);
           System.out.println("Linea 50");
           if(this.objPerfilAVer!=null){
               System.out.println("Linea 52");
               this.objPerfilAVer.setListaAlbumes(new AlbumLogica().BuscarAlbumDetalladoUsuario(this.codPerfilPeticicion));
               this.objPerfilAVer.setListaPublicaciones(new PublicacionLogica().buscarPublicacionesDePerfil(this.codPerfilPeticicion));
               this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(this.codPerfilPeticicion);
           }else{
               System.out.println("Linea 55");
               new SessionLogica().redirigirA("/user/404.xhtml");
           }
           
       }else{
           System.out.println("Linea 60");
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
        this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(this.codPerfilPeticicion);
           
    }
    
    
    public void toggleLike(int codPublicacion){
//        Mensaje.js("Diste un like a "+codPublicacion);
//        int pos=this.posicionLike(codPublicacion);
        if(this.verificarLike(codPublicacion)){
            new GustarLogica().eliminarLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            this.actualizarLikes();
            Mensaje.js("Dejo de gustarte una publicacion");
           
        }else{
            new GustarLogica().nuevoLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            this.actualizarLikes();
            Mensaje.js("Te gusto una publicación");
        }
    }
    
    
}
