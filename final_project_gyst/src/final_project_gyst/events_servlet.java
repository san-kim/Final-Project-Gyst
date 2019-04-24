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
				
				int eventID = am.generateEvent_ID();
				
				am.addEvent(eventID, am.getUsernameFromID(id), eventname, location, start, end, notes, am.getUsernameFromID(id));
				
				String sharedwith = request.getParameter("sharedwith");
				if(sharedwith != null)
				{
					//if user decided to share with anyone
					if(sharedwith.length() > 0)
					{
						String [] users = getUsersFromLine(sharedwith);
						int validsharedusers = 0;
						for(int i = 0; i<users.length; i++)
						{
							//check if username exists
							if(am.userExists(users[i]))
							{
								validsharedusers++;
								//host remains the same but update all others
								am.addEvent(eventID, users[i], eventname, location, start, end, notes, am.getUsernameFromID(id));
							}
						}
						
						//if it is shared with at least 1 valid user
						if(validsharedusers >= 1)
						{
							for(int i = 0; i<users.length; i++)
							{
								//check if username exists
								if(am.userExists(users[i]))
								{
									validsharedusers++;
									//host remains the same but update all others
									am.addToSharedEvents(eventID, users[i]);
								}
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
			        responsetext += "<button id='editeventbutton' onclick='showchangeevent();'>edit</button>";
			        responsetext += "<button id='closebutton1' onclick='exiteventinfo();'>close</button>";
			        
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		String changeeventfill = request.getParameter("changeeventfill");
		if(changeeventfill != null)
		{
			if(changeeventfill.trim().equals("true"))
			{
				int eventID = Integer.parseInt((String)request.getParameter("eventID"));
				DatabaseAccess am = new DatabaseAccess();
								
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				EventInfo event = am.getOneEvent(eventID, currentuser);
				String responsetext = "";

				if(event != null)
				{
					responsetext += "<p class='neweventformp'>Edit Event</p>";
					responsetext += "<p>Event Name</p>";
					responsetext += "<input type='text' name='eventname' id='changeeventname' value="+event.eventname+">";
					responsetext += "<p>Location</p>";
					responsetext += "<input type='text' name='location' id='changeeventlocation' value="+event.location+">";
					responsetext += "<p>Start</p>";
					responsetext += "<input type='datetime-local' name='start' id='changeeventstart' value="+event.start+">";
					responsetext += "<p>End</p>";
					responsetext += "<input type='datetime-local' name='end' id='changeeventend' value="+event.end+">";
					responsetext += "<p>Notes</p>";
					responsetext += "<input type='text' name='notes' id='changeeventnotes' value="+event.notes+">";
					responsetext += "<input type='submit' id='changeeventsubmitbutton' name='submit' value='save' onclick=changeevent("+eventID+")>";
					responsetext += "<input type='submit' id='deleteeventsubmitbutton' name='submit' value='delete' onclick=deleteevent("+eventID+")>";
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		String changeeventdatabase = request.getParameter("changeeventdatabase");
		if(changeeventdatabase != null)
		{
			if(changeeventdatabase.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
								
				int eventID = Integer.parseInt(request.getParameter("eventID"));
				String name = request.getParameter("name");
				String location = request.getParameter("location");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String notes = request.getParameter("notes");
				EventInfo ei = new EventInfo(am.getIDFromUsername(currentuser), name, location, start, end, notes);
				am.changeEventInfo(currentuser, ei, eventID);
				//SUCCESSFULLY CHANGES TO DATABASE
			}
		}
		
		String deleteeventdatabase = request.getParameter("deleteeventdatabase");
		if(deleteeventdatabase != null)
		{
			if(deleteeventdatabase.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				
				int eventID = Integer.parseInt(request.getParameter("eventID"));
				am.removeOneEvent(eventID, currentuser.trim());
				//SUCCESSFULLY DELETES THIS EVENT FROM DATABASE
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
