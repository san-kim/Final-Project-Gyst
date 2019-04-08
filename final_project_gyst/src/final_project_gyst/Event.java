package final_project_gyst;

import java.util.ArrayList;

public class Event {

    // event identification
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
    private Account host; // host user for this event

    // sharing functionality
    private ArrayList<Account> interested;
    private ArrayList<Account> going;
    private ArrayList<Account> not_going;
    private ArrayList<Account> no_response;
    private ArrayList<Account> people_shared; // all people event is shared with
    
    // private Notification notification; --> FIXME: this may not be necessary
    
    void sendNotification(){
        
    }

    public Event(String name, String UTCDateTimeString, String note, String loc, Account h){
        eventName = name; notes = note; location = loc; host = h;

        // convert UTC Date / Time String to Date and Time in int
        
    }

}
