package cb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindCar
 */
public class FindCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindCar() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement statement = null;
		String SQLStr;
		ResultSet rs = null;

		request.setCharacterEncoding("UTF-8");
		String registration = request.getParameter("registration");
		String factory = request.getParameter("factory");
		String model = request.getParameter("model");
		String colour = request.getParameter("colour");
		String cc = request.getParameter("cc");
		String regdate = request.getParameter("regdate");

		SQLStr = "SELECT * FROM `test`.`car` WHERE colour like '%' ";
		if (registration != null && !registration.equals(""))
			SQLStr += "AND registration like '" + registration + "' ";
		if (factory != null && !factory.equals(""))
			SQLStr += "AND factory like '" + factory + "' ";
		if (model != null && !model.equals(""))
			SQLStr += "AND model like '" + model + "' ";
		if (colour != null && !colour.equals(""))
			SQLStr += "AND colour like '" + colour + "' ";
		if (cc != null && !cc.equals(""))
			SQLStr += "AND cc = " + cc + " ";
		if (regdate != null && !regdate.equals(""))
			SQLStr += "AND regdate = " + regdate + ";";

		try {
			// get connection
			conn = DriverManager.getConnection(
					"jdbc:mysql://5.189.135.166:3011/test?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", 
					"java", "java");

			System.out.println(SQLStr);
			statement = conn.createStatement();
			rs = statement.executeQuery(SQLStr);
			request.setAttribute("cars", rs);
			request.getRequestDispatcher("/findcar.jsp").forward(request, response);

		} catch (SQLException sqlEx) {
			System.err.println(sqlEx.getMessage());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				conn.close();
			} catch (final SQLException sqlEx) {
				System.err.println(sqlEx.getMessage());
			}
		}
	}
}
