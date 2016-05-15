/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.List;


/**
 *
 * @author ferna
 */
public abstract class AbstractLogica <T> {

    public abstract boolean 
        Registrar(Object a);
  
        public abstract List<T>
                Listar();
        /* public abstract*/ 
    
}
