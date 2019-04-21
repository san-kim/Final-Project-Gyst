package final_project_gyst;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToDoEvent
 */
@WebServlet("/AddToDoEvent")
public class AddToDoEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Account currentUser = (Account) session.getAttribute("user");
		
		//Account currentUser = new Account("abc", "123");
        ToDoEvent a = new ToDoEvent((long) session.getAttribute("eventid"),
        (String) session.getAttribute("eventname"), 
        (String) session.getAttribute("eventstart"),
        (String) session.getAttribute("eventend"),
        (String) session.getAttribute("eventnote"),
        (String) session.getAttribute("eventlocation"), false);
		
        
		//Insert event info into our database.
		DatabaseAccess d = new DatabaseAccess();
		d.addEvent((long) session.getAttribute("eventid"),
		(String) currentUser.getUserName(),
		(String) session.getAttribute("eventname"), 
		(String) session.getAttribute("eventlocation"),
        (String) session.getAttribute("eventstart"),
        (String) session.getAttribute("eventend"),
        (String) session.getAttribute("eventnote"));
		currentUser.addToDoEvent(a); //add todo event object to account's todo events
        System.out.println("added a");

		// response.setContentType("application/json");
		// PrintWriter out = response.getWriter();
		// ArrayList<CalendarEvent> eventsToSend = new ArrayList<CalendarEvent>();
		// for (Event e: currentUser.getEvents()) {
		// 	CalendarEvent toSend = new CalendarEvent(e); // generate new calendar formatted event to send to frontend
		// 	eventsToSend.add(toSend);
		// }
        
        //FIXME:Then call GetEvents to update our calendar page
        request.setAttribute("user",currentUser);
        RequestDispatcher rd = request.getRequestDispatcher("GetEvents");
        rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
