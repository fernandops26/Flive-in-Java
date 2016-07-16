/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Notificacion;
import com.entidad.Usuario;
import com.util.ConectorBD;
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
public class NotificacionLogica implements Serializable{
    
    public boolean notificacionSeguidor(Notificacion objNotificacion){
        Connection con;
        CallableStatement cl;
      try{
         
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_notificacionSeguidor(?,?,?)}");
          cl.setInt(1, objNotificacion.getObjPerfilOrigen().getCodPerfil());
         cl.setInt(2, objNotificacion.getObjPerfil().getCodPerfil());
         cl.setInt(3, 0);
         cl.execute();
         cl.close();
         con.close();
      }catch(Exception e){
         System.out.print(e.getMessage()); 
         return false;
      }
      return true;
    }
    
    
    public boolean notificacionLike(Notificacion objNotificacion){
        Connection con;
        CallableStatement cl;
      try{
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_notificacionLike(?,?,?)}");
          cl.setInt(1, objNotificacion.getObjPerfilOrigen().getCodPerfil());
         cl.setInt(2, objNotificacion.getObjPublicacion().getCodPublicacion());
         cl.setInt(3, 0);
         cl.execute();
         cl.close();
         con.close();
      }catch(Exception e){
         System.out.print(e.getMessage()); 
         return false;
      }
      return true;
    }
    
    
    
    
    public List<Notificacion> listarNotificacionesDePerfil(int codPerfil){
        Connection con;
        CallableStatement cl;
        Notificacion obj;
        List<Notificacion> lista=new ArrayList<>();
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_NOTIF.sp_listarNotificacionesPerfil(?,?)}");
            cl.setInt(1, codPerfil);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            if(rs.next()){
                    do{
                        obj=new Notificacion();
                        obj.setCodNotificacion(rs.getInt("CODNOTIFICACION"));
                        obj.getObjPublicacion().setCodPublicacion(rs.getInt("CODPUBLICACION"));
                        obj.getObjPublicacion().setTituloPub(rs.getString("TITULO"));
                        obj.getObjPublicacion().setImagenPub(rs.getString("IMAGENPUB"));
                        obj.getObjPerfilOrigen().setCodPerfil(rs.getInt("CODPERFIL_ORIGEN"));
                        obj.getObjPerfilOrigen().setNombrePer(rs.getString("NOMBRES_ORIGEN"));
                        obj.getObjPerfilOrigen().setImagenPer(rs.getString("IMAGEN_ORIGEN"));
                        obj.getObjPerfilOrigen().setApellidosPer(rs.getString("APELLIDOS_ORIGEN"));
                        obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
                        obj.getObjPerfil().setNombrePer(rs.getString("NOMBRES"));
                        obj.getObjPerfil().setApellidosPer(rs.getString("APELLIDOS"));
                        obj.getObjPerfil().setImagenPer(rs.getString("IMAGEN"));
                        obj.setLeidoNot(rs.getInt("LEIDO"));
                        obj.setFecha(new java.util.Date(rs.getTimestamp("fecha").getTime()));
                        obj.getObjTipoNotificacion().setCodTipoNotificacion(rs.getInt("CODTIPONOTIFICACION"));

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
        return lista;
    }
    
    
    public boolean eliminarNotificacionLike(int codPerfilOrigen,int codPublicacion){
        Connection con;
        CallableStatement cl;
      try{
         
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_eliminarNotificacionLike(?,?)}");
          cl.setInt(1, codPerfilOrigen);
         cl.setInt(2, codPublicacion);
         cl.execute();
         cl.close();
         con.close();
      }catch(Exception e){
         System.out.print(e.getMessage()); 
         return false;
      }
      return true;
    }
    
    
    public boolean eliminarNotificacionSeguidor(int codPerfilOrigen,int codPerfil){
        Connection con;
        CallableStatement cl;
      try{
         
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_eliminarNotifSeguidor(?,?)}");
          cl.setInt(1, codPerfilOrigen);
         cl.setInt(2, codPerfil);
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
