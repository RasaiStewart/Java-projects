package chat.Server;


	import java.io.*;
	import java.net.*;
	import java.util.*;
	 
	/**
	 * This is the chat server program.
	 * Press Ctrl + C to terminate the program.
	 */
	public class ChatServer {
	    private int port;
	    private Set<ipAddress> ipAddress = new HashSet<>(); ;
	    private Set<String> userNames = new HashSet<>();
	    private Set<UserThread> userThreads = new HashSet<>();
	 
	    public ChatServer(int port) {
	        this.port = port;
	        this.ipAddress = ipAddress;
	    }
	 
	    public void execute() {
	        try (ServerSocket serverSocket = new ServerSocket(port, ipAddress)) {
	 
	            System.out.println("Chat Server is listening on port " + port + "from" + ipAddress);
	 
	            while (true) {
	                Socket socket = serverSocket.accept();
	                System.out.println("A New user has been connected to the chat");
	 
	                UserThread newUser = new UserThread(socket, this);
	                userThreads.add(newUser);
	                newUser.start();
	 
	            }
	 
	        } catch (IOException e) {
	            System.out.println("Error in the server: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	 
	    public static void main(String[] args) {
	        if (args.length < 1) {
	            System.out.println("Syntax: java ChatServer <port-number> <ipAddress>");
	            System.exit(0);
	        }
	 
	        int port = Integer.parseInt(args[0]);
	        int ipAddress = Integer.parseInt(args[0]);
	 
	        ChatServer server = new ChatServer(port);
	        server.execute();
	    }
	 
	    /**
	     * Delivers a message from one user to others (broadcasting)
	     */
	    void broadcast(String message, UserThread excludeUser) {
	        for (UserThread aUser : userThreads) {
	            if (aUser != excludeUser) {
	                aUser.sendMessage(message);
	            }
	        }
	    }
	 
	    /**
	     * Stores username of the newly connected client.
	     */
	    void addUserName(String userName) {
	        userNames.add(userName);
	    }
	 
	    /**
	     * When a client is disconnected, removes the associated username and UserThread
	     */
	    void removeUser(String userName, UserThread aUser) {
	        boolean removed = userNames.remove(userName);
	        if (removed) {
	            userThreads.remove(aUser);
	            System.out.println("The user " + userName + " quitted");
	        }
	    }
	 
	    Set<String> getUserNames() {
	        return this.userNames;
	    }
	 
	    /**
	     * Returns true if there are other users connected (not count the currently connected user)
	     */
	    boolean hasUsers() {
	        return !this.userNames.isEmpty();
	    }
	}

