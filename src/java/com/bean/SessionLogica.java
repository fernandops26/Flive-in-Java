/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author ferna
 */
public class SessionLogica implements Serializable {
    FacesContext contexto=FacesContext.getCurrentInstance();
    public void nuevaSesion(Usuario objUsuario){
        contexto.getExternalContext().getSessionMap().put("objUsuario", objUsuario);
        try {
            contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/user/home.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void eliminarSesion(){
        try {
            contexto.getExternalContext().getSessionMap().remove("objUsuario");
            contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/");
            System.out.println(contexto.getExternalContext().getApplicationContextPath());
        } catch (IOException ex) {
            Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void esAutenticado(){
        if(obtenerUsuarioSession() == null){
            try {
                contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/");
            } catch (IOException ex) {
                Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public Usuario obtenerUsuarioSession(){
        Usuario obj=new Usuario();
       obj=(Usuario) contexto.getExternalContext().getSessionMap().get("objUsuario");
       System.out.println(obj.getObjPerfil().getImagenPer());
       return obj;
    }
}
