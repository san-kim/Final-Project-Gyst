package final_project_gyst;

import java.util.HashSet;

public class Account extends Thread{
    // TODO: Create a map for accounts in Server class --> should be map<username, account object>
    //FIXME: Zihan: I dont think the Account needs to be a thread any more. We use thread only in chatroom

    private long userID;
    private String username;
    private HashSet<Event> events;
    private HashSet<ToDoEvent> todo_events;

    public Account(String u){
        username = u;
        events = new HashSet<Event>();
        todo_events = new HashSet<ToDoEvent>();
    }
    
    public HashSet<Event> getEvents(){
    	return events;
    }

    public void addEvent(Event e){
        events.add(e);
    }

    public void removeEvent(Event e){
        events.remove(e);
    }

    public HashSet<ToDoEvent> getToDoEvents(){
    	return todo_events;
    }

    public void addToDoEvent(ToDoEvent e){
    	todo_events.add(e);
    }

    public void removeToDoEvent(ToDoEvent e){
    	todo_events.remove(e);
    }
    
    public void setUserId(long id){
        userID = id;
    }

    public long getUserId(){
        return userID;
    }

    public String getUsername(){
        return username;
    }

    // TODO: Thread.Run() method implementation
    
}