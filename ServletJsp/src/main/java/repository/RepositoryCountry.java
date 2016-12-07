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

public class RepositoryCountry {
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();
	
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
	
	public void insertCountry(Form form){
		int id=0;
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String idioma = form.getLanguage();
			preparedStatement = conn.prepareStatement("INSERT INTO PAIS (PAIS,IDIOMA)" + "VALUES (?,?)");
			preparedStatement.setString(1, form.getCountry());
			preparedStatement.setInt(2, findLanguageId(idioma));

			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}
	
	public int findLanguageId(String language) {
		int idLanguage = 0;
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
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
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM PAIS WHERE IDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.close(conn);
	}
}
