/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LAB03_16
 */
public class ConectorBD {
    Connection con=null;
    public Connection conectar(){
            try{
                String url="jdbc:oracle:thin:" + "@localhost:1521:orcl";
                String user="user_flive";
                String pass= "1234";
                //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                Class.forName("oracle.jdbc.OracleDriver");
                con=DriverManager.getConnection(url,user,pass);
                System.out.println(con);
            }
            
            catch(Exception ex){
                System.out.println(ex.getMessage());
                
            }
            
           return con; 
                    
    }
    public static void main(String[] args) {
      
        Connection con;
        if((con= new ConectorBD().conectar())!=null) System.out.println("ok");
    }
    
    
}
