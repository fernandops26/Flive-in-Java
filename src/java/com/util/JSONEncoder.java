/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.entidad.MensajeJSON;
import org.primefaces.json.JSONObject;
import org.primefaces.push.Encoder;

/**
 *
 * @author ferna
 */
public class JSONEncoder implements Encoder<MensajeJSON,String>{
    
    public String encode(MensajeJSON m){
        return new JSONObject(m).toString();
    }
}
