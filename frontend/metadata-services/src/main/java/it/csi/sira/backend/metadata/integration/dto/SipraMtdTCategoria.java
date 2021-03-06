/*
 * Created on 25 nov 2016 ( Time 10:35:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package it.csi.sira.backend.metadata.integration.dto;

import java.io.Serializable;


/**
 * Java bean for 'SipraMtdTCategoria' entity
 * 
 * @author Telosys Tools
 *
 */
public class SipraMtdTCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id_categoria int4 
    private Integer idCategoria;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : fk_tipo_categoria int4 
    private Integer fkTipoCategoria;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setIdCategoria( Integer idCategoria ) {
        this.idCategoria = idCategoria ;
    }

    public Integer getIdCategoria() {
        return this.idCategoria;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setFkTipoCategoria( Integer fkTipoCategoria ) {
        this.fkTipoCategoria = fkTipoCategoria;
    }
    public Integer getFkTipoCategoria() {
        return this.fkTipoCategoria;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(idCategoria);
        sb.append("|");
        sb.append(fkTipoCategoria);
        return sb.toString(); 
    } 


}
