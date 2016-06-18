/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Perfil;
import com.entidad.Usuario;
import com.util.GeneradorNumeros;
import javax.swing.JOptionPane;

/**
 *
 * @author LAB03_06
 */
public class Prueba {
public static void ZonaLogicaR(){
    Perfil perf=new Perfil();
    perf.setNombrePer("Jorge Fernando");
    perf.setApellidosPer("Palacios Sanchez");
    Usuario us= new Usuario();
    us.setEmail("f.499@hotmail.es");
    us.setPassword("Contraseña temporal");
    us.setObjPerfil(perf);
    
    if(new UsuarioLogica().RegistroUsuario(us)){
        JOptionPane.showMessageDialog(null,"OK");
    }
}
public static void ZonaLogicaL(){
    {
//        JOptionPane.showMessageDialog
//        (null,new ZonaObservacionLogica().Listar().size());
    }
}

public static void main(String[] args) {
//  Prueba.ZonaLogicaR();
//    Prueba.ZonaLogicaL();

    System.out.println(new GeneradorNumeros().generar(String.valueOf(1)));
}
 
}
