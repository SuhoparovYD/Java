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
import javax.servlet.http.HttpSession;

import stplate.beans.UserAccount;
import stplate.utils.DBUtils;
import stplate.utils.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	// Показать страницу Login.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Forward (перенаправить) к странице /WEB-INF/views/loginView.jsp
		// (Пользователь не может прямо получить доступ
		// к страницам JSP расположенные в папке WEB-INF).
		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

		dispatcher.forward(request, response);

	}

	// Когда пользователь вводит userName & password, и нажимает Submit.
	// Этот метод будет выполнен.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rememberMeStr = request.getParameter("rememberMe");
		boolean remember = "Y".equals(rememberMeStr);

		UserAccount user = null;
		boolean hasError = false;
		String errorString = null;

		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				// Найти user в DB.
				user = DBUtils.findUser(conn, userName, password);

				if (user == null) {
					hasError = true;
					errorString = "User Name or password invalid";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		// В случае, если есть ошибка,
		// forward (перенаправить) к /WEB-INF/views/login.jsp
		if (hasError) {
			user = new UserAccount();
			user.setUserName(userName);
			user.setPassword(password);

			// Сохранить информацию в request attribute перед forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			// Forward (перенаправить) к странице /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

			dispatcher.forward(request, response);
		}
		// В случае, если нет ошибки.
		// Сохранить информацию пользователя в Session.
		// И перенаправить к странице userInfo.
		else {
			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, user);

			// Если пользователь выбирает функцию "Remember me".
			if (remember) {
				MyUtils.storeUserCookie(response, user);
			}
			// Наоборот, удалить Cookie
			else {
				MyUtils.deleteUserCookie(response);
			}

			// Redirect (Перенаправить) на страницу /userInfo.
			response.sendRedirect(request.getContextPath() + "/userInfo");
		}
	}

}