import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.util.Set;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Executors


package chatServer;

public class ChatServer {
	
	// Store client names for reference later.
	private static Set <String> names = new HashSet<>();
	
	// Define print writer for all the clients.
	private static Set <PrintWriter> writers = new HashSet<>();
	
	private static void main(String[] args) throws Exception {
		System.out.println("The chat server is running...")
		var pool = Executors.newFixedThreadPool(500);
		try(var listener = new ServerSocket(59001)) {
			while (true) {
				pool.execute(new Handler(listener.accept()));
			}
		}
	}
	
	// Handles scripts and messages on the client side.
	
	private static class Handler implements Runnable {
		private String name;
		private Socket socket;
		private Scanner in;
		private PrintWriter out;
		
		/*
		 * Create a thread to request a name by the client
		 * until a unique one has been submitted., then responds and registers the
		 * output stream for the client. All inputs are then broadcasted (displayed, and can be seen by all users.
		 */
		
		public void run() {
			try {
				in = new Scanner(socket.getInputStream())
					out = new PrintWriter(socket.getOutputStream(), true);
					
					// Continue requesting a new name until a unique one is found.
					while (true) {
						out.println("Submit name");
						name = in.nextLine();
						if (name == null) {
							return;
						}
						synchronised {
							if (!name.isBlank() && !names.contains(name)) {
								names.add(name);
								break;
							}
						}
							
					}
					
					// Add a print writer from the socket class
					// to set containing all writer. So the client can receive messages.
					// Before this can happened, inform everyone that a new person has joined the chat.
					out.println("Your name has been accepted and verified");
					for (PrintWriter writer : writers) {
						writer.println("Message: " + name + " has joined");
					}
					writers.add(out);
					
					// Accept messages from client and broadcast them for everyone to see.
					while (true) {
						String input = in.nextLine();
						if (input.toLowerCase().startsWith("/quit")) {
							return;
						}
					}
			} catch (IOexception e) {
				System.out.println(e);
			} finally {
				if (out != null) {
					writers.remove(out);
				}
				if (name != null) {
					System.out.println(name + " is leaving");
					names.remove(name);;
					for (PrintWriter writer : writers) {
						writer.println("Message " + name + " has left");
					}
					
				}
				
				try {
					socket.close();
				} catch (IOException e) {
					
					}
				}
			}
					};
		}
