/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controlador;

import com.bean.GustarLogica;
import com.bean.MotorLogica;
import com.bean.PublicacionLogica;
import com.bean.SessionLogica;
import com.entidad.Gustar;
import com.entidad.Perfil;
import com.entidad.Publicacion;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.util.Indexador;
import com.util.Mensaje;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import org.apache.lucene.queryparser.classic.ParseException;
import org.primefaces.context.RequestContext;
/**
 *
 * @author ferna
 */
@ManagedBean
public class cMotor implements Serializable{
    private String consulta="";
    private List<Publicacion> listaPubGeneral=null;
    private List<Gustar> listaLikesDePerfil;
    private List<Perfil> listaPerfilesEncontrados=null; 
    private String consultaPerfil="";
    /**
     * Creates a new instance of cMotor
     */
    public cMotor() {
        this.listaPubGeneral=new ArrayList<Publicacion>();
        this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
        this.listaPerfilesEncontrados=new ArrayList<Perfil>();
        this.consultaPerfil="";
        this.consulta="";
    }
    
    public void buscarTexto() throws IOException, ParseException, java.text.ParseException{
        if(!this.consulta.equals("")){
            this.listaPubGeneral=new MotorLogica().buscarPublicacionesGenerales(consulta);
        }
    }
    
    public boolean verificarLike(int codPublicacion){
        for (int i = 0; i < this.listaLikesDePerfil.size(); i++) {
             if(this.listaLikesDePerfil.get(i).getObjPublicacion().getCodPublicacion()==codPublicacion){
                 return true;
             }
        }
        
        return false;
    }
    
    public void actualizarLikes() throws IOException, ParseException, java.text.ParseException{
        if(!this.consulta.equals("")){
            this.listaPubGeneral=new MotorLogica().buscarPublicacionesGenerales(this.consulta);
        }
        this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
           
    }
    
    public void toggleLike(int codPublicacion) throws IOException, ParseException, java.text.ParseException{
//        Mensaje.js("Diste un like a "+codPublicacion);
//        int pos=this.posicionLike(codPublicacion);
        if(this.verificarLike(codPublicacion)){
            new GustarLogica().eliminarLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            this.actualizarLikes();
            Mensaje.js("Dejo de gustarte una publicacion");
           
        }else{
            new GustarLogica().nuevoLikePublicacion(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil(), codPublicacion);
            this.actualizarLikes();
            Mensaje.js("Te gusto una publicación");
        }
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public List<Publicacion> getListaPubGeneral() {
        return listaPubGeneral;
    }

    public void setListaPubGeneral(List<Publicacion> listaPubGeneral) {
        this.listaPubGeneral = listaPubGeneral;
    }

    public String getConsultaPerfil() {
        return consultaPerfil;
    }

    public void setConsultaPerfil(String consultaPerfil) {
        this.consultaPerfil = consultaPerfil;
    }

    public List<Perfil> getListaPerfilesEncontrados() {
        return listaPerfilesEncontrados;
    }

    public void setListaPerfilesEncontrados(List<Perfil> listaPerfilesEncontrados) {
        this.listaPerfilesEncontrados = listaPerfilesEncontrados;
    }

    

    
    public void buscarAlTipear() throws IOException, ParseException, java.text.ParseException{
        this.consultaPerfil.trim();
            if(this.consultaPerfil.length()>1){
                this.listaPerfilesEncontrados=new MotorLogica().buscarPerfilesAlTipear(this.consultaPerfil);
                System.out.println(this.listaPerfilesEncontrados.size());
            }else{
                this.listaPerfilesEncontrados.clear();
            }
            
       
//       System.out.println(this.listaPerfilesEncontrados.size());
//       RequestContext.getCurrentInstance().update("listaPerfilesEncontrados");
    }
            
            
    
}
