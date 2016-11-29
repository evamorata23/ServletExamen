package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionH2;
import connection.ConnectionManager;
import model.*;

public class Repository {
	
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	
	public Country search(Country countryForm) {
		Country cForm= null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM PAIS WHERE nombrePais = ?");
			prepareStatement.setString(1, countryForm.getCountry());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				countryForm = new Country();
				countryForm.setCountry(resultSet.getString(0));
				countryForm.setLanguage(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return countryForm;
	}
	
	public Language search(Language languageForm) {
		Language lForm= null;
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		Connection conn = manager.open(jdbcUrl);
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE nombreIdioma = ?");
			prepareStatement.setString(1, languageForm.getLanguage());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				languageForm = new Language();
				languageForm.setLanguage(resultSet.getString(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
			
		}
		manager.close(conn);
		return languageForm;
	}
	
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
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
	
	public void insert(Country countryForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO PAIS (nombrePais,nombreIdioma)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, countryForm.getCountry());
			preparedStatement.setString(2, countryForm.getLanguage());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	
	public void insert(Language languageForm) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO PAIS (nombrePais,nombreIdioma)" +
					"VALUES (?, ?)");
			preparedStatement.setString(1, languageForm.getLanguage());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(preparedStatement);
		}
		manager.close(conn);
	}
	
	/*Inserta un pais*/
	public void update(Country countryForm) {
		Connection conn = manager.open(jdbcUrl);
		manager.close(conn);
	}
	
	/*Inserta un idioma*/
	public void update(Language languageForm) {
		Connection conn = manager.open(jdbcUrl);
		manager.close(conn);
	}
	
	public List<Country> searchAllCountry() {
		List<Country> listCountry= new ArrayList<Country>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM USER");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Country countryInDatabase = new Country();
				countryInDatabase.setCountry(resultSet.getString(1));
				countryInDatabase.setLanguage(resultSet.getString(2));
				
				listCountry.add(countryInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listCountry;
	}
	
	public List<Language> searchAllLanguage() {
		List<Language> listLanguage= new ArrayList<Language>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT * FROM IDIOMA");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Language languageInDatabase = new Language();
				languageInDatabase.setLanguage(resultSet.getString(1));
				
				listLanguage.add(languageInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listLanguage;
	}
	
	/*Busca los idiomas de un pais*/
	public List<Country> searchCountriesByLanguage(Language language) {
		List<Country> listCountry= new ArrayList<Country>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			//Definimos la consulta
			prepareStatement = conn.prepareStatement("SELECT * FROM PAIS where idIdioma=?");
			//Le pasamos un objeto de la clase language para coger bien el idioma
			prepareStatement.setInt(1,language.getIdLanguage());
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				Country countryInDatabase = new Country();
				//Le pasamos el segundo parametro porque queremos coger el nombrePais
				countryInDatabase.setCountry(resultSet.getString(2));
				countryInDatabase.setLanguage(String.valueOf(resultSet.getInt(3)));
				listCountry.add(countryInDatabase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			close(resultSet);
			close(prepareStatement);
		}
		manager.close(conn);
		return listCountry;
	}
	
}
