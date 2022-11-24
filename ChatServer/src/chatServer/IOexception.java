package chatServer;
 
import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 
public class ChatServer {
    private int port;
    private int ipAddress
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();
 
    public ChatServer(int port, ipAddress) {
        this.port = port;
        this.ipAddress
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
 
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
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }
 
        int port = Integer.parseInt(args[0]);
        int ipAaddress = Integer.parseInt(args[0];
        
        ChatServer server = new ChatServer(port);
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (User != excludeUser) {
                User.sendMessage(message);
            }
        }
    }
 
    /**
     * Stores ip address and port number of the newly connected client.
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(User);
            System.out.println("The user " + userName + "has left");
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