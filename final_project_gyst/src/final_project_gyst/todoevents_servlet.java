package final_project_gyst;

import java.io.IOException;
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
				if(currentuser.length() != 0 && !currentuser.equals("guest"))
				{
					ArrayList<ToDoEventInfo>todoevents = am.getToDoEvents(currentuser);
				}
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
