/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.DepartamentoLogica;
import com.bean.ImagenLogica;
import com.bean.PerfilLogica;
import com.bean.SessionLogica;
import com.bean.UsuarioLogica;
import com.entidad.Departamento;
import com.entidad.Imagen;
import com.entidad.Usuario;
import com.util.Mensaje;
import java.io.File;
import java.io.IOException;
import org.primefaces.event.FileUploadEvent;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ferna
 */
@ViewScoped
public class cUsuario implements Serializable{
    private Usuario objAuthUsuario;
    private Usuario objUserPerfil;
    private UploadedFile archivo;
    
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
    

    
    
    public void cargarFoto(){
       ImagenLogica imL=new ImagenLogica();
        if(!this.objUserPerfil.getObjPerfil().getImagenPer().equals("none.jpg")){
           imL.eliminarImagen();
        }
//        System.out.println(this.archivo.getFileName());
        imL.getObjImagen().setImagen(this.archivo);
        imL.getObjImagen().setNombreArchivo(this.objUserPerfil.getObjPerfil().getImagenPer());

        this.objUserPerfil.getObjPerfil().setImagenPer(imL.guardarImagen());
        Mensaje.msg("Actualización de Imagen", 
                "Realizada Correctamente");
    }
    
    public void doActualizarDatosPefil(){
        if(new PerfilLogica().actualizarDatosPerfil(this.objUserPerfil)){
            new SessionLogica().setUsuarioSession(this.objUserPerfil);
            Mensaje.msg("Actualización de Perfil", 
                "Realizada Correctamente");
            
        }else{
            Mensaje.msg("Actualización de Perfil", 
                "Ocurrió algún error");
        }
    }
    
    
    public void buscarPerfilUsuario(){
        int codUsuario=new SessionLogica().obtenerUsuarioSession().getCodUsuario();
        this.objUserPerfil=new UsuarioLogica().BuscarPerfilUsuario(codUsuario);
    }
    
    
    public List<Departamento> doCargarComboDepartamentos(){
        List<Departamento> d=new DepartamentoLogica().ListarDepartamentos();
        return d;
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

    public UploadedFile getArchivo() {
        return archivo;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

   
    
    
    
    
    
}
