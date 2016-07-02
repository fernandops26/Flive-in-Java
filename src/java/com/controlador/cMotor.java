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
import com.entidad.Publicacion;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import com.util.Indexador;
import com.util.Mensaje;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;
/**
 *
 * @author ferna
 */

public class cMotor implements Serializable{
    private String consulta="";
    private List<Publicacion> listaPubGeneral=null;
     private List<Gustar> listaLikesDePerfil;
    /**
     * Creates a new instance of cMotor
     */
    public cMotor() {
        this.listaPubGeneral=new ArrayList<Publicacion>();
        this.listaLikesDePerfil=new GustarLogica().buscarLikesPorPerfil(new SessionLogica().obtenerUsuarioSession().getObjPerfil().getCodPerfil());
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
    

    
    
}
