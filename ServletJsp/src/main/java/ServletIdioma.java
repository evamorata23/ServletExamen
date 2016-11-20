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

public class ServletIdioma  extends HttpServlet{
	Pais pais = new Pais();
	private ConnectionManager manager = new ConnectionH2();
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Idioma idioma = assembleUserFromRequest(req);
		try {
			if(existeEnBaseDeDatos(idioma)){
				actualizar(idioma);
			}else{
				insertar(idioma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		redirect(req,resp);	
	}

	private void insertar(Idioma idioma) {
		Connection conn = manager.open(jdbcUrl);
		// codigo sql que  inserta un idioma
		manager.close(conn);
	}

	private void actualizar(Idioma idioma) {
		Connection conn = manager.open(jdbcUrl);
		// codigo sql que  inserta un idioma
		manager.close(conn);
	}

	private boolean existeEnBaseDeDatos(Idioma idioma) throws SQLException{
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement prepareStatement;
		
		
			// En caso de que no exista creamos un registro nuevo
			System.out.println("Insert");
			// Creamos una SQL de inserccion en el fichero
			String sql = "INSERT INTO IDIOMAS(IDIOMA) VALUES("+idioma.getLanguage()+"')";
			try {
				// Ejecutamos la consulta y cerramos la conexion
				prepareStatement = conn.prepareStatement(sql);
				prepareStatement.execute();
				prepareStatement.close();
				System.out.println("insertado");
			} catch (SQLException e) {

				throw new RuntimeException(e);
			}
			try {
				prepareStatement.close();
			} catch (SQLException e) {

			}
		manager.close(conn);
		return false;
	}

	private Idioma assembleUserFromRequest(HttpServletRequest req) {
		Idioma idioma = new Idioma();
		String language = req.getParameter("language");
		idioma.setLanguage(language);
		return idioma;
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redirect(req,resp);
	}
	

	private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fin.jsp");
		dispatcher.forward(req,resp);
	}
	
}
