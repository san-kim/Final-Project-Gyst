import java.util.ArrayList;

public class Account extends Thread{
    // TODO: Create a map for accounts in Server class --> should be map<username, account object>

    private long userID;
    private String username;
    private String password;
    private ArrayList<Event> hosted_events;

    public Account(String u, String p){
        username = u; password = p;
        hosted_events = new ArrayList<Event>();
    }
    
    public ArrayList<Event> getEvents(){
    	return hosted_events;
    }

    public void addEvent(Event e){
        hosted_events.add(e);
    }

    public void removeEvent(Event e){
        hosted_events.remove(e);
    }

    public void setUserId(long id){
        userID = id;
    }

    public long getUserId(){
        return userID;
    }

    // TODO: Thread.Run() method implementation
    
    
    //ADDED
    public String getUsername(){
    	return username;
    }
    //ADDED
    
    
}