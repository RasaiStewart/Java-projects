import java.util.concurrent.Executors;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// A simple threaded server

public class capitaliseServer {

	public static void main(String[] args) throws Exception {
		try (var listener = new ServerSocket(59898)) {
			System.out.println("The capitalisation server is running...");
			var pool = Executors.newFixedThreadPool(20);
			while (true) {
				pool.execute(new Capitaliser(listener.accept());
				
			}
		// TODO Auto-generated method stub

	}

}

private static class Capitaliser implements Runnable {
	private Socket socket;
	
	Capitaliser(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		System.out.println("Connected: " + socket);
		try {
			var in = new Scanner(socket.getInputStream());
			var out = new PrintWriter(socket.getOutputStream(), true);
			while (in.hasNextLine()) {
				out.println(in.nextLine().toUpperCase());
			}
		} catch (Exception e) {
			System.out.println("Error:" + socket);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
			System.out.println("Closed: " + socket);
			}
			}
		}
	}
}