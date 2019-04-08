package final_project_gyst;

public class Account extends Thread{
    // TODO: Create a map for accounts in Server class --> should be map<username, account object>

    private int userID;
    private String username;
    private String password;

    public Account(String u, String p){
        username = u; password = p;
    }

    // TODO: Thread.Run() method implementation
    
}