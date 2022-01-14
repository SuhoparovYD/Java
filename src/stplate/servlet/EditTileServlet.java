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

@WebServlet(urlPatterns = { "/editTile" })
public class EditTileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditTileServlet() {
		super();
	}

	// Отобразить страницу редактирования продукта.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String idStr = (String) request.getParameter("id");

		Tile tile = null;

		String errorString = null;

		try {
			tile = DBUtils.findTile(conn, idStr);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Ошибки не имеются.
		// Продукт не существует для редактирования (edit).
		// Redirect sang trang danh sách sản phẩm.
		if (errorString != null && tile == null) {
			response.sendRedirect(request.getServletPath() + "/tileList");
			return;
		}

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tile", tile);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editTileView.jsp");
		dispatcher.forward(request, response);

	}

	// После того, как пользователь отредактировал информацию и нажал на Submit.
	// Данный метод будет выполнен.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		
		String errorString = null;
		
		int id = 1; //Integer.parseInt(request.getParameter("id"));
		
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {		}  
	
		int x =0;
		try {
			x = Integer.parseInt(request.getParameter("x"));
    	} catch (Exception e) {
    		e.printStackTrace(); 
    		errorString = "Ошибка ввода размера по X - " + e.getMessage();
    	} 
		
		int y =0;
		try {
			y = Integer.parseInt(request.getParameter("y"));
    	} catch (Exception e) {	e.printStackTrace(); errorString = e.getMessage();	}
				
		
		String comments = (String) request.getParameter("comments");

/*		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		} catch (Exception e) {
		}  */
		
		Tile tile = new Tile(id, x, y, comments);

		try {
			DBUtils.updateTile(conn, tile);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tile", tile);

		// Если имеется ошибка, forward к странице edit.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editTileView.jsp");
			dispatcher.forward(request, response);
		}
		// Если все хорошо.
		// Redirect к странице со списком продуктов.
		else {
			response.sendRedirect(request.getContextPath() + "/tileList");
		}
	}

}