/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

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
public class DepartamentoLogica implements Serializable{
    
    
    public List<Departamento> ListarDepartamentos(){
        Connection con;
        CallableStatement cl;
        Departamento obj;
        List<Departamento> arr=null;
        int id=0;
        try{
            con=new ConectorBD().conectar();
            cl=con.prepareCall("{call PAQ_DEPART.sp_listarDepartamentos(?)}");
            cl.registerOutParameter(1, OracleTypes.CURSOR);
            cl.executeQuery();
            ResultSet rs=(ResultSet) cl.getObject(1);
            arr=new ArrayList<>();
            if(rs.next()){
                do{
                   obj=new Departamento();
                    obj.setCodDepartamento(rs.getInt("CODDEPARTAMENTO"));
                    obj.setNombre_dep(rs.getString("NOMBREDEP"));
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
