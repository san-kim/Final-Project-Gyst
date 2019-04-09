package final_project_gyst;

import java.util.ArrayList;

public class Event {

    // CAN BE EDITED AND CHANGED PER EVENT

    // event identification
	
	// TODO: this needs to be UNIQUE FOR ALL EVENTS --> ACROSS ALL USERS (maybe use a hashset & while loop?)
	private long id; 
	
    private String eventName;

    // times
    private int startTime;
    private int endTime;

    // date
    private int month;
    private int day;
    private int year;

    // misc
    private String notes;
    private String location;

    // sharing functionality --> all public arraylists to make adding and removing users easier per group
    public ArrayList<Account> interested;
    public ArrayList<Account> going;
    public ArrayList<Account> not_going;
    public ArrayList<Account> no_response;
    public ArrayList<Account> people_shared; // all people event is shared with


    // CANNOT BE CHANGED --> HOST MUST STAY SAME

    private Account host; // host user for this event
    
    // private Notification notification; --> FIXME: this may not be necessary
    
    void sendNotification(){
        // needs implementation
    }

    // constructor with ISO-8601 Date-Time String
    public Event(String name, String UTCDateTimeString, String note, String loc, Account h){
        eventName = name; notes = note; location = loc; host = h;

        // convert UTC Date / Time String to Date and Time in int
        // TODO: parse ISO-8601 Date-Time String into class data
    }

    // constructor with all variables already parsed
    public Event(String name, int start, int end, int m, int d, int y, String note, String loc, Account h){
        eventName = name; startTime = start; endTime = end;
        month = m; day = d; year = y;
        notes = note; location = loc; host = h;
    }

    public void setEventName(String e){
        eventName = e;
    }

    public String getEventName(){
        return eventName;
    }

    public void setStartTime(int s){
        startTime = s;
    }

    public int getStartTime(){
        return startTime;
    }

    public void setEndtime(int e){
        endTime = e;
    }

    public int getEndTime(){
        return endTime;
    }

    public void setMonth(int m){
        month = m;
    }

    public int getMonth(){
        return month;
    }

    public void setDay(int d){
        day = d;
    }

    public int getDay(){
        return day;
    }

    public void setYear(int y){
        year = y;
    }
    
    public int getYear(){
        return year;
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

}
