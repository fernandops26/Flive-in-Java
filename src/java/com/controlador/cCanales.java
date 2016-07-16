/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ferna
 */
@Named("cCanales")
@ManagedBean(name="cCanales",eager = true)
@ApplicationScoped
public class cCanales implements Serializable {
    
    //Objeto para mapear un valor por medio de una clave.
    private Map<String,String> canalesApp=null;
   
     @PostConstruct
    public void init() {
        canalesApp=new HashMap<String,String>();
    }
 
    
    
    public void agregarCanal(String codUsuario,String canal ){
        this.canalesApp.put(codUsuario, canal);
    
    }
    
    public String obtenerCanal(String codUsuario){
        return canalesApp.get(codUsuario);
    }
    
    public void eliminarCanal(String codUsuario){
        this.canalesApp.remove(codUsuario);
        
         System.out.println("Cantidad de canales: "+this.cantidadCanles());
    }
    public int cantidadCanles(){
        return this.canalesApp.size();
    }
    
    
    
}
