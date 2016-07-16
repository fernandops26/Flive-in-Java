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
    
    FacesContext contexto=FacesContext.getCurrentInstance();//Objeto usado para la comunicación con el exterior de la aplicacion(PATH,HTTP,ETC).
    
    /**
     * Metodo para crear una nueva session del usuario
     * @param objUsuario Objeto tipo usuario para ser almacenado
     **/
    public void nuevaSesion(Usuario objUsuario){
        //Mapea las variables de session y crea una nueva del usuario indicando clave,valor.
        contexto.getExternalContext().getSessionMap().put("objUsuario", objUsuario);
        try {
            //Redirecciona a la pagina principal del usuario autenticado 
            contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/user/home.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo para eliminar la session una vez que el usuario cierre la sesion
     **/
    public void eliminarSesion(){
        try {
            //Mapea las variables de session y eliminar la del usuario
            contexto.getExternalContext().getSessionMap().remove("objUsuario");
            //Redirecciona a la pagina de inicio de un usuario no autenticado
            contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/");
            
        } catch (IOException ex) {
            Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que evalua si un usuario esta autenticado , cuando intenta acceder a una pagina
     **/
    public void esAutenticado(){
        //Evalua si existe una variable de session del usuario actual
        if(obtenerUsuarioSession() == null){
            try {
                //Redirecciona a la pagina principal de un usuario no autenticado
                contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+"/");
            } catch (IOException ex) {
                Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * Metodo que retorna el usuario de la sessin actual
     * @return Objeto tipo usuario lleno con los datos actuales del usuario actual
     **/
    public Usuario obtenerUsuarioSession(){
        //Mapea un objeto de session del usuario
        Usuario obj=(Usuario) contexto.getExternalContext().getSessionMap().get("objUsuario");
       return obj;
    }
    
    /**
     * Metodo para reemplazar el variable de session del usuario actual
     * @param objUsuario Objeto tipo usuario con datos del usuario actual
     **/
    public void setUsuarioSession(Usuario objUsuario){
        //Mapea las variables de sesion y reemplaza el valor de la clave del usuario actual
        contexto.getExternalContext().getSessionMap().put("objUsuario",objUsuario);
    }
    
    /**
     * Metodo para redigir al usuario a una determinada ruta
     * @param ruta Ruta usada para redirigir
     **/
    public void redirigirA(String ruta){
        try {
            contexto.getExternalContext().redirect(contexto.getExternalContext().getApplicationContextPath()+ruta);
        } catch (IOException ex) {
            Logger.getLogger(SessionLogica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void nuevoClaveValor(String clave,String valor){
        contexto.getExternalContext().getSessionMap().put(clave, valor);
    }
    
    public Object extraerValorDeClave(String clave){
        return contexto.getExternalContext().getSessionMap().get(clave);
    }
    
    public void eliminarValorDeClave(String clave){
        contexto.getExternalContext().getSessionMap().remove(clave);
    }
}
