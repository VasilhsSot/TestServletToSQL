package cb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertCar() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Statement statement = null;
		String SQLStr;
		
		if (request.getParameter("registration") == null || 
				request.getParameter("factory") == null  ||
				request.getParameter("model") == null || 
				request.getParameter("colour") == null ||
				request.getParameter("cc") == null || 
				request.getParameter("regdate").equals("") ||
				request.getParameter("factory").equals("")  ||
				request.getParameter("model").equals("") || 
				request.getParameter("colour").equals("") ||
				request.getParameter("cc").equals("") || 
				request.getParameter("regdate").equals("")) {
			
			response.getWriter().print(
					"<div style=\"width: 400px; " + "margin: auto;\">"
					+ "Could not insert car, please fill in required fields"
					+ "<br><br><a href=\"index.htm\">home</a></div>");
			
		} else {
			request.setCharacterEncoding("UTF-8");
			String registration = request.getParameter("registration");
			String factory = request.getParameter("factory");
			String model = request.getParameter("model");
			String colour = request.getParameter("colour");
			String cc = request.getParameter("cc");
			String regdate = request.getParameter("regdate");

			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://" + "localhost:3306/test?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", "user",
					"test");) {

				SQLStr = "INSERT INTO car " + "VALUES ('" + registration + "', '" + factory + "', '" + model + "', '"
						+ colour + "', " + cc + ", " + regdate + ");";

				statement = conn.createStatement();
				statement.executeUpdate(SQLStr);
				response.getWriter()
						.print("<div style=\"width: 400px; " + "margin: auto;\">"
								+ "The car has been successfully inserted"
								+ "<br><a href=\"index.htm\">home</a></div>");

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (final SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
}
