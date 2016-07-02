/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import com.bean.PublicacionLogica;
import com.entidad.Publicacion;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author ferna
 */
public class Indexador implements Serializable {
        private Directory dir2=null;
        private IndexWriterConfig config2=null;
        private IndexWriter writer2=null;
        private DirectoryReader reader2=null;
        private IndexSearcher searcher2=null;
        private  StandardAnalyzer analizador=new StandardAnalyzer(Version.LUCENE_41);
        QueryParser qp2=null;
        
        
        public Indexador(){
            this.dir2=new RAMDirectory();
            this.config2=new IndexWriterConfig(Version.LUCENE_41,analizador);
        }
        
        public void crearEscritorIndexado() throws IOException{
            if(this.writer2==null){
                this.writer2=new IndexWriter(this.dir2,this.config2);
            }
        }
    
        public void construirIndexadoPublicaciones(List<Publicacion> listaPub) throws IOException{
            this.limpiarIndexado();
            Document doc=null;
            for (int i = 0; i < listaPub.size(); i++) {
                doc = new Document();
                doc.add(new IntField("codPublicacion",listaPub.get(i).getCodPublicacion() ,Field.Store.YES));
                doc.add(new StringField("titulo",listaPub.get(i).getTituloPub(),Field.Store.YES));
                doc.add(new StringField("imagen",listaPub.get(i).getImagenPub(),Field.Store.YES));
                doc.add(new StringField("f_publicacion",listaPub.get(i).getF_creacionPub().toString(),Field.Store.YES));
                doc.add(new IntField("n_likes",listaPub.get(i).getN_likesPub(),Field.Store.YES));
                doc.add(new TextField("tags",listaPub.get(i).getTagsPublicacion(), Store.YES));
                doc.add(new IntField("codAlbum",listaPub.get(i).getObjAlbum().getCodAlbum(), Store.YES));
                doc.add(new StringField("nombre",listaPub.get(i).getObjAlbum().getNombreAlb(), Store.YES));
                doc.add(new IntField("codCategoria",listaPub.get(i).getObjAlbum().getObjCategoria().getCodCategoria(), Store.YES));
                doc.add(new StringField("nombrecate",listaPub.get(i).getObjAlbum().getObjCategoria().getNombreCategoria(),Field.Store.YES));
                doc.add(new IntField("codPerfil",listaPub.get(i).getObjAlbum().getObjPerfil().getCodPerfil(),Field.Store.YES));
                doc.add(new StringField("nombres",listaPub.get(i).getObjAlbum().getObjPerfil().getNombrePer(),Field.Store.YES));
                doc.add(new StringField("apellidos",listaPub.get(i).getObjAlbum().getObjPerfil().getApellidosPer(),Field.Store.YES));
                doc.add(new StringField("imagenper",listaPub.get(i).getObjAlbum().getObjPerfil().getImagenPer(),Field.Store.YES));
                String todo=listaPub.get(i).getObjAlbum().getObjCategoria().getNombreCategoria().trim() +" "+ listaPub.get(i).getTagsPublicacion();
                doc.add(new Field("Contenido",todo,Store.NO,Field.Index.ANALYZED));
                this.writer2.addDocument(doc);
                doc=null;
            }
        }
        
        public void limpiarIndexado() throws IOException{
            this.writer2.deleteAll();
        }
        
        public List<Publicacion> buscarPublicaciones(String consulta) throws IOException, ParseException, java.text.ParseException{
            Publicacion obj;
            List<Publicacion> listaPub;
            this.reader2 = DirectoryReader.open(this.writer2, true);
            this.searcher2 = new IndexSearcher(this.reader2);
            this.qp2=new QueryParser(Version.LUCENE_41,"Contenido",analizador);
            this.qp2.setDefaultOperator(QueryParser.Operator.AND);
            Query query=this.qp2.parse(consulta);
            TopDocs docs=this.searcher2.search(query, 9999999);
                    
                ScoreDoc[] score=docs.scoreDocs;
                Document dc=null;
                listaPub=new ArrayList<>();
                String[] arrTags=null;
                for (ScoreDoc sd1 : score) {
                    obj=new Publicacion();
                    SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
                    dc=this.searcher2.doc(sd1.doc);
                    System.out.println("ID: "+ dc.get("cod"));
                    System.out.println("NOMBRE: "+ dc.get("titulo"));
                    System.out.println("DESCRIPCION: "+ dc.get("tags"));
                    System.out.println("CATEGORIA: "+ dc.get("categoria"));
                    obj.setCodPublicacion(Integer.parseInt(dc.get("codPublicacion")));
                    obj.setTituloPub(dc.get("titulo"));
                    obj.setImagenPub(dc.get("imagen"));
                    java.util.Date fecha=sdf.parse(dc.get("f_publicacion"));
                    obj.setF_creacionPub(fecha);
                    obj.setN_likesPub(Integer.parseInt(dc.get("n_likes")));
                    obj.setTagsPublicacion(dc.get("tags"));
                    obj.getObjAlbum().setCodAlbum(Integer.parseInt(dc.get("codAlbum")));
                    obj.getObjAlbum().setNombreAlb(dc.get("nombre"));
                    obj.getObjAlbum().getObjCategoria().setCodCategoria(Integer.parseInt(dc.get("codCategoria")));
                    obj.getObjAlbum().getObjCategoria().setNombreCategoria(dc.get("nombrecate"));
                    obj.getObjAlbum().getObjPerfil().setCodPerfil(Integer.parseInt(dc.get("codPerfil")));
                    obj.getObjAlbum().getObjPerfil().setNombrePer(dc.get("nombres"));
                    obj.getObjAlbum().getObjPerfil().setApellidosPer(dc.get("apellidos"));
                    obj.getObjAlbum().getObjPerfil().setImagenPer(dc.get("imagenper"));
                    
                    arrTags=new Utiles().SepararTags(obj.getTagsPublicacion());
                    obj.setArrTags(arrTags);
                    arrTags=null;
                    listaPub.add(obj);
                    obj=null;
                }       
                
            this.writer2.deleteAll();
            this.writer2.close();
                
            return listaPub;
                  
        }
    
}
