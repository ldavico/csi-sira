/*
 * Created on 8 nov 2016 ( Time 16:16:33 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package it.csi.sira.backend.metadata.integration.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import it.csi.sira.backend.metadata.integration.dto.SipraMtdTCategoriaAppl;
import it.csi.sira.backend.metadata.integration.dao.SipraMtdTCategoriaApplDAO;
import it.csi.sira.backend.metadata.utils.GenericDAO;
import org.springframework.jdbc.core.RowMapper;

/**
 * SipraMtdTCategoriaAppl DAO implementation 
 * 
 * @author Telosys Tools
 *
 */
public class SipraMtdTCategoriaApplDAOImpl extends GenericDAO<SipraMtdTCategoriaAppl> implements SipraMtdTCategoriaApplDAO {
	//----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public SipraMtdTCategoriaApplDAOImpl() {
		super();
	}

	private final static String QUERY_PRIMARY_KEY = 
		"select * from sipra_mtd_t_categoria_appl where id_categoria_appl = :id_categoria_appl";
	
	private final static String QUERY_INSERT = 
		"insert into sipra_mtd_t_categoria_appl(id_categoria_appl,fk_padre,livello,des_categoria,url_icona,object_number,view_number) values(:id_categoria_appl,:fk_padre,:livello,:des_categoria,:url_icona,:object_number,:view_number)";

	private final static String QUERY_UPDATE = 
		"update sipra_mtd_t_categoria_appl set fk_padre = :fk_padre, livello = :livello, des_categoria = :des_categoria, url_icona = :url_icona, object_number = :object_number, view_number = :view_number  where id_categoria_appl = :id_categoria_appl";

	private final static String QUERY_DELETE = 
		"delete from sipra_mtd_t_categoria_appl where  id_categoria_appl = :id_categoria_appl";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from sipra_mtd_t_categoria_appl";
	
	@Override
	public String getPrimaryKeySelect() {
		return QUERY_PRIMARY_KEY;		
	}
	
	@Override
	public String getTableName(){
		return "sipra_mtd_t_categoria_appl";
	}

	@Override
	public String getSqlSelect() {
		return "select * from sipra_mtd_t_categoria_appl";
	}

	@Override
	public String getSqlInsert() {
		return QUERY_INSERT;
	}

	@Override
	public String getSqlUpdate() {
		return QUERY_UPDATE;
	}

	@Override
	public String getSqlDelete() {
		return QUERY_DELETE;
	}

	@Override
	public String getSqlCount() {
		return SQL_COUNT_ALL;
	}

	public SipraMtdTCategoriaAppl findByPK(Integer idCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("id_categoria_appl", idCategoriaAppl);
		return super.findByPK(map);		
	}

	public int deleteByPK(Integer idCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("id_categoria_appl", idCategoriaAppl);
		return super.delete(getSqlDelete(), map);		
	}

	@Override
	public java.util.Map<String, Object> getValuesForInsert(SipraMtdTCategoriaAppl sipraMtdTCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("id_categoria_appl", sipraMtdTCategoriaAppl.getIdCategoriaAppl());
		map.put("fk_padre", sipraMtdTCategoriaAppl.getFkPadre());
		map.put("livello", sipraMtdTCategoriaAppl.getLivello());
		map.put("des_categoria", sipraMtdTCategoriaAppl.getDesCategoria());
		map.put("url_icona", sipraMtdTCategoriaAppl.getUrlIcona());
		map.put("object_number", sipraMtdTCategoriaAppl.getObjectNumber());
		map.put("view_number", sipraMtdTCategoriaAppl.getViewNumber());
		return map;
	}

	@Override
	public java.util.Map<String, Object> getValuesForUpdate(SipraMtdTCategoriaAppl sipraMtdTCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("fk_padre", sipraMtdTCategoriaAppl.getFkPadre());
		map.put("livello", sipraMtdTCategoriaAppl.getLivello());
		map.put("des_categoria", sipraMtdTCategoriaAppl.getDesCategoria());
		map.put("url_icona", sipraMtdTCategoriaAppl.getUrlIcona());
		map.put("object_number", sipraMtdTCategoriaAppl.getObjectNumber());
		map.put("view_number", sipraMtdTCategoriaAppl.getViewNumber());
		map.put("id_categoria_appl", sipraMtdTCategoriaAppl.getIdCategoriaAppl());
		return map;
	}

	public java.util.Map<String, Object> getValuesForPrimaryKey(SipraMtdTCategoriaAppl sipraMtdTCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("id_categoria_appl", sipraMtdTCategoriaAppl.getIdCategoriaAppl());
		return map;
	}

	@Override
	public java.util.Map<String, Object> getValuesForDelete(SipraMtdTCategoriaAppl sipraMtdTCategoriaAppl) {
		java.util.Map<String, Object> map = new java.util.HashMap<String, Object>();
		map.put("id_categoria_appl", sipraMtdTCategoriaAppl.getIdCategoriaAppl());
		return map;
	}

	@Override
	public RowMapper<SipraMtdTCategoriaAppl> getRowMapper()  {
		//--- RowMapper to populate a new bean instance
		//return new SipraMtdTCategoriaApplRowMapper( new SipraMtdTCategoriaAppl() ) ;

       return new SipraMtdTCategoriaApplRowMapper() ;
	}

	//----------------------------------------------------------------------
	/**
	 * Populates the given bean with the data retrieved from the given ResultSet
	 * @param rs
	 * @param sipraMtdTCategoriaAppl
	 * @throws SQLException
	 */
	private static void populateBean(ResultSet rs, SipraMtdTCategoriaAppl sipraMtdTCategoriaAppl) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		sipraMtdTCategoriaAppl.setIdCategoriaAppl(rs.getInt("id_categoria_appl")); // java.lang.Integer
		if ( rs.wasNull() ) { sipraMtdTCategoriaAppl.setIdCategoriaAppl(null); }; // not primitive number => keep null value if any
		sipraMtdTCategoriaAppl.setFkPadre(rs.getInt("fk_padre")); // java.lang.Integer
		if ( rs.wasNull() ) { sipraMtdTCategoriaAppl.setFkPadre(null); }; // not primitive number => keep null value if any
		sipraMtdTCategoriaAppl.setLivello(rs.getInt("livello")); // java.lang.Integer
		if ( rs.wasNull() ) { sipraMtdTCategoriaAppl.setLivello(null); }; // not primitive number => keep null value if any
		sipraMtdTCategoriaAppl.setDesCategoria(rs.getString("des_categoria")); // java.lang.String
		sipraMtdTCategoriaAppl.setUrlIcona(rs.getString("url_icona")); // java.lang.String
		sipraMtdTCategoriaAppl.setObjectNumber(rs.getInt("object_number")); // java.lang.Integer
		if ( rs.wasNull() ) { sipraMtdTCategoriaAppl.setObjectNumber(null); }; // not primitive number => keep null value if any
		sipraMtdTCategoriaAppl.setViewNumber(rs.getInt("view_number")); // java.lang.Integer
		if ( rs.wasNull() ) { sipraMtdTCategoriaAppl.setViewNumber(null); }; // not primitive number => keep null value if any
	}


	/**
	 * Specific inner class for 'RowMapper' implementation
	 */
	public static class SipraMtdTCategoriaApplRowMapper implements RowMapper<SipraMtdTCategoriaAppl> {
	
		public SipraMtdTCategoriaApplRowMapper() {
			
		}
		
		public SipraMtdTCategoriaAppl mapRow(ResultSet rs, int rowNum) throws SQLException {
			SipraMtdTCategoriaAppl bean = new SipraMtdTCategoriaAppl();
			populateBean(rs, bean);
			return bean;
		}
	}
}
