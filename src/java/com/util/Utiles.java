/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author ferna
 */
public class Utiles implements Serializable{
     FacesContext contexto=FacesContext.getCurrentInstance();
     ServletContext servletContext;
     
    public String generar(String codUsuario){
        int cod=(int)(Math.random()*999999)+1;
        return codUsuario+"-"+String.valueOf(cod);
    }
    
    public String[] SepararTags(String cadena){
         return cadena.split(" ");
    }
    
    
    public boolean crearRutasPublicacionesUsuarioNuevo(int codUNuevo){
        servletContext=(ServletContext) contexto.getExternalContext().getContext();
        String ruta="";
        //Ruta real hasta la carpeta uploads
        ruta=servletContext.getRealPath("/upload/");
        //Obtener el codigo del usuario de la sesion actual
        //este es utilizado para ubicar la carpeta que le pertenece
        String codUsuario=String.valueOf(codUNuevo);
        //Concatenamiento de directorios internos
        ruta+=File.separatorChar+"img"+File.separatorChar+"post"+File.separatorChar+codUsuario+File.separatorChar+"all";
        System.out.println(ruta);
        File carpetas=new File(ruta);
        return carpetas.mkdirs();
        
    }
   
}
