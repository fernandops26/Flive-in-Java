/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Imagen;
import com.entidad.Usuario;
import com.util.GeneradorNumeros;
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
    FacesContext contexto=FacesContext.getCurrentInstance();
    private Imagen objImagen;
    ServletContext servletContext;
    private String rutaImgPerfil;
    private String rutaImgPublicacion;
    
    
    public ImagenLogica(){
        this.objImagen=new Imagen();
        this.creaRutaImgPerfil();
        this.crearRutaImgPublicacion();
        
    }
    
    public void creaRutaImgPerfil(){
        servletContext=(ServletContext) contexto.getExternalContext().getContext();
        String ruta="";
        ruta=servletContext.getRealPath("/upload/");
        
        //Ruta de archivos temporales
//        ruta+=File.separatorChar+"resources"+File.separatorChar+"img"+
//                File.separatorChar+"temp"+File.separatorChar+"users"+File.separatorChar;

        //Ruta de imagenes de perfiles de usuarios
        ruta+=File.separatorChar+"img"+File.separatorChar+"users"+File.separatorChar;
        
        
        this.rutaImgPerfil=ruta;
    }
    
    public void crearRutaImgPublicacion(){
        servletContext=(ServletContext) contexto.getExternalContext().getContext();
        String ruta="";
        ruta=servletContext.getRealPath("/upload/");
        String codUsuario=String.valueOf(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        ruta+=File.separatorChar+"img"+File.separatorChar+"post"+File.separatorChar+codUsuario+File.separatorChar+"all"+File.separatorChar;
        this.rutaImgPublicacion=ruta;
    }
    
    public String guardarImgPost(){
        
        File archivo=null;
        InputStream in =null;
        String codUsuario=codUsuario=String.valueOf(new SessionLogica().obtenerUsuarioSession().getCodUsuario());
        String codGenerado=new GeneradorNumeros().generar(codUsuario);
        String extension="";
        int i=0;
        
        extension="."+FilenameUtils.getExtension(this.getObjImagen().getImagen().getFileName());
        
       
        try {
            in=this.getObjImagen().getImagen().getInputstream();
            byte[] data=new byte[in.available()];
            in.read(data);
            archivo=new File(this.rutaImgPublicacion+codGenerado+extension);
            FileOutputStream out=new FileOutputStream(archivo);
            
            out.write(data);
            System.out.println("Ruta de Path Absolute");
            System.out.println(archivo.getAbsolutePath());
            
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
           return "none.jpg";
        }
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
    
    public boolean eliminarImagen(){
        File f=new File(this.rutaImgPerfil+this.objImagen.getNombreArchivo());
        if(f.delete()){
            return true;
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
