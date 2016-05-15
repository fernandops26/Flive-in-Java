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
public class Usuario implements Serializable{
    private int codUsuario;
    private String email;
    private String password;
    private Perfil objPerfil;
    
    public Usuario(){
        
    }

    public Perfil getObjPerfil() {
        return objPerfil;
    }

    public void setObjPerfil(Perfil objPerfil) {
        this.objPerfil = objPerfil;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
