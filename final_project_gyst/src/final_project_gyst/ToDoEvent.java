package final_project_gyst;

public class ToDoEvent {

    // CAN BE EDITED AND CHANGED PER EVENT

    // event identification
	
	// TODO: this needs to be UNIQUE FOR ALL EVENTS --> ACROSS ALL USERS (maybe use a hashset & while loop?)
	private long id; 
	
    private String name;
    private String location;
    private boolean block;

    // times
    private String start;
    private String end;

    // date --> might not be necessary, can represent date as ISO-8601 String
    // private int month;
    // private int day;
    // private int year;

    // misc
    private String notes;
    
    // private Notification notification; --> FIXME: this may not be necessary
    
    void sendNotification(){
        // needs implementation
    }

    // constructor with all variables already parsed
    public ToDoEvent(long id, String name, String start, String end, String note, String location, boolean block){
        this.id = id; this.name = name; this.location = location; 
        this.start = start; this.end = end; this.block = block; notes = note;
    }

    public void setName(String e){
        name = e;
    }
    
    public long getId(){
        return id;
    }

    public void setId(int i){
        id = i;
    }
    public String getName(){
        return name;
    }

    public void setStart(String s){
        start = s;
    }

    public String getStart(){
        return start;
    }

    public void setEnd(String e){
        end = e;
    }

    public String getEnd(){
        return end;
    }

    public void setNotes(String n){
        notes = n;
    }

    public String getNotes(){
        return notes;
    }

    public void setLocation(String l){
        location = l;
    }

    public String getLocation(){
        return location;
    }
    public void setBlock(boolean b){
        block = b;
    }

    public boolean getBlock(){
        return block;
    }
}
