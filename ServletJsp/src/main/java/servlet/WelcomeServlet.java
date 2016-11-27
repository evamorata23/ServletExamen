package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionH2;
import connection.ConnectionManager;

import service.Service;


public class WelcomeServlet extends HttpServlet{
	
	private Service service = new Service();
	
	
}
