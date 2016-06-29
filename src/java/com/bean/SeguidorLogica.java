/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Perfil;
import com.entidad.Seguidor;
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
public class SeguidorLogica implements Serializable {
    
    public SeguidorLogica(){
        
    }
    
    public boolean verificarRelacionSeguidor(int codPSeguido, int codPSeguidor){
        Connection con;
        CallableStatement cl;
        Perfil obj=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_SEGUI.sp_verificarRelacionSeguidor(?,?,?)}");
            cl.setInt(1, codPSeguido);
            cl.setInt(2, codPSeguidor);
            cl.registerOutParameter(3, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(3);
            if(rs.next()){
                return true;
            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    
    
    public boolean nuevoSeguidor(int codPerfilSeguido,int codPerfilSeguidor){
        Connection con;
        CallableStatement cl;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call sp_nuevoSeguidor(?,?)}");
            cl.setInt(1, codPerfilSeguido);
            cl.setInt(2, codPerfilSeguidor);
            cl.execute();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean dejarDeSeguir(int codPerfilSeguido,int codPerfilSeguidor){
        Connection con;
        CallableStatement cl;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call sp_dejarDeSeguir(?,?)}");
            cl.setInt(1, codPerfilSeguido);
            cl.setInt(2, codPerfilSeguidor);
            cl.execute();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public List<Seguidor> buscarSeguidoresDePerfil(int codPerfilSeguido){
        Connection con;
        CallableStatement cl;
        Seguidor obj=null;
        List<Seguidor> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_SEGUI.sp_buscarSeguidoresDePerfil(?,?)}");
            cl.setInt(1, codPerfilSeguido);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                    obj=new Seguidor();
                    obj.setCodRelacionSeguidor(rs.getInt("CODRELACIONSEGUIDOR"));
                    obj.getObjPerfilSeguido().setCodPerfil(rs.getInt("CODPERFILS"));
                    obj.getObjPerfilSeguido().setNombrePer(rs.getString("NOMBRESS"));
                    obj.getObjPerfilSeguido().setApellidosPer(rs.getString("APELLIDOSS"));
                    obj.getObjPerfilSeguido().setImagenPer(rs.getString("IMAGENS"));
                    obj.getObjPerfilSeguido().getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEPS"));
                    obj.getObjPerfilSeguidor().setCodPerfil(rs.getInt("CODPERFILSR"));
                    obj.getObjPerfilSeguidor().setNombrePer(rs.getString("NOMBRESSR"));
                    obj.getObjPerfilSeguidor().setApellidosPer(rs.getString("APELLIDOSSR"));
                    obj.getObjPerfilSeguidor().setImagenPer(rs.getString("IMAGENSR"));
                    obj.getObjPerfilSeguidor().getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEPSR"));
                    
                    arr.add(obj);
                    obj=null;
                }while(rs.next());
            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return arr;
    }
    
    
    
    public List<Seguidor> buscarAQuienSigueUnPerfil(int codPerfilSeguidor){
        Connection con;
        CallableStatement cl;
        Seguidor obj=null;
        List<Seguidor> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_SEGUI.sp_buscarAQuienSigueUnPerfil(?,?)}");
            cl.setInt(1, codPerfilSeguidor);
            cl.registerOutParameter(2, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(2);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                    obj=new Seguidor();
                    obj.setCodRelacionSeguidor(rs.getInt("CODRELACIONSEGUIDOR"));
                    obj.getObjPerfilSeguido().setCodPerfil(rs.getInt("CODPERFILS"));
                    obj.getObjPerfilSeguido().setNombrePer(rs.getString("NOMBRESS"));
                    obj.getObjPerfilSeguido().setApellidosPer(rs.getString("APELLIDOSS"));
                    obj.getObjPerfilSeguido().setImagenPer(rs.getString("IMAGENS"));
                    obj.getObjPerfilSeguido().getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEPS"));
                    obj.getObjPerfilSeguidor().setCodPerfil(rs.getInt("CODPERFILSR"));
                    obj.getObjPerfilSeguidor().setNombrePer(rs.getString("NOMBRESSR"));
                    obj.getObjPerfilSeguidor().setApellidosPer(rs.getString("APELLIDOSSR"));
                    obj.getObjPerfilSeguidor().setImagenPer(rs.getString("IMAGENSR"));
                    obj.getObjPerfilSeguidor().getObjDepartamento().setNombre_dep(rs.getString("NOMBREDEPSR"));
                    
                    arr.add(obj);
                    obj=null;
                }while(rs.next());
            }
            rs.close();
            cl.close();
            con.close();
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return arr;
    }
}
