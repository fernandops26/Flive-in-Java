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
    private Usuario objAuthUsuario;//Objeto usado en la autenticacion+
    private Usuario objRegUsuario;
    private Usuario objUserPerfil; //Objeto utilizado para las actualizaciones de Peril del usuario actual
    private UploadedFile archivo; //Objeto que recibe la imagen enviada por el fileUpload,usado en la actualizacion del perfil actual
    
    public cUsuario(){
        this.objAuthUsuario=new Usuario();
    }
    
    /**
     * Metodo que realiza un intento de inicio de session
     **/
    public void iniciarSesion (){
        
        Usuario obj=new Usuario();
        //Obtiene el objeto de una session exitosa o no
        obj= new UsuarioLogica().IniciarSesion(objAuthUsuario);
       
        //Evalua si el objeto no es nulo
        if(obj!=null){
            //Pasa el objeto para ser guardado en una variable de session
            new SessionLogica().nuevaSesion(obj);
        }else{
            //Mensaje erroneo
            System.out.println("Error de inicio de sesion");
            Mensaje.msg("Error", "Usuario y/o contraseña incorrectos"); 
        }
        
    }
    
    public void registrarUsuario(){
        Usuario obj;
        if(new UsuarioLogica().RegistroUsuario(this.objRegUsuario)){
            obj=new UsuarioLogica().IniciarSesion(this.objRegUsuario);
            
            if(obj!=null){
                new SessionLogica().nuevaSesion(obj);
            }
        }else{
            Mensaje.msg("Error de Registro", "Usuario ya existe");
        }
        
    }
    

    
    /**
     * Metodo encargado de gestionar la actualizacion de la foto de perfil del usuario actual
     **/
    public void cargarFoto(){
       ImagenLogica imL=new ImagenLogica();
        //Evalua si es la imagen de perfil por defecto(none.jpg) o es una personalizada
        if(!this.objUserPerfil.getObjPerfil().getImagenPer().equals("none.jpg")){
            //Ingresa el nombre de la imagen al objeto de la logica de imagen
            imL.getObjImagen().setNombreArchivo(this.objUserPerfil.getObjPerfil().getImagenPer());
            //Ejecuta la eliminacion de la imagen antigua
           imL.eliminarImagen();
        }
//        System.out.println(this.archivo.getFileName());
        
        //Pasa el archivo del fileUpload al objeto de la logica
        imL.getObjImagen().setImagen(this.archivo);
        
        //Ingresa el nombre de la imagen de perfil de usuario al objeto de la logica
        imL.getObjImagen().setNombreArchivo(this.objUserPerfil.getObjPerfil().getImagenPer());

        //Ejecuta el guardado de la imagen y almacena el nombre resultante en el objeto usado en el formulario del perfil
        this.objUserPerfil.getObjPerfil().setImagenPer(imL.guardarImagen());
        //Mensaje satisfactorio
        Mensaje.msg("Actualización de Imagen", 
                "Realizada Correctamente");
    }
    
    /**
     * Metodo encargado de actualizar los datos del perfil del usuario actual
     **/
    public void doActualizarDatosPefil(){
        //Pasa el objeto que se utiliza para actualizar el perfil actual
        if(new PerfilLogica().actualizarDatosPerfil(this.objUserPerfil)){
            //Envia el usuario para ser almacenado en una variable de session
            new SessionLogica().setUsuarioSession(this.objUserPerfil);
            
            //Mensaje satisfactorio
            Mensaje.msg("Actualización de Perfil", 
                "Realizada Correctamente");
            
        }else{
            //Mensaje erroneo
            Mensaje.msg("Actualización de Perfil", 
                "Ocurrió algún error");
        }
    }
    
    /**
     * Metodo que busca el Perfil del usuario actual 
     **/
    public void buscarPerfilUsuario(){
        int codUsuario=new SessionLogica().obtenerUsuarioSession().getCodUsuario();
        this.objUserPerfil=new UsuarioLogica().BuscarPerfilUsuario(codUsuario);
    }
    
    /**
     * Metodo que carga los departamentos existentes, usado en el formulario del perfil
     * @return La lista de departamentos existentes en la BD
     **/
    public List<Departamento> doCargarComboDepartamentos(){
        List<Departamento> d=new DepartamentoLogica().ListarDepartamentos();
        return d;
    }
    
    
    /**
     * Metodo encargado de cerrar la session del usuario
     **/
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

    public Usuario getObjRegUsuario() {
        return objRegUsuario;
    }

    public void setObjRegUsuario(Usuario objRegUsuario) {
        this.objRegUsuario = objRegUsuario;
    }

    
    
   
    
    
    
    
    
}
