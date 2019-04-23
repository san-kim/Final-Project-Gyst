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
				        responsetext += "<p>event name: "+todoevents.get(index).eventname+"</p>";
				        responsetext += "<p>location: "+todoevents.get(index).location+"</p>";
				        responsetext += "<p>start: "+todoevents.get(index).start+"</p>";
				        responsetext += "<p>end: "+todoevents.get(index).end+"</p>";
				        responsetext += "<p>notes: "+todoevents.get(index).notes+"</p>";
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
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
