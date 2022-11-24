package chat.Server;


	import java.io.*;
	import java.net.*;
	import java.util.*;
	 
	/**
	 * This thread handles connection for each connected client, so the server
	 * can handle multiple clients at the same time.
	 */

	public class UserThread extends Thread {
	    private Socket socket;
	    private ChatServer server;
	    private PrintWriter writer;
	 
	    public UserThread(Socket socket, ChatServer server) {
	        this.socket = socket;
	        this.server = server;
	    }
	 
	    public void run() {
	        try {
	            InputStream input = socket.getInputStream();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            writer = new PrintWriter(output, true);
	 
	            printUsers();
	 
	            String userName = reader.readLine();
	            server.addUserName(userName);
	 
	            String serverMessage = "New user connected: " + userName;
	            server.broadcast(serverMessage, this);
	 
	            String clientMessage;
	 
	            do {
	                clientMessage = reader.readLine();
	                serverMessage = "[" + userName + "]: " + clientMessage;
	                server.broadcast(serverMessage, this);
	 
	            } while (!clientMessage.equals("bye"));
	 
	            server.removeUser(userName, this);
	            socket.close();
	 
	            serverMessage = userName + " has exited the chat.";
	            server.broadcast(serverMessage, this);
	 
	        } catch (IOException e) {
	            System.out.println("Error in UserThread: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * Sends a list of online users to the newly connected user.
	     * So that all users are notified about when a person leaves
	     * or joins the chat.
	     */
	    void printUsers() {
	        if (server.hasUsers()) {
	            writer.println("Connected users: " + server.getUserNames());
	        } else {
	            writer.println("No other users connected");
	        }
	    }
	 
	    /**
	     * Sends a message to the client.
	     */
	    void sendMessage(String message) {
	        writer.println(message);
	    }
	}
}
