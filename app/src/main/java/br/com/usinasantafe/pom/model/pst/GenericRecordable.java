package br.com.usinasantafe.pom.model.pst;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;

import br.com.usinasantafe.pom.model.dao.LogErroDAO;

public class GenericRecordable<T> {

	private Dao dao;
	private DatabaseHelper instance;
	
	public GenericRecordable() {
		instance = DatabaseHelper.getInstance();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void insert(T element, Class classe) {
		try {
			dao = instance.getDao(classe);
			dao.create(element);
		} catch (SQLException e) {
			LogErroDAO.getInstance().insertLogErro(e);
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteAll(Class classe) {
		try {
			dao = instance.getDao(classe);
			DeleteBuilder<String, Object> deleteBuilder = dao.deleteBuilder();
			deleteBuilder.delete();
		} catch (SQLException e) {
			LogErroDAO.getInstance().insertLogErro(e);
			throw new RuntimeException(e);
		}
	}
	
}
