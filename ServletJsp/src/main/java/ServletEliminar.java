import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEliminar extends HttpServlet{
	
	private ConnectionManager manager = new ConnectionH2();
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	
	private void eliminar(String user) throws SQLException{
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement prepareStatement;
		prepareStatement = conn.prepareStatement("DELETE FROM USER WHERE NOMBRE = '" + user +"'");
		prepareStatement.executeUpdate();
		prepareStatement.close();
		
		manager.close(conn);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idioma = req.getParameter("idioma");
		try {
			eliminar(idioma);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redirect(req,resp);	
	}
	
	private void closeConnection(Connection connectToDatabase) {
		try {
			connectToDatabase.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void executeSqlQuery(Connection connectToDatabase, String sql)  {
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connectToDatabase.prepareStatement(sql);
			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection connectToDatabase()  {
		Connection conn ;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/test.mv", "sa", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return conn;
	}
	
	private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/mensaje.jsp");
		dispatcher.forward(req,resp);
	}
}
