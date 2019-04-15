

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

@WebServlet("/RegisterValidation")
public class RegisterValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("TxtUsername");
		String password = request.getParameter("TxtPasswrd");
		String cfmpassword = request.getParameter("CfmPasswrd");
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		if(!password.equals(cfmpassword)) {
			//passwords dont match!
			RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
			request.setAttribute("error", "The passwords do not match.");
			rd.forward(request, response);
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//don't have to write this line if is a standalone project
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/201a3userinfo?user=root&password=root");//root = full permission too
/*			ps = conn.prepareStatement("CREATE DATABASE 201a3userinfo");//? is filled with what user typed in
			//ps.setString(1, firstName);//1 means replace first question mark with variable firstName
			rs = ps.executeQuery();
			ps.close();
			ps = conn.prepareStatement("CREATE TABLE userinfo  (" +  
					"username varchar(50) PRIMARY KEY auto_increment," + 
					"password varchar(50) not null" + 
					");");
			rs = ps.executeQuery();
			ps.close();*/
			ps = conn.prepareStatement("SELECT * FROM userinfo WHERE username =?");
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {//if there are more rows, return true
				//User already exists!
				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				request.setAttribute("error", "This username is already taken.");
				rd.forward(request, response);
				return;
			}
			ps.close();
			ps = conn.prepareStatement("insert into userinfo (username,password)" + 
					" values (?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			RequestDispatcher rd = request.getRequestDispatcher("LoggedIn.jsp");//TODO: direct this to logged in page!
			request.setAttribute("UserN", username);
			rd.forward(request, response);
			return;
		}
		catch(SQLException sqle){
			System.out.println("sqle:"+sqle.getMessage());
			System.out.println(username);
			System.out.println(password);
			System.out.println(cfmpassword);
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("cnfe:" +cnfe.getMessage());
		}finally {
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}