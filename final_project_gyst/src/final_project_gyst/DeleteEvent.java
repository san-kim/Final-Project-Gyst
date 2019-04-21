package final_project_gyst;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.*;

/**
 * Servlet implementation class DeleteEvent
 */
@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
		HttpSession session = request.getSession();
        Account currentUser = (Account) session.getAttribute("user");
        String eventNameToDelete = (String) session.getAttribute("eventnametodelete");
        
        //TODO: if currentuser does not host this event, the user cannot delete this event. Maybe implement in frontend?
		
		//Account currentUser = new Account("abc", "123");
		
        DatabaseAccess d = new DatabaseAccess();
        //traverse all hosted events, if match, delete that event.
        HashSet<Event> events_host = currentUser.getHostedEvents();
		Iterator<final_project_gyst.EventInfo> it = events_to_send.iterator();
	     while(it.hasNext()){
	    	 final_project_gyst.EventInfo temp = it.next();
	    	 Event tempevent = new Event(temp.user_ID,
	    			 temp.eventname,
	    			 temp.start,
	    			 temp.end,
	    			 temp.notes,
	    			 temp.location,
						 currentUser, false//FIXME:I set it to false, but the event could be allday! Need an allday boolean in EventInfo class as well
						);
				CalendarEvent toSend = new CalendarEvent(tempevent); // generate new calendar formatted event to send to frontend
				eventsToSend.add(toSend);
	     }
        
//        for(int i =0;i<currentUser.getHostedEvents().size();i++){
//            if(currentUser.getHostedEvents().get(i).getEventName()==eventNameToDelete){
//                d.removeAllEvent(currentUser.getHostedEvents().get(i));
//                currentUser.removeHostedEvent(currentUser.getHostedEvents().get(i));//add event object to account's hosting events
//                System.out.println("event deleted");
//            }
//        }
        
        //Find the events in database which has the event name same as this name, then delete
		
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