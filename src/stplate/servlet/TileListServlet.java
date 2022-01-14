package stplate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
*/

import org.json.*;

import stplate.beans.Tile;
import stplate.utils.DBUtils;
import stplate.utils.MyUtils;

@WebServlet(urlPatterns = { "/tileList" })
public class TileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TileListServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		List<Tile> tileList = null;
		try {
			tileList = DBUtils.queryTiles(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
				
		// Сохранить информацию в request attribute перед тем как forward к views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("tileList", tileList);
		
		// Forward Рє /WEB-INF/views/productListView.jsp
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/tileListView.jsp");
		dispatcher.forward(request, response);
	}
	
	JSONObject getData(HttpServletRequest request, HttpServletResponse response) {
		
		Connection conn = MyUtils.getStoredConnection(request);
		
		List<Tile> tileList = null;
		try {
			tileList = DBUtils.queryTiles(conn);
		} catch (SQLException e) {
			e.printStackTrace();
	//		errorString = e.getMessage();
		}

		JSONObject jsonObj = new JSONObject();
		try {
			int cntL =  tileList.size();
	//		JSONObject jsonObj = new JSONObject();
			jsonObj.put("total", 1+ cntL/40);
			jsonObj.put("page", 1);
			jsonObj.put("records", cntL);
			jsonObj.put("rows", tileList );
					
	//		PrintWriter pstream = new PrintWriter(response.getOutputStream(), true);
	//		pstream.println(jsonObj); // here I want to output string as UTF-8
			
		 } catch (Exception e) {
			e.printStackTrace();
		//	errorString = e.getMessage();
		}			
		return (jsonObj);		

	
}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}