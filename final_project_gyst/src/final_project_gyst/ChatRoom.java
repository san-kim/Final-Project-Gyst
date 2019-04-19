import java.io.IOException;
import java.net.*;
import java.util.Vector;

public class ChatRoom {
	private Vector<ChatThread> chatThreads;
	public ChatRoom(int port) {
		ServerSocket ss = null;
		try {
			System.out.println("Trying to bind port " +port);
			ss = new ServerSocket(port);
			System.out.println("Bounded to port " +port);
			chatThreads = new Vector<ChatThread>();
			while(true) {
				Socket s = ss.accept();
				System.out.println("Connection from "+s.getInetAddress());
				ChatThread st = new ChatThread(s,this);
				chatThreads.add(st);
			}
		}
		catch(IOException ioe) {
			System.out.println("ioe: "+ioe.getMessage());
		}
		finally {
			try {
				if (ss!=null) ss.close();
			}
			catch(IOException ioe) {
				System.out.println("ioe closing: "+ ioe.getMessage());
			}
		}
	}
	
	public void broadcast(String message,ChatThread currentCT) {
        //TODO: Change broadcast to pushing the message to the frontend chat page
		if(message !=null) {
			System.out.println(message);
			for(ChatThread st:chatThreads) {
				if(st!=currentCT) {
					st.sendMessage(message);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ChatRoom cr = new ChatRoom(6789);
	}
}
