/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entidad.Publicacion;
import com.util.Indexador;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 *
 * @author ferna
 */
public class MotorLogica implements Serializable {
    private Indexador objInd1=null;
    public MotorLogica(){
        this.objInd1=new Indexador();
    }
    
    public void buscarPublicaciones(){
        
    }
    
    public List<Publicacion> buscarPublicacionesGenerales(String consulta) throws IOException, ParseException, java.text.ParseException{
        objInd1.crearEscritorIndexado();
        objInd1.construirIndexadoPublicaciones(new PublicacionLogica().buscarTodasPublicaciones());
        return objInd1.buscarPublicaciones(consulta);
    }
}
