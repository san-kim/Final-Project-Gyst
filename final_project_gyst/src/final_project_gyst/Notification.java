package final_project_gyst;

import java.util.ArrayList;

public class Notification {
    private Account sender;
    private ArrayList<Account> recipients;
    private String message;

    public Notification(Account s, ArrayList<Account> r, String m){
        sender = s; recipients = r; message = m;
    }
}