/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Gustar;
import com.entidad.Publicacion;
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
public class GustarLogica implements Serializable {
    
    public GustarLogica(){
        
    }
    
    public List<Gustar> buscarLikesPorPerfil(int codPerfil){
         Connection con;
        CallableStatement cl;
        Gustar obj;
        List<Gustar> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_GUSTAR.sp_buscarLikesPorPerfil(?,?)}");
            cl.setInt(1, codPerfil);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                   obj=new Gustar();
                    obj.setCodGustar(rs.getInt("CODGUSTAR"));
                    obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
                    obj.getObjPublicacion().setCodPublicacion(rs.getInt("CODPUBLICACION"));
                    
                    arr.add(obj);
                    obj=null;
                }while(rs.next());

            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
    
    public boolean nuevoLikePublicacion(int codPerfil, int codPublicacion){
         Connection con;
        CallableStatement cl;
      try{
         
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_nuevoLikePublicacion(?,?)}");
          cl.setInt(1, codPerfil);
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
    
    
    public boolean eliminarLikePublicacion(int codPerfil, int codPublicacion){
         Connection con;
        CallableStatement cl;
      try{
         
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_eliminarLikePublicacion(?,?)}");
          cl.setInt(1, codPerfil);
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
    
}
