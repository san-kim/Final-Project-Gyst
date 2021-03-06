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

@WebServlet("/todoevents_servlet")
public class todoevents_servlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    public todoevents_servlet() 
    {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();  
		String currentuser = (String)session.getAttribute("currentuser");

		String gettodo = request.getParameter("gettodo");
		if(gettodo != null)
		{
			if(gettodo.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				String responsetext = "";

				if(todoevents != null)
				{
					for(int i = 0; i<todoevents.size(); i++)
					{
						responsetext += "<a onclick='showtodoinfo("+i+");'>"+ todoevents.get(i).eventname +"</a><br>";
					}
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		String getnexttodo = request.getParameter("getnexttodo");
		if(getnexttodo != null)
		{
			if(getnexttodo.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				String responsetext = "";

				if(todoevents != null)
				{
					if(todoevents.size() > 0)
						responsetext = todoevents.get(0).eventname;
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		String showtodoinfo = request.getParameter("showtodoinfo");
		if(showtodoinfo != null)
		{
			if(showtodoinfo.trim().equals("true"))
			{
				int index = Integer.parseInt((String)request.getParameter("index"));
				DatabaseAccess am = new DatabaseAccess();
				
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				String responsetext = "";

				if(todoevents != null)
				{
					if(todoevents.size() > index)
					{
						responsetext += "<p id='todotitle'>To Do Event Details</p>";
				        responsetext += "<p id='todoname1'>event name: "+todoevents.get(index).eventname+"</p>";
				        responsetext += "<p id='todolocation1'>location: "+todoevents.get(index).location+"</p>";
				        responsetext += "<p id='todostart1'>start: "+todoevents.get(index).start+"</p>";
				        responsetext += "<p id='todoend1'>end: "+todoevents.get(index).end+"</p>";
				        responsetext += "<p id='todonotes1'>notes: "+todoevents.get(index).notes+"</p>";
				        responsetext += "<button id='edittodobutton' onclick='showchangetodo();'>edit</button>";
				        responsetext += "<button id='closebutton' onclick='exittodoinfo();'>close</button>";
					}
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		
		
		String addtodo = request.getParameter("addtodo");
		if(addtodo != null)
		{
			if(addtodo.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				int todoID = am.generateToDoEvent_ID();
				String name = request.getParameter("name");
				String location = request.getParameter("location");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String notes = request.getParameter("notes");
				am.addToDoEvent(todoID, currentuser, name, location, start, end, false, notes);
				//SUCCESSFULLY ADDS TO DATABASE
			}
		}
		
		String changetodo = request.getParameter("changetodo");
		if(changetodo != null)
		{
			if(changetodo.trim().equals("true"))
			{
				int index = Integer.parseInt((String)request.getParameter("index"));
				DatabaseAccess am = new DatabaseAccess();
								
				//successfully got the ArrayList of todoevents onload of the calendar page
				//STYLE THIS AND ADD IT
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				String responsetext = "";

				if(todoevents != null)
				{
					if(todoevents.size() > index)
					{
						responsetext += "<p class='neweventformp'>Edit To Do Event</p>";
						responsetext += "<p>Event Name</p>";
						responsetext += "<input type='text' name='todoeventname' id='changetodoeventname' value="+todoevents.get(index).eventname+">";
						responsetext += "<p>Location</p>";
						responsetext += "<input type='text' name='location' id='changetodolocation' value="+todoevents.get(index).location+">";
						responsetext += "<p>Start</p>";
						responsetext += "<input type='datetime-local' name='start' id='changetodostart' value="+todoevents.get(index).start.trim()+">";
						responsetext += "<p>End</p>";
						responsetext += "<input type='datetime-local' name='end' id='changetodoend' value="+todoevents.get(index).end.trim()+">";
						responsetext += "<p>Notes</p>";
						responsetext += "<input type='text' id='changetodonotesinput' name='notes' value="+todoevents.get(index).notes+">";
						responsetext += "<input type='submit' id='changetodoeventsubmitbutton' name='submit' value='save' onclick=changetodoevent("+index+");>";
						responsetext += "<input type='submit' id='deletetodoeventsubmitbutton' name='submit' value='delete' onclick=deletetodoevent("+index+");>";

					}
				}
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(responsetext);
			}
		}
		
		String changetododatabase = request.getParameter("changetododatabase");
		if(changetododatabase != null)
		{
			if(changetododatabase.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				
				int index = Integer.parseInt(request.getParameter("index"));
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				int todoID = todoevents.get(index).event_ID;
				String name = request.getParameter("name");
				String location = request.getParameter("location");
				String start = request.getParameter("start");
				String end = request.getParameter("end");
				String notes = request.getParameter("notes");
				ToDoEventInfo tdei = new ToDoEventInfo(todoID, am.getIDFromUsername(currentuser), name, location, start, end, false, notes);
				am.changeToDoEvent(currentuser, tdei);
				//SUCCESSFULLY CHANGES TO DATABASE
			}
		}
		
		String deletetododatabase = request.getParameter("deletetododatabase");
		if(deletetododatabase != null)
		{
			if(deletetododatabase.trim().equals("true"))
			{
				DatabaseAccess am = new DatabaseAccess();
				
				int index = Integer.parseInt(request.getParameter("index"));
				ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				int todoID = todoevents.get(index).event_ID;
				am.removeOneToDoEvent_eventIDusername(todoID, currentuser.trim());
				//SUCCESSFULLY DELETES THIS EVENT FROM DATABASE
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
