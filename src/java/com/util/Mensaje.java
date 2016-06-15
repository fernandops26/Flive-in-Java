package com.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensaje {
    public static void msg(String t,String m)   {
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, 
     new FacesMessage(t, m) );
    }
 
}
