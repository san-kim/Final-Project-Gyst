package final_project_gyst;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteToDoEvent")
public class DeleteToDoEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Account currentUser = (Account) session.getAttribute("user");
        String eventNameToDelete = (String) session.getAttribute("eventnametodelete");
        
        //TODO: if currentuser does not host this event, the user cannot delete this event. Maybe implement in frontend?
		
		//Account currentUser = new Account("abc", "123");
		
        DatabaseAccess d = new DatabaseAccess();
        //traverse all todo events, if match, delete that event.
        for(int i =0;i<currentUser.getToDoEvents().size();i++){
        	ToDoEvent a = currentUser.getToDoEvents().get(i);
            if(a.getToDoEventName()==eventNameToDelete){
                d.removeToDoEvent(a);
                currentUser.removeToDoEvent(a);
                System.out.println("removed a");
            }
        }
        
        //Find the events in database which has the event name same as this name, then delete

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
