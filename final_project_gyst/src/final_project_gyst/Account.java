package final_project_gyst;

public class Account extends Thread{
    private int userID;
    private String username;
    private String password;

    public Account(String u, String p){
        username = u; password = p;
    }
}