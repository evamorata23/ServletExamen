package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;

public class DeleteServlet extends HttpServlet {
	


private Service servicio = new  Service();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		servicio.delete(req.getParameter("languageDelete"));
		redirect("/end.jsp", req, resp);
		
		
	}

	protected void redirect(String endPage, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(endPage);
		dispatcher.forward(req,resp);
	}
}

