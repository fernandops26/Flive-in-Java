/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Usuario;
import com.util.ConectorBD;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author ferna
 */
public class PerfilLogica implements Serializable{
    
    public boolean actualizarImagenPerfil(Usuario objUsuario){
        Connection con;
        CallableStatement cl;
        try{
           con=new ConectorBD().conectar();
           cl=con.prepareCall("{call sp_actualizarImagenPerfil(?,?)}");
            cl.setInt(1, objUsuario.getObjPerfil().getCodPerfil());
            cl.setString(2, objUsuario.getObjPerfil().getImagenPer());

           cl.execute();
           cl.close();
           con.close();
        }catch(Exception e){
           System.out.print(e.getMessage()); 
           return false;
        }
        return true;
    }
    
    
    public boolean actualizarDatosPerfil(Usuario objUsuario){
        Connection con;
        CallableStatement cl;
      try{
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_actualizarDatosPerfil(?,?,?,?)}");
          cl.setInt(1, objUsuario.getObjPerfil().getCodPerfil());
         cl.setString(2, objUsuario.getObjPerfil().getNombrePer());
         cl.setString(3, objUsuario.getObjPerfil().getApellidosPer());
         cl.setInt(4, objUsuario.getObjPerfil().getObjDepartamento().getCodDepartamento());
        
         cl.execute();
         cl.close();
         con.close(); 
      }catch(Exception e){
         System.out.print(e.getMessage()); 
         return false;
      }
      return true;
    }
}
