package stplate.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stplate.beans.Tile;
import stplate.utils.DBUtils;
import stplate.utils.MyUtils;
import stplate.beans.Tiles;

@WebServlet(urlPatterns = { "/createTile" })
public class CreateTileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateTileServlet() {
		super();
	}

	// ���������� �������� �������� ��������.
	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = MyUtils.getStoredConnection(request);
		int maxId = 0;

		String errorString = null;

		try {
			maxId = DBUtils.findTileId(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// select max(id) from tiles; id++;
	   	Tiles tiles = new Tiles(Integer.toString(maxId+1), "600", "700", "comments");
	 	request.setAttribute("tiles", tiles);
	 	request.setAttribute("errorString", errorString);
		
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/createTileView.jsp");
		dispatcher.forward(request, response);
	}

	// ����� ������������ ������ ���������� ��������, � �������� Submit.
	// ���� ����� ����� ������.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String errorString = null;

		int id =11;
		try {
			id = Integer.parseInt(request.getParameter("id"));
    	} catch (Exception e) {
    		e.printStackTrace(); 
    		errorString = "������ Id - " + e.getMessage();
    	} 		
		
		int x ,y;
		try {
			x = Integer.parseInt(request.getParameter("x"));
    	} catch (Exception e) {
    		e.printStackTrace(); 
    		errorString = "������ ����� ������� X - " + e.getMessage();
    		x=0;
    	} 

		try {
			 y = Integer.parseInt(request.getParameter("y"));
    	} catch (Exception e) {
    		e.printStackTrace(); 
    		errorString = "������ ����� ������� Y - " + e.getMessage();
    		y=0;
    	}
		
		String comments = (String) request.getParameter("comments");

		Tiles tiles = new Tiles(Integer.toString(id), Integer.toString(x), Integer.toString(y), comments);
		Tile tile = new Tile(id , x, y, comments);

		if (errorString == null) {
			try {
				DBUtils.insertTile(conn, tile);
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			}
		}

		// ��������� ���������� � request attribute ����� ��� ��� forward � views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tiles", tiles);

		// ���� ������� ������ forward (���������������) � �������� 'edit'.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
			.getRequestDispatcher("/WEB-INF/views/createTileView.jsp");
			dispatcher.forward(request, response);
		}
		// ���� ��� ������.
		// Redirect (�������������) � �������� �� ������� ���������.
		else {
			response.sendRedirect(request.getContextPath() + "/tileList");
		}
	}

}
