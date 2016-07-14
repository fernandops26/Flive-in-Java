/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Perfil;
import com.entidad.Publicacion;
import com.entidad.Usuario;
import com.util.ConectorBD;
import com.util.Utiles;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

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
         cl=con.prepareCall("{call sp_actualizarDatosPerfil(?,?,?,?,?)}");
          cl.setInt(1, objUsuario.getObjPerfil().getCodPerfil());
         cl.setString(2, objUsuario.getObjPerfil().getNombrePer());
         cl.setString(3, objUsuario.getObjPerfil().getApellidosPer());
         cl.setInt(4, objUsuario.getObjPerfil().getObjDepartamento().getCodDepartamento());
         cl.setString(5, objUsuario.getObjPerfil().getDescripcionPer());
         cl.execute();
         cl.close();
         con.close(); 
      }catch(Exception e){
         System.out.print(e.getMessage()); 
         return false;
      }
      return true;
    }
    
    
    public Perfil buscarPerfilPorCodigo(int codPerfil){
         Connection con;
        CallableStatement cl;
        Perfil obj=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_PERFIL.sp_buscarPerfilPorCodigo(?,?)}");
            cl.setInt(1, codPerfil);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            if(rs.next()){
                    obj=new Perfil();
                    obj.setCodPerfil(rs.getInt("CODPERFIL"));
                    obj.setNombrePer(rs.getString("NOMBRES"));
                    obj.setApellidosPer(rs.getString("APELLIDOS"));
                    obj.setImagenPer(rs.getString("IMAGEN"));
                    obj.setF_creacionPer(rs.getDate("F_CREACION"));
                    obj.setDescripcionPer(rs.getString("DESCRIPCIONPER"));
                    obj.getObjDepartamento().setCodDepartamento(rs.getInt("CODDEPARTAMENTO"));
                    obj.getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEP"));
                       return obj;
            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
    
    
    public List<Perfil> buscarTodosPerfiles(){
         Connection con;
        CallableStatement cl;
        Perfil obj=null;
        List<Perfil> lista=new ArrayList<>();
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_PERFIL.sp_buscarTodosPerfiles(?)}");
            cl.registerOutParameter(1, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(1);
            if(rs.next()){
                
                do{
                    obj=new Perfil();
                    obj.setCodPerfil(rs.getInt("CODPERFIL"));
                    obj.setNombrePer(rs.getString("NOMBRES"));
                    obj.setApellidosPer(rs.getString("APELLIDOS"));
                    obj.setImagenPer(rs.getString("IMAGEN"));
                    obj.setF_creacionPer(rs.getDate("F_CREACION"));
                    obj.setDescripcionPer(rs.getString("DESCRIPCIONPER"));
                    obj.getObjDepartamento().setCodDepartamento(rs.getInt("CODDEPARTAMENTO"));
                    obj.getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEP"));
                    lista.add(obj);
                    obj=null;
                }while(rs.next());
            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Perfiles encontados: "+lista.size());
        return lista;
    }
}
