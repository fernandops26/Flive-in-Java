/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.NotificacionLogica;
import com.bean.PublicacionLogica;
import com.bean.SessionLogica;
import com.entidad.MensajeJSON;
import com.entidad.Notificacion;
import com.entidad.Perfil;
import com.entidad.Publicacion;
import com.entidad.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author ferna
 */
@ManagedBean
@SessionScoped
public class cNotificacion implements Serializable{
    
    @ManagedProperty("#{cCanales}") 
    private cCanales objBeanCanales;
    
     private EventBus eventBus= EventBusFactory.getDefault().eventBus();
    
    private String canalActual;
    private String codPerfil="";
    
    private List<Notificacion> listaNotif=new ArrayList<>();;//Lista de notificaciones del usuario actual
    
    
    
    private String codPerfilPrueba;
    private String textoPrueba;

    public String getCodPerfilPrueba() {
        return codPerfilPrueba;
    }

    public String getTextoPrueba() {
        return textoPrueba;
    }

    public void setTextoPrueba(String textoPrueba) {
        this.textoPrueba = textoPrueba;
    }
    
    

    public void setCodPerfilPrueba(String codPerfilPrueba) {
        this.codPerfilPrueba = codPerfilPrueba;
    }
    
    
    public void enviarNotificacion(int codPerfil,int tipo,String nombrePerOrigen,String nombrePub,String urlImagen){
        String texto="";
        String uuid=this.objBeanCanales.obtenerCanal(String.valueOf(codPerfil));
        if(uuid!=null){
            System.out.println("UUID DIFERENTE DE NULO");
            if(tipo==1){
                texto=" le ha dado like a tu publicación: " +nombrePub;
            }else{
                texto=" te ha empezado a seguir";
            }
            System.out.println("Enviando mensaje a "+uuid);
            this.eventBus.publish("/"+uuid,new MensajeJSON(nombrePerOrigen, texto,tipo,urlImagen));
        }
        
    }
    
    
    public void notificacionLike(int codPublicacion){
        Notificacion obj=new Notificacion();
        obj.getObjPerfilOrigen().setCodPerfil(Integer.parseInt(this.codPerfil));
        obj.getObjPublicacion().setCodPublicacion(codPublicacion);
        new NotificacionLogica().notificacionLike(obj);
        
        //Obj publicacion
        Publicacion objPub=new Publicacion();
        objPub=new PublicacionLogica().buscarPublicacionesPorCodigo(codPublicacion);
        
        Usuario objU=new SessionLogica().obtenerUsuarioSession();
        
        //Concatenar nombres del dueño de la publicacion
        String nombres=objU.getObjPerfil().getNombrePer()+" "+objU.getObjPerfil().getApellidosPer();
        
        System.out.println("CODIGO PERFIL ACTUAL"+this.codPerfil);
        System.out.println("CODIGO DUEÑO PUBLICACION:"+objPub.getObjAlbum().getObjPerfil().getCodPerfil());
        //Perfil de la session actual es diferente al perfil que dio like(verficar si uno mismo no se ha dado like)
        if(Integer.parseInt(this.codPerfil)!=objPub.getObjAlbum().getObjPerfil().getCodPerfil()){
            
            //Enviar notificacion
            this.enviarNotificacion(objPub.getObjAlbum().getObjPerfil().getCodPerfil(),1,nombres ,objPub.getTituloPub(), objPub.getImagenPub());
        }
        
    }

    public void setObjBeanCanales(cCanales objBeanCanales) {
        this.objBeanCanales = objBeanCanales;
    }
    
    public cNotificacion(){
        //Obtener el bean de canales
        FacesContext facesContext = FacesContext.getCurrentInstance();
    objBeanCanales = (cCanales) facesContext.getApplication()
        .getVariableResolver().resolveVariable(facesContext, "cCanales");
      
        
    }
    
   
 
    @PostConstruct
    public void construirCanal(){
        this.canalActual=UUID.randomUUID()+"";
        this.codPerfil=String.valueOf(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
        System.out.println("PERFIL AL CONSTRUIR:"+this.codPerfil);
        this.objBeanCanales.agregarCanal(this.codPerfil, this.canalActual);
        
        //Carga las notificaiones del usuario actual
        this.cargarNotificaciones();
    }
    
    @PreDestroy
    public void destruirCanal(){
        System.out.println("Eliminando codSession: "+this.codPerfil);
        this.objBeanCanales.eliminarCanal(this.codPerfil);
    }
    

    public String getCanalActual() {
        return canalActual;
    }

    public void setCanalActual(String canalActual) {
        this.canalActual = canalActual;
    }
    
    
    public void cargarNotificaciones(){
        System.out.println("COD PERFIL ACTUAL:"+this.codPerfil);
        this.listaNotif=new NotificacionLogica().listarNotificacionesDePerfil(Integer.parseInt(this.codPerfil));
    }
    

    public String getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(String codPerfil) {
        this.codPerfil = codPerfil;
    }

    public List<Notificacion> getListaNotif() {
        return listaNotif;
    }

    public void setListaNotif(List<Notificacion> listaNotif) {
        this.listaNotif = listaNotif;
    }
    
    
    
    
    
}
