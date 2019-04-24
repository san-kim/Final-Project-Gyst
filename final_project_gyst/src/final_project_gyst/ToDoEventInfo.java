package final_project_gyst;

class ToDoEventInfo
{
	public int event_ID;
	public int user_ID;
	public String eventname;
	public String location;
	public String start;
	public String end;
	public boolean block;
	public String notes;
	public ToDoEventInfo(int event_ID, int user_ID, String eventname, String location, String start, String end, boolean block, String notes)
	{
		this.event_ID = event_ID;
		this.user_ID = user_ID;
		this.eventname = eventname;
		this.location = location;
		this.start = start;
		this.end = end;
		this.block = block;
		this.notes = notes;		
	}
}
