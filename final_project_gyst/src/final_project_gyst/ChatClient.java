package final_project_gyst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends Thread{
    //TODO: When a user opens a chat page a new Chatclient thread will start running;
    //When the user closes a chat page the thread should be killed.
	private BufferedReader br;
	private PrintWriter pw;
	public ChatClient(String hostname,int port) {
		Socket s = null;
		try {
			System.out.println("Trying to connect to "+hostname +": "+port);
			s = new Socket(hostname,port);
			System.out.println("Connected to "+hostname+":"+port);
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.pw = new PrintWriter(s.getOutputStream());
			this.start();
			Scanner scan = new Scanner(System.in);
			while(true) {
				String line = scan.nextLine();
				pw.println("John Doe: "+line);
				pw.flush();//dont forget to flush
			}
		}catch(IOException ioe) {
			System.out.println("ioe: " +ioe.getMessage());
		}finally {
			try{
				if(s!=null) s.close();
				
			}
			catch(IOException ioe) {
				System.out.println("ioe closing: "+ioe.getMessage());
			}
		}
	}
	public void run() {
		try {
			while(true) {
                //TODO: change to pushing all content in BufferedReader to the frontend chat page
				String line = br.readLine();
				System.out.println(line);
			}
		}
		catch(IOException ioe) {
			System.out.println("ioe reading from server: "+ioe.getMessage());
		}
	}
	public static void main(String[] args) {
		ChatClient cc = new ChatClient("localhost",6789);

	}

}