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
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author ferna
 */
public class UsuarioLogica implements Serializable{
    
    
    public boolean RegistroUsuario(Usuario objUsuario){
        Connection con;
        CallableStatement cl;
      boolean estado=false;
      try{
          estado=this.BuscarUsuario(objUsuario);
          if(estado){
              return false;
          }
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_registrarUsuario(?,?,?,?)}");
          cl.setString(1, objUsuario.getEmail());
         cl.setString(2, objUsuario.getPassword());
         cl.setString(3, objUsuario.getObjPerfil().getNombrePer());
         cl.setString(4, objUsuario.getObjPerfil().getApellidosPer());
        
         cl.execute();
         cl.close();
         con.close();
         estado= true;  
      }catch(Exception e){
         System.out.print(e.getMessage()); 
      }
      return estado;
    }
    
    
    public Usuario IniciarSesion(Usuario objUsuario){
        Connection con;
        CallableStatement cl;
        Usuario obj=null;
        boolean estado=false;
        
        try{
            estado=this.BuscarUsuario(objUsuario);
            if(estado){
                con=new ConectorBD().conectar();
                cl=con.prepareCall("{call PAQ_USER.sp_loguearUsuario(?,?,?)}");
                cl.setString(1, objUsuario.getEmail());
                cl.setString(2, objUsuario.getPassword());
                cl.registerOutParameter(3,OracleTypes.CURSOR);
                cl.executeQuery();
                ResultSet rs=(ResultSet) cl.getObject(3);
                if(rs.next()){
                    obj=new Usuario();
                    obj.setCodUsuario(rs.getInt("CODUSUARIO"));
                    obj.setEmail(rs.getString("EMAIL"));
//                    obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
                    obj.getObjPerfil().setNombrePer(rs.getString("NOMBRES"));
                    obj.getObjPerfil().setApellidosPer(rs.getString("APELLIDOS"));
                    obj.getObjPerfil().setImagenPer(rs.getString("IMAGEN"));   
                    return obj;
                }
                
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }
    
    
    public boolean BuscarUsuario(Usuario objUsuario){
         Connection con;
        CallableStatement cl;
        int id=0;
        boolean estado=false;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_USER.sp_buscarUsuario(?,?,?)}");
            cl.setString(1, objUsuario.getEmail());
            cl.setString(2, objUsuario.getPassword());
            cl.registerOutParameter(3, OracleTypes.NUMBER);
            cl.executeQuery();
            id=cl.getInt(3);
            if(id!=0){
                return true;
            }
      
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
        
    }
    
    
    public Usuario BuscarPerfilUsuario(int codUsuario){
        Connection con;
        CallableStatement cl;
        Usuario obj;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_USER.sp_buscarPerfilUsuario(?,?)}");
            cl.setInt(1, codUsuario);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            if(rs.next()){
                    obj=new Usuario();
                    obj.setCodUsuario(rs.getInt("CODUSUARIO"));
                    obj.setEmail(rs.getString("EMAIL"));
                    obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
                    obj.getObjPerfil().setNombrePer(rs.getString("NOMBRES"));
                    obj.getObjPerfil().setApellidosPer(rs.getString("APELLIDOS"));
                    obj.getObjPerfil().setImagenPer(rs.getString("IMAGEN"));
                    obj.getObjPerfil().setF_creacionPer(rs.getDate("F_CREACION"));
                    return obj;
                }
      
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}








