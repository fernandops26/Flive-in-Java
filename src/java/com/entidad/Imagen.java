/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ferna
 */
public class Imagen implements Serializable{
    private UploadedFile imagen;
    private String rutaTemporal;
    private String rutaFinal;
    private String nombreArchivo;
    
    public Imagen(){
        
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getRutaTemporal() {
        return rutaTemporal;
    }

    public void setRutaTemporal(String rutaTemporal) {
        this.rutaTemporal = rutaTemporal;
    }

    public String getRutaFinal() {
        return rutaFinal;
    }

    public void setRutaFinal(String rutaFinal) {
        this.rutaFinal = rutaFinal;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    
    
    
}
