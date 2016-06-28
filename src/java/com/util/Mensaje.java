package com.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

public class Mensaje {
    public static void msg(String t,String m)   {
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, 
     new FacesMessage(t, m) );
    }
    
    public static void js(String msj){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(" Materialize.toast('"+msj+"',4000)");
    }
 
}
