/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Publicacion;
import com.util.ConectorBD;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 *
 * @author ferna
 */
public class PublicacionLogica implements Serializable{
    public PublicacionLogica(){
        
    }
    
    
    public boolean publicarPost(Publicacion objPublicacion){
        
        Connection con;
        CallableStatement cl;
      boolean estado=false;
      try{
          
         con=new ConectorBD().conectar();
         cl=con.prepareCall("{call sp_nuevaPublicacion(?,?,?,?)}");
          cl.setString(1, objPublicacion.getTituloPub());
         cl.setString(2, objPublicacion.getImagenPub());
         cl.setInt(3, objPublicacion.getObjAlbum().getCodAlbum());
         cl.setString(4, objPublicacion.getTagsPublicacion());
        
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
