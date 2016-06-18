/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Album;
import com.entidad.Departamento;
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
public class AlbumLogica implements Serializable {
    
   
    public List<Album> BuscarAlbumUsuario(Integer codPerfil){
        Connection con;
        CallableStatement cl;
        Album obj;
        List<Album> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_ALBUM.sp_buscarAlbumUsuario(?,?)}");
            cl.setInt(1, codPerfil);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                   obj=new Album();
                    obj.setCodAlbum(rs.getInt("CODALBUM"));
                    obj.setNombreAlb(rs.getString("NOMBRE"));
                    arr.add(obj);
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
    
}
