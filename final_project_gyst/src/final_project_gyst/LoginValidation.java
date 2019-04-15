

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("TxtUsername");
		String password = request.getParameter("TxtPasswrd");
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//don't have to write this line if is a standalone project
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/201a3userinfo?user=root&password=root");
			ps = conn.prepareStatement("SELECT * FROM userinfo WHERE username =?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(!username.equals(rs.getString("username"))) {
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("error", " This user does not exist.");
					rd.forward(request, response);
					return;
				}
				//User exists,check password now
				if(!password.equals(rs.getString("password"))) {
					RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
					request.setAttribute("error", " Incorrect password.");
					rd.forward(request, response);
					return;
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				request.setAttribute("error", " This user does not exist.");
				rd.forward(request, response);
				return;
			}
		}
		catch(SQLException sqle){
			System.out.println("sqle:"+sqle.getMessage());
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("cnfe:" +cnfe.getMessage());
		}finally {//finally: no matter what, execute
			//since we run it on Tomcat, close connection to make other connections able to proceed (if you have 10000+ connections in 5 min)
			try {
				if(rs!=null) {rs.close();}
				if(ps!=null) {ps.close();}
				if(conn!=null) conn.close();
			}
			catch(SQLException sqle){
				System.out.println("sqle closing:"+sqle.getMessage());
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("fullcalendar.html");
		request.setAttribute("UserN", username);
		rd.forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}