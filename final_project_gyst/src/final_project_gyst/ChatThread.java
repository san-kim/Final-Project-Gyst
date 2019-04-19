import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatThread extends Thread /*implements Runnable*/{
	private BufferedReader br;
	private PrintWriter pw;
	private ChatRoom cr;
	public ChatThread (Socket s,ChatRoom cr) {
		this.cr = cr;
		try {
            //TODO: Modify the code such that it updates the frontend chat room page
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.pw = new PrintWriter(s.getOutputStream());
			this.start();
		}
		catch (IOException ioe) {
			System.out.println("ioe: "+ioe.getMessage());
		}
	}
	public void run() {
		try {
			while(true) {
				String line = br.readLine();
				cr.broadcast(line,this);
			}
		}
		catch(IOException ioe){
			System.out.println("ioe: "+ioe.getMessage());
		}
	}
	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
}