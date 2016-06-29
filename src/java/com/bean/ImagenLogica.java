/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Imagen;
import com.entidad.Usuario;
import com.util.Utiles;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author ferna
 */
public class ImagenLogica implements Serializable {
    
    FacesContext contexto=FacesContext.getCurrentInstance();//Objeto usado para la interaccion con el "exterior de la aplicacion"(PATH,HTTP,ETC).
    private Imagen objImagen;//Objeto usado para almacenar los datos de la imagen
    ServletContext servletContext; //Objeto usado como puente con el contexto exterior
    private String rutaImgPerfil;//Ruta de almacenamiento de la imagen del perfil
    private String rutaImgPublicacion;//Ruta de almacenamiento de la imagen de una publicacion
    
    //Constructor
    public ImagenLogica(){
        this.objImagen=new Imagen();
        //Creacion de rutas
        this.creaRutaImgPerfil();
        this.crearRutaImgPublicacion();
        
    }
    
    /**
     * Metodo encargado de la creacion de la ruta de imagen del perfil de usuario actual
     **/
    public void creaRutaImgPerfil(){
        servletContext=(ServletContext) contexto.getExternalContext().getContext();
        String ruta="";
        //Ruta real hasta la carpeta de uploads
        ruta=servletContext.getRealPath("/upload/");
        
        //Concatenamiento de directorios internos
        ruta+=File.separatorChar+"img"+File.separatorChar+"users"+File.separatorChar;
        
        //Asignacion de la ruta creada
        this.rutaImgPerfil=ruta;
    }
    
    /**
     * Metodo encargado de la creacion de la ruta de la imagen de una determinada publicacion
     **/
    public void crearRutaImgPublicacion(){
        
        servletContext=(ServletContext) contexto.getExternalContext().getContext();
        String ruta="";
        //Ruta real hasta la carpeta uploads
        ruta=servletContext.getRealPath("/upload/");
        //Obtener el codigo del usuario de la sesion actual
        //este es utilizado para ubicar la carpeta que le eprtenece
        String codUsuario=String.valueOf(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        //Concatenamiento de directorios internos
        ruta+=File.separatorChar+"img"+File.separatorChar+"post"+File.separatorChar+codUsuario+File.separatorChar+"all"+File.separatorChar;
        //Asignacion de la ruta creada
        this.rutaImgPublicacion=ruta;
    }
    
    /**
     * Metodo encargado de guardar la imagen perteneciente a una determinada publicacion
     * @return El nombre de la imagen que ha sido guardada en el servidor
     **/
    public String guardarImgPost(){
        
        File archivo=null;//Objeto para el manejo de os archivos
        InputStream in =null;//Objeto para el manejo del stream de datos del archivo
        //Se obtiene el codigo de usuario de la sesion actual
        String codUsuario=codUsuario=String.valueOf(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        //Se obtiene un codigo producto de la combinacion del codigo del usuario y de un numero aleatorio
        String codGenerado=new Utiles().generar(codUsuario);
        String extension="";
        int i=0;
        //Extension del archivo ha subir
        extension="."+FilenameUtils.getExtension(this.getObjImagen().getImagen().getFileName());
        
       
        try {
            //Pasa el buffer de datos en un array de bytes , finalmente lee cada uno de los bytes
            in=this.getObjImagen().getImagen().getInputstream();
            byte[] data=new byte[in.available()];
            in.read(data);
            
            //Crea un archivo en la ruta de publicacion
            archivo=new File(this.rutaImgPublicacion+codGenerado+extension);
            FileOutputStream out=new FileOutputStream(archivo);
            //Escribe los datos en el nuevo archivo creado
            out.write(data);
            
            System.out.println("Ruta de Path Absolute");
            System.out.println(archivo.getAbsolutePath());
            
            //Cierra todas las conexiones
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
           return "none.jpg";
        }
        //Retorna el nombre de la iamgen almacenada
        return codGenerado+extension;
    }
    
    public String guardarImagen(){

        File archivo=null;
        InputStream in =null;
        String codUsuario="";
        String extension="";
        int i=0;
        
        extension="."+FilenameUtils.getExtension(this.getObjImagen().getImagen().getFileName());
        codUsuario=String.valueOf(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        
       
        try {
            in=this.getObjImagen().getImagen().getInputstream();
            byte[] data=new byte[in.available()];
            in.read(data);
            archivo=new File(this.rutaImgPerfil+codUsuario+extension);
            FileOutputStream out=new FileOutputStream(archivo);
            
            out.write(data);
            System.out.println("Ruta de Path Absolute");
            System.out.println(archivo.getAbsolutePath());
            
            in.close();
            out.flush();
            out.close();
            
            
            
            Usuario obj=new SessionLogica().obtenerUsuarioSession();
            obj.getObjPerfil().setImagenPer(codUsuario+extension);
            new SessionLogica().setUsuarioSession(obj);
            
            new PerfilLogica().actualizarImagenPerfil(obj);
            
            
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
           return "none.jpg";
        }
        return codUsuario+extension;
    }
    
    /**
     * Metodo encargado de eliminar una imagen del sevidor
     * @return True si se elimino, False si se produjo algún error
     **/
    public boolean eliminarImagen(){
        File f=new File(this.rutaImgPerfil+this.objImagen.getNombreArchivo());
        if(f.exists()){
            if(f.delete()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
        
    }
    
    public boolean eliminarImagenesDePost(){
       File f=new File(this.rutaImgPublicacion+ this.objImagen.getNombreArchivo());
       if(f.exists()){
            if(f.delete()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    
    

    public Imagen getObjImagen() {
        return objImagen;
    }

    public void setObjImagen(Imagen objImagen) {
        this.objImagen = objImagen;
    }

  
    
    
    
}
