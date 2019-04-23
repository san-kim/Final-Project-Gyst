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


@WebServlet("/events_servlet")
public class events_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public events_servlet() 
    {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();  
		String currentuser = (String)session.getAttribute("currentuser");
		
		String addingevent = request.getParameter("addingevent");
		if(addingevent != null)
		{
			if(addingevent.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				String eventname = request.getParameter("eventname");
				String location = request.getParameter("location");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String notes = request.getParameter("notes");
				int id = Integer.parseInt(request.getParameter("id"));
				
				am.addEvent(am.getUsernameFromID(id), eventname, location, start, end, notes, am.getUsernameFromID(id));
				
				String sharedwith = request.getParameter("sharedwith");
				if(sharedwith != null)
				{
					//if user decided to share with anyone
					if(sharedwith.length() > 0)
					{
						String [] users = getUsersFromLine(sharedwith);
						for(int i = 0; i<users.length; i++)
						{
							//check if username exists
							if(am.userExists(users[i]))
							{
								//host remains the same but update all others
								am.addEvent(users[i], eventname, location, start, end, notes, am.getUsernameFromID(id));
							}
						}
					}
				}
			}
		}
		

		String showeventinfo = request.getParameter("showeventinfo");
		if(showeventinfo != null)
		{
			if(showeventinfo.trim().equals("true"))
			{				
				int id = Integer.parseInt(request.getParameter("eventidinput").trim());
				DatabaseAccess am = new DatabaseAccess();
				
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				EventInfo info = am.getEventWithIDUsername(id, currentuser);
				String responsetext = "";

				if(info != null)
				{
					responsetext += "<p id='eventtitle'>Event Details</p>";
			        responsetext += "<p>event name: "+info.eventname+"</p>";
			        responsetext += "<p>location: "+info.location+"</p>";
			        responsetext += "<p>start: "+info.start+"</p>";
			        responsetext += "<p>end: "+info.end+"</p>";
			        responsetext += "<p>notes: "+info.notes+"</p>";
			        responsetext += "<button id='closebutton1' onclick='exiteventinfo();'>close</button>";
			        
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	
	//helper function
	public String[] getUsersFromLine(String line)
	{
		String delimiter = ",";
		String[] users = line.split(delimiter);
		for(int i = 0; i<users.length; i++)
		{
			users[i] = users[i].trim();
		}
		return users;
	}
}
