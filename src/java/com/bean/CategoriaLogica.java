/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Categoria;
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
public class CategoriaLogica implements Serializable {
    
    public List<Categoria> ListarCategorias(){
        Connection con;
        CallableStatement cl;
        Categoria obj;
        List<Categoria> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_CATEG.sp_listarCategorias(?)}");
            cl.registerOutParameter(1, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(1);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                   obj=new Categoria();
                    obj.setCodCategoria(rs.getInt("CODCATEGORIA"));
                    obj.setNombreCategoria(rs.getString("NOMBRECATE"));
                    arr.add(obj);
                }while(rs.next());

            }
            rs.close();
            cl.close();
            con.close();
            System.out.println("hay; "+arr.size());
        
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }
}
