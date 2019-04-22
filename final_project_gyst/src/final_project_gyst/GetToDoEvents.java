package final_project_gyst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

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
		Account currentUser = (Account) session.getAttribute("user");
		
		DatabaseAccess d = new DatabaseAccess();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		HashSet<ToDoEventInfo> events_to_send = d.getToDoEvents(currentUser.getUsername());
		ArrayList<CalendarEvent> eventsToSend = new ArrayList<CalendarEvent>();
		Iterator<ToDoEventInfo> it = events_to_send.iterator();
		while (it.hasNext()) {
			ToDoEventInfo temp = it.next();
			ToDoEvent tempevent = new ToDoEvent(temp.user_ID, temp.eventname, temp.start, temp.end, temp.notes, temp.location,false);
			CalendarEvent toSend = new CalendarEvent(tempevent);
			eventsToSend.add(toSend);
		}
	     
		// create json array from CalendarEvent class
		Gson gson = new Gson();
		String jsonOutput = gson.toJson(eventsToSend);
		out.println(jsonOutput);
		out.flush();

		request.setAttribute("eventsToSend", jsonOutput);
		request.getRequestDispatcher("fullcalendar.html").forward(request, response);
		//TODO: in fullcalendar.html, get the eventsToSend parameter and populate the events on the calendar page
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
