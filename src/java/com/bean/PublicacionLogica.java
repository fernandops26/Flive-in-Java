/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Departamento;
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
    
    public List<Publicacion> buscarPublicacionesDeAlbum(int codAlbum){
         Connection con;
        CallableStatement cl;
        Publicacion obj;
        List<Publicacion> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_PUBLIC.sp_buscarPublicacionesDeAlbum(?,?)}");
            cl.setInt(1, codAlbum);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                   String[] arrTags;
                   obj=new Publicacion();
                    obj.setCodPublicacion(rs.getInt("CODPUBLICACION"));
                    obj.setTituloPub(rs.getString("TITULO"));
                    obj.setImagenPub(rs.getString("IMAGEN"));
                    obj.setF_creacionPub(rs.getDate("F_CREACION"));
                    obj.setN_likesPub(rs.getInt("N_LIKES"));
                    obj.getObjAlbum().setCodAlbum(rs.getInt("CODALBUM"));
                    obj.setTagsPublicacion(rs.getString("TAGS"));
                    
                    arrTags=new Utiles().SepararTags(obj.getTagsPublicacion());
                    obj.setArrTags(arrTags);
                    arrTags=null;
                   
                    arr.add(obj);
                    obj=null;
                }while(rs.next());

            }
            rs.close();
            cl.close();
            con.close();
            System.out.println(arr.size()+"-----");
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
}
