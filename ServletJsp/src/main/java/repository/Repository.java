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
	
	public List<Language> searchLanguage() {
		List<Language> listLanguage= new ArrayList<Language>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(""
					+ "SELECT IdIdioma, IDIOMA FROM IDIOMA");
			resultSet = prepareStatement.executeQuery();
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
			close(prepareStatement);
			}catch(Exception e){}
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
	public void insert(Form formulary) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id=0;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE IDIOMA = ?");
			preparedStatement.setString(1, formulary.getLanguage());
			System.out.println(preparedStatement);
			System.out.println(formulary.getLanguage());
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){	
				preparedStatement = conn.prepareStatement("INSERT INTO IDIOMA (IDIOMA)" +
						"VALUES (?)");
				preparedStatement.setString(1, formulary.getLanguage());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try {
				preparedStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE IDIOMA = ?");
				preparedStatement.setString(1, formulary.getLanguage());
				System.out.println(preparedStatement);
				System.out.println(formulary.getLanguage());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){	
					id = resultSet.getInt(1);
					System.out.println(id);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					preparedStatement = conn.prepareStatement("INSERT INTO PAIS (PAIS,IDIOMA)" +
							"VALUES (?,?)");
					preparedStatement.setString(1, formulary.getCountry());
					preparedStatement.setInt(2, id);
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		close(preparedStatement);
		manager.close(conn);
	}
	
	public void deleteLanguage(String language){
		int idLanguage = 0;
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT IDIDIOMA FROM IDIOMA WHERE IDIOMA = ?");
			prepareStatement.setString(1, language);
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				idLanguage = resultSet.getInt(1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);}
		delete(idLanguage);	
	}

	public void delete(int IdIdioma) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM IDIOMA WHERE " +
					"IDIDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement("DELETE FROM PAIS WHERE " +
					"IDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.close(conn);
	}
	
	public List<Form> searchAll() {
		List<Form> listForm= new ArrayList<Form>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT PAIS.PAIS, IDIOMA.IDIOMA "
					+ "FROM PAIS INNER JOIN IDIOMA ON PAIS.IDIOMA = IDIOMA.IDIDIOMA");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Form formulary = new Form();
				formulary.setCountry(resultSet.getString(1));
				formulary.setLanguage(resultSet.getString(2));
				listForm.add(formulary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try{
			close(resultSet);
			close(prepareStatement);
			}catch(Exception e){}
		}
		manager.close(conn);
		return listForm;
	}
}

