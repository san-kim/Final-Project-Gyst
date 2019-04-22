package final_project_gyst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetToDoEvents
 */
@WebServlet("/GetToDoEvents")
public class GetToDoEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("currentuser");
		
		DatabaseAccess d = new DatabaseAccess();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		HashSet<ToDoEventInfo> eventsToSend = d.getToDoEvents(username);
		String eventJSON = new Gson().toJson(eventsToSend);
		response.setStatus(HttpServletResponse.SC_OK);
		out.write(eventJSON);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
