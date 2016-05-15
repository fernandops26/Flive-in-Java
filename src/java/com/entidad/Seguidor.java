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
public class Seguidor implements Serializable{
    private int codRelacionSeguidor;
    private Perfil objPerfilSeguido;
    private Perfil objPerfilSeguidor;
    
    public Seguidor(){
        this.objPerfilSeguido=new Perfil();
        this.objPerfilSeguidor=new Perfil();
    }

    public int getCodRelacionSeguidor() {
        return codRelacionSeguidor;
    }

    public void setCodRelacionSeguidor(int codRelacionSeguidor) {
        this.codRelacionSeguidor = codRelacionSeguidor;
    }

    public Perfil getObjPerfilSeguido() {
        return objPerfilSeguido;
    }

    public void setObjPerfilSeguido(Perfil objPerfilSeguido) {
        this.objPerfilSeguido = objPerfilSeguido;
    }

    public Perfil getObjPerfilSeguidor() {
        return objPerfilSeguidor;
    }

    public void setObjPerfilSeguidor(Perfil objPerfilSeguidor) {
        this.objPerfilSeguidor = objPerfilSeguidor;
    }
    
    
}
