package final_project_gyst;

class ToDoEventInfo
{
	public int user_ID;
	public String eventname;
	public String location;
	public String start;
	public String end;
	public boolean block;
	public String notes;
	public ToDoEventInfo(int user_ID, String eventname, String location, String start, String end, boolean block, String notes)
	{
		this.user_ID = user_ID;
		this.eventname = eventname;
		this.location = location;
		this.start = start;
		this.end = end;
		this.block = block;
		this.notes = notes;		
	}
}
