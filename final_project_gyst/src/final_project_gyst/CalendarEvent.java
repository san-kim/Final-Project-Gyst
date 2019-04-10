package final_project_gyst;

public class CalendarEvent{
    /**
			Event Format for reference:
			title: title of event
			allDay: determines if event spans an entire day, or if it starts/ends at set time
			className: 5 different types, determines the color of the event
				-> default(transparent), important(red), chill(pink), success(green), info(blue)
				-> for our case, just use info color (blue)
			start: new Date(year, month, day, hour, minute) --> can be ISO-8601 String
			end: new Date(year, month, day, hour, minute) --> can be ISO-8601 String
			url: onclick function(?) --> can call servlet from here? or ajax calls / javascript?

			CALENDAR DISPLAYS WITH CURRENT WEEK

			TODO: Add events feed (using JSON array) for entering into calendar
					--> NEED TO MAKE FUNCTION FOR AJAX CALL TO SERVLET (/GetEvents)

		**/
    private long id;
    private String title;
    private String startTime;
    private String endTime;
    private boolean allDay;
    private String className = "info";
    private String URL = ""; // ONCLICK FUNCTION, SET FOR ANY PARTICULAR EVENT --> MUST SEND EVENT ID AS PARAMETER

    public CalendarEvent(Event event){
        id = event.getId();
        title = event.getEventName();
        startTime = event.getStartTime();
        endTime = event.getEndTime();
        allDay = event.getAllDay();
    }

    public long getId(){
        return id;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getTitle(){
        return title;
    }

    public boolean getAllDay(){
        return allDay;
    }

    public String getClassName(){
        return className;
    }

    public String getURL(){
        return URL;
    }
}