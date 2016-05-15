/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidad;

import java.io.Serializable;

/**
 *
 * @author ferna
 */
public class Departamento implements Serializable {
    private int codDepartamento;
    private String nombre_dep;
    
    public Departamento(){
        
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNombre_dep() {
        return nombre_dep;
    }

    public void setNombre_dep(String nombre_dep) {
        this.nombre_dep = nombre_dep;
    }
    
    
}
