package final_project_gyst;

import java.util.ArrayList;

public class Account extends Thread{
    // TODO: Create a map for accounts in Server class --> should be map<username, account object>

    private int userID;
    private String username;
    private String password;
    private ArrayList<Event> hosted_events;

    public Account(String u, String p){
        username = u; password = p;
    }
    
    public ArrayList<Event> getEvents(){
    	return hosted_events;
    }

    // TODO: Thread.Run() method implementation
    
}