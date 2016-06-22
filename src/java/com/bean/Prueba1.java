/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Usuario;
import com.util.Utiles;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB03_06
 */
public class Prueba1 {

public static void UsuarioL(){
    {
        Usuario ob=new Usuario();
                ob.setEmail("f.499@hotmail.com");
                ob.setPassword("1234");
                Usuario a;
//boolean a=false;
                a=new UsuarioLogica().IniciarSesion(ob);
//                System.out.println(a);
         System.out.println(a.getEmail());
         System.out.println(a.getObjPerfil().getApellidosPer());
         System.out.println(a.getObjPerfil().getNombrePer());
    }
}



public static void main(String[] args) {
 


}
 
}
