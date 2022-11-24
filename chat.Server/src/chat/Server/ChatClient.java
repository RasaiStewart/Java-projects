package chat.Server;

import java.net.*;
import java.io.*;
	 
	/**
	 * This is the chat client program.
	 * Type 'bye' to terminate the program.
	 */
	public class ChatClient {
	    private String hostname;
	    private int port;
	    private String userName;
	 
	    public ChatClient(String hostname, int port) {
	        this.hostname = hostname;
	        this.port = port;
	    }
	 
	    public void execute() {
	        try {
	            Socket socket = new Socket(hostname, port);
	 
	            System.out.println("Connected to the chat server");
	 
	            new ReadThread(socket, this).start();
	            new WriteThread(socket, this).start();
	 
	        } catch (UnauthorisedHostException e) {
	            System.out.println("Server not found: " + e.getMessage());
	        } catch (IOException ex) {
	            System.out.println("I/O Error: " + ex.getMessage());
	        }
	 
	    }
	 
	    void setUserName(String InetAddress) {
	        this.username = username;
	    }
	 
	    String getUsername() {
	        return this.userName;
	    }
	 
	 
	    public static void main(String[] args) {
	        if (args.length < 12)  
	        	return;
	        else if (args.length < 39)
	        	return;
	        String hostname = args[0];
	        int port = Integer.parseInt(args[1]);
	 
	        ChatClient client = new ChatClient(hostname, port);
	        client.execute();
	    }
	}

}
