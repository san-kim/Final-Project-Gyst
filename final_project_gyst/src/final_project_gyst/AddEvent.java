package final_project_gyst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.*;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Account currentUser = (Account) session.getAttribute("user");
		
		//Account currentUser = new Account("abc", "123");
        Event a = new Event((long) session.getAttribute("eventid"),
        (String) session.getAttribute("eventname"), 
        (String) session.getAttribute("eventstart"),
        (String) session.getAttribute("eventend"),
        (String) session.getAttribute("eventnote"),
        (String) session.getAttribute("eventlocation"),
        (Account)currentUser, false);
		// now return as json all of the events
		
		currentUser.addHostedEvent(a);//add event object to account's hosting events
        System.out.println("added a");
        
        //TODO: Insert event info into our database. Also add the currentuser to people_shared, and modify its hosted_events

		// response.setContentType("application/json");
		// PrintWriter out = response.getWriter();
		// ArrayList<CalendarEvent> eventsToSend = new ArrayList<CalendarEvent>();
		// for (Event e: currentUser.getEvents()) {
		// 	CalendarEvent toSend = new CalendarEvent(e); // generate new calendar formatted event to send to frontend
		// 	eventsToSend.add(toSend);
		// }
        
        //Then call GetEvents to update our calendar page
        request.setAttribute("user",currentUser);
        RequestDispatcher rd = request.getRequestDispatcher("GetEvents");
        rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}