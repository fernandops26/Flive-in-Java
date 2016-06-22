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
    
    
    
    public List<Album> BuscarAlbumDetalladoUsuario(Integer codPerfil){
        Connection con;
        CallableStatement cl;
        Album obj;
        List<Album> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_ALBUM.sp_buscarAlbumDetalladoUsuario(?,?)}");
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
                    obj.setDescripcionAlb(rs.getString("DESCRIPCION"));
                    obj.setF_creacionAlb(rs.getDate("F_CREACION"));
                    obj.setN_publicaciones(rs.getInt("NUM_PUBLIC"));
                    obj.getObjCategoria().setCodCategoria(rs.getInt("CODCATEGORIA"));
                    obj.getObjCategoria().setNombreCategoria(rs.getString("NOMBRECATE"));
                    obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
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
    
    public boolean eliminarAlbum(int codAlbum){
        Connection con;
        CallableStatement cl;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call sp_eliminarAlbum(?)}");
            cl.setInt(1, codAlbum);
            cl.executeQuery();
            cl.close();
            con.close();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
        
    }
    
    public boolean crearAlbum(Album objAlbum){
        Connection con;
        CallableStatement cl;
        Album obj;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call sp_NuevoAlbum(?,?,?,?)}");
            cl.setString(1, objAlbum.getNombreAlb());
            cl.setString(2, objAlbum.getDescripcionAlb());
            cl.setInt(3, objAlbum.getObjPerfil().getCodPerfil());
            cl.setInt(4, objAlbum.getObjCategoria().getCodCategoria());
            cl.execute();
            cl.close();
            con.close();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
    public boolean actualizarAlbum(Album objAlbum){
        Connection con;
        CallableStatement cl;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call sp_actualizarAlbum(?,?,?,?)}");
            cl.setInt(1, objAlbum.getCodAlbum());
            cl.setString(2, objAlbum.getNombreAlb());
            cl.setString(3, objAlbum.getDescripcionAlb());
            cl.setInt(4, objAlbum.getObjCategoria().getCodCategoria());
            cl.execute();
            cl.close();
            con.close();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    
     public Album buscarAlbDetPorCodigoAlbum(Integer codAlbum){
        Connection con;
        CallableStatement cl;
        Album obj=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_ALBUM.sp_buscarAlbDetPorCodigoAlbum(?,?)}");
            cl.setInt(1, codAlbum);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            if(rs.next()){
                   obj=new Album();
                    obj.setCodAlbum(rs.getInt("CODALBUM"));
                    obj.setNombreAlb(rs.getString("NOMBRE"));
                    obj.setDescripcionAlb(rs.getString("DESCRIPCION"));
                    obj.setF_creacionAlb(rs.getDate("F_CREACION"));
                    obj.setN_publicaciones(rs.getInt("NUM_PUBLIC"));
                    obj.getObjCategoria().setCodCategoria(rs.getInt("CODCATEGORIA"));
                    obj.getObjCategoria().setNombreCategoria(rs.getString("NOMBRECATE"));
                    obj.getObjPerfil().setCodPerfil(rs.getInt("CODPERFIL"));
            }
            rs.close();
            cl.close();
            con.close();
        
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return obj;
    }
    
}
