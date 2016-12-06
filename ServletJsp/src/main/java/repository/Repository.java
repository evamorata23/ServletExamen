package repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionH2;
import connection.ConnectionManager;
import model.Form;
import model.Language;

public class Repository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	
	Connection conn = manager.open(jdbcUrl);
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	public List<Language> searchLanguage() {
		List<Language> listLanguage= new ArrayList<Language>();
		try {
			preparedStatement = conn.prepareStatement(""
					+ "SELECT IdIdioma, IDIOMA FROM IDIOMA");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString(2));
				Language language = new Language();
				language.setIdLanguage(resultSet.getInt(1));
				language.setLanguage(resultSet.getString(2));
				listLanguage.add(language);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try{
			close(resultSet);
			close(preparedStatement);
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		manager.close(conn);
		return listLanguage;
	}

	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	// insert
	public void insert(Form form) {
		int id=0;
		try {
			String idioma = form.getLanguage();
			if(findLanguageId(idioma)==0){	
				preparedStatement = conn.prepareStatement("INSERT INTO IDIOMA (IDIOMA)" + "VALUES (?)");
				preparedStatement.setString(1,idioma);
				preparedStatement.executeUpdate();
			}
			preparedStatement = conn.prepareStatement("INSERT INTO PAIS (PAIS,IDIOMA)" + "VALUES (?,?)");
			preparedStatement.setString(1, form.getCountry());
			preparedStatement.setInt(2, findLanguageId(idioma));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}
	
	public int findLanguageId(String language) {
		int idLanguage = 0;
		try {
			preparedStatement = conn.prepareStatement("SELECT IDIDIOMA FROM IDIOMA WHERE IDIOMA = ?");
			preparedStatement.setString(1, language);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				idLanguage = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return idLanguage;
	}
	
	public void deleteCountry(int IdIdioma) {
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM PAIS WHERE IDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.close(conn);
	}
	
	public void deleteLanguage(int IdIdioma) {
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM IDIOMA WHERE IDIDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.close(conn);
	}
	
	public List<Form> searchAll() {
		List<Form> listForm= new ArrayList<Form>();
		try {
			preparedStatement = conn.prepareStatement("SELECT PAIS.PAIS, IDIOMA.IDIOMA "
					+ "FROM PAIS INNER JOIN IDIOMA ON PAIS.IDIOMA = IDIOMA.IDIDIOMA");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Form form = new Form();
				form.setCountry(resultSet.getString(1));
				form.setLanguage(resultSet.getString(2));
				listForm.add(form);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try{
			close(resultSet);
			close(preparedStatement);
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		manager.close(conn);
		return listForm;
	}
}

