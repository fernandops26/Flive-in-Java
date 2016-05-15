/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ferna
 */
public class Provincia implements Serializable{
    private int codProvincia;
    private String nombre_prov;
    private Departamento objDepartamento;
    private List<Provincia> listaProvincias;
    public Provincia(){
        
    }

    public List<Provincia> getListaProvincias() {
        return listaProvincias;
    }

    public void setListaProvincias(List<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNombre_prov() {
        return nombre_prov;
    }

    public void setNombre_prov(String nombre_prov) {
        this.nombre_prov = nombre_prov;
    }

    public Departamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
        this.objDepartamento = objDepartamento;
    }
    
    
} 
