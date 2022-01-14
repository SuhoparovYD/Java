package stplate.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stplate.beans.Slab;
import stplate.beans.Tile;
import stplate.beans.Noslab;
import stplate.utils.DBUtils;
import stplate.utils.MyUtils;

@WebServlet(urlPatterns = { "/slabToTiles" })

public class SlabToTilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SlabToTilesServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);
		String errorString = null;
		int ncurrent = 1, tcurrent = 1;

		// write to DB		
		try {
			ncurrent  = Integer.parseInt(request.getParameter("ncurrent"));
			DBUtils.insertCurrent(conn, ncurrent);
		} catch (Exception e) {		} 
		
		try {
			tcurrent  = Integer.parseInt(request.getParameter("tcurrent"));
			DBUtils.insertTileCurrent(conn, tcurrent);
		} catch (Exception e) {		} 
				
		
		//select ncurrent			
		try {
			ncurrent = DBUtils.findCurrent(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		Noslab noslab = null;
		String currentNo= "125";
		try {
			noslab = DBUtils.findNoslab(conn, ncurrent);
			currentNo = noslab.getSlabno();
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		List<Slab> list = null;
		try {
			list = DBUtils.querySlabs(conn, noslab);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		List<Noslab> nolist = null;
		try {
			nolist = DBUtils.queryNoslabs(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString += e.getMessage();
		}
		
		List<Tile> tlist = null;
		try {
			tlist = DBUtils.queryTiles(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
				
		try {
			tcurrent= DBUtils.findTileCurrent(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
				
		 Tile tileCurrent=null;
			try {
				tileCurrent = DBUtils.findTile(conn, Integer.toString(tcurrent)); 
			} catch (SQLException e) {
				e.printStackTrace();
				errorString = e.getMessage();
			} 
			
		String tcstr = Integer.toString(tileCurrent.getX()) +"*" +Integer.toString(tileCurrent.getY()); 
		
		int tx = (tileCurrent.getX())/10; 
		int ty = (tileCurrent.getY())/10; 

		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("slabList", list);
		request.setAttribute("noslabList", nolist);
		request.setAttribute("currentNo", currentNo);
		request.setAttribute("tileList", tlist);
		request.setAttribute("tcstr", tcstr);
		request.setAttribute("tx", tx);
		request.setAttribute("ty", ty);

		// Forward Рє /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/slabToTilesView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
