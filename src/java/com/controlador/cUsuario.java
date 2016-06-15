/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.SessionLogica;
import com.bean.UsuarioLogica;
import com.entidad.Usuario;
import com.util.Mensaje;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author ferna
 */
public class cUsuario implements Serializable{
    private Usuario objAuthUsuario;
    private Usuario objUserPerfil;
    
    public cUsuario(){
        this.objAuthUsuario=new Usuario();
    }
    public void iniciarSesion (){
        
        Usuario obj=new Usuario();
        obj= new UsuarioLogica().IniciarSesion(objAuthUsuario);
       
        if(obj!=null){
            System.out.println(obj.getEmail());
            new SessionLogica().nuevaSesion(obj);
        }else{
            System.out.println("Error de inicio de sesion");
            Mensaje.msg("Error", "Usuario y/o contraseña incorrectos"); 
        }
        
    }
    
    
    public void buscarPerfilUsuario(){
        int codUsuario=new SessionLogica().obtenerUsuarioSession().getCodUsuario();
        System.out.println(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        this.objUserPerfil=new UsuarioLogica().BuscarPerfilUsuario(codUsuario);
    }
    
    
    
    public void cerrarSesion(){
        new SessionLogica().eliminarSesion();
    }
    
    public void esAutenticado(){
        new SessionLogica().esAutenticado();
    }

    public Usuario getObjAuthUsuario() {
        return objAuthUsuario;
    }

    public void setObjAuthUsuario(Usuario objAuthUsuario) {
        this.objAuthUsuario = objAuthUsuario;
    }

    public Usuario getObjUserPerfil() {
        return objUserPerfil;
    }

    public void setObjUserPerfil(Usuario objUserPerfil) {
        this.objUserPerfil = objUserPerfil;
    }
    
    
    
    
}
