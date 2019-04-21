package final_project_gyst;

class EventInfo 
{
	public int user_ID;
	public String eventname;
	public String location;
	public String start;
	public String end;
	public String notes;
	public EventInfo(int user_ID, String eventname, String location, String start, String end, String notes)
	{
		this.user_ID = user_ID;
		this.eventname = eventname;
		this.location = location;
		this.start = start;
		this.end = end;
		this.notes = notes;		
	}
}