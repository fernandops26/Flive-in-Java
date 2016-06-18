/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.Serializable;

/**
 *
 * @author ferna
 */
public class GeneradorNumeros implements Serializable{
    public String generar(String codUsuario){
        int cod=(int)(Math.random()*999999)+1;
        return codUsuario+"-"+String.valueOf(cod);
    }
}
