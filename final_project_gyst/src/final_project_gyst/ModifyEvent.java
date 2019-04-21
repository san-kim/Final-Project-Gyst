package final_project_gyst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.*;

/**
 * Servlet implementation class ModifyEvent
 */
@WebServlet("/ModifyEvent")
public class ModifyEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
		HttpSession session = request.getSession();
        Account currentUser = (Account) session.getAttribute("user");
        //String eventNameToModify = (String) session.getAttribute("eventnametomodify");
        //FIXME: I am assuming the event names are unique.
        
        //TODO: if currentuser does not host this event, the user cannot modify this event. Implement in frontend?
		
		//Account currentUser = new Account("abc", "123");
		DatabaseAccess d = new DatabaseAccess();
        Event modified = new Event((long) session.getAttribute("eventid"),
        (String) session.getAttribute("eventname"), 
        (String) session.getAttribute("eventstart"),
        (String) session.getAttribute("eventend"),
        (String) session.getAttribute("eventnote"),
        (String) session.getAttribute("eventlocation"),
        (Account)currentUser, false);

        //Modify this event in our database.
		d.changeEvent(modified);
		//a.people_shared = new ArrayList<Account>();
		//a.people_shared.add(currentUser);//add the currentuser to people_shared

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}