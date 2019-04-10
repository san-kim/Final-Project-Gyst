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
import com.google.gson.Gson;

/**
 * Servlet implementation class GetEvents
 */
@WebServlet("/GetEvents")
public class GetEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
//		Account currentUser = (Account) session.getAttribute("user");
		
		Account currentUser = new Account("abc", "123");
		// generate pre-made events to test servlet functionality
		Event a = new Event("event1", "2019-04-09T12:30:00", "2019-04-09T13:30:00", "note1", "loc1", currentUser, false);
		Event b = new Event("event2", "2019-04-08T10:30:00", "2019-04-08T12:30:00", "note2", "loc2", currentUser, false);
		// now return as json all of the events

		// FIXME: THIS IS CURRENTLY THROWING NULLPOINTEREXCEPTION (?)
		currentUser.addEvent(a);
		System.out.println("added a");	
		currentUser.addEvent(b);
		System.out.println("added b");
		/**
			Event Format for reference:
			title: title of event
			allDay: determines if event spans an entire day, or if it starts/ends at set time
			className: 5 different types, determines the color of the event
				-> default(transparent), important(red), chill(pink), success(green), info(blue)
				-> for our case, just use info color (blue)
			start: new Date(year, month, day, hour, minute) --> need to output as Date() object
			end: new Date(year, month, day, hour, minute)
			url: onclick function(?)

			CALENDAR DISPLAYS WITH CURRENT WEEK

			TODO: Add events feed (using JSON array) for entering into calendar
					--> NEED TO MAKE FUNCTION FOR AJAX CALL TO SERVLET (/GetEvents)

		**/

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ArrayList<CalendarEvent> eventsToSend = new ArrayList<CalendarEvent>();
		for (Event e: currentUser.getEvents()) {
			CalendarEvent toSend = new CalendarEvent(e); // generate new calendar formatted event to send to frontend
			eventsToSend.add(toSend);
		}
		// create json array from CalendarEvent class
		String jsonOutput = new Gson().toJson(eventsToSend);
		out.println(jsonOutput);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
