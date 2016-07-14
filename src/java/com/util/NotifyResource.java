/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.entidad.MensajeJSON;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PathParam;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.annotation.Singleton;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author ferna
 */
@PushEndpoint("/{canalActual}")
@Singleton
public class NotifyResource {
    @PathParam("canalActual")
    private String canalActual;
    
    @OnMessage(decoders = {JSONDecoder.class}, encoders= {JSONEncoder.class})
    public MensajeJSON onMessage(MensajeJSON message){
        return message;
    }
}
