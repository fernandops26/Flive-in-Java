/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.entidad.MensajeJSON;
import org.primefaces.push.Decoder;

/**
 *
 * @author ferna
 */
public class JSONDecoder implements Decoder<String, MensajeJSON> {
    
    public MensajeJSON decode(String t){
        String[] msj =t.split(":");
            MensajeJSON m=new MensajeJSON();
            m.setNombrePerOrigen(msj[0]);
            m.setAccion(msj[1]);
            m.setTipo(Integer.parseInt(msj[2]));
            m.setUrlImagen(msj[3]);
            return m;
    }
    
}
