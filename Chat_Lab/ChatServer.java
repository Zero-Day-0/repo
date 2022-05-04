import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import javax.swing.JOptionPane;

public class ChatServer implements Runnable { 
    private String name;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

	ChatServer(int port) {
		CHAT_ROOM_PORT = port;
	}
    
    /**
     * The port that the server listens on.
     */
    private static int CHAT_ROOM_PORT;

    /**
     * The set of all names of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static HashSet<String> names = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	//@Override
	public void run() {
        ServerSocket listener = null;
		try {
			listener = new ServerSocket(CHAT_ROOM_PORT);
			System.out.println("Server started.");
			System.out.println("The chat server is running.");
            while (true) {
            	clientSocket = listener.accept();
            	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            	out = new PrintWriter(clientSocket.getOutputStream(),true);
                name = null;
                while (name == null) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name.equals("")) {
                    	name = null;
                    } else if (name.equals("null")) {
                    	name = null;
                    } else if (names.contains(name)) {
                    	System.out.println("Client's name is in use. Select another name.");
                    	JOptionPane.showMessageDialog(null, "Screen Name '" + name + "' is already in use.");
                    	out.println("Name is in use.");
                    	Thread.sleep(100);
                    	name=null;
                    } else {
                    	names.add(name);	                    
                    }
                }
                out.println("NAMEACCEPTED");
                writers.add(out);
                ServerThreadForClient svrForClient = new ServerThreadForClient(in, out, name);
                
                Thread t = new Thread(svrForClient);
                t.start();
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
            
    private class ServerThreadForClient implements Runnable {
    	BufferedReader in;
    	PrintWriter out;
    	String name;
    	
    	ServerThreadForClient (BufferedReader in, PrintWriter out, String name) {
    		this.in = in;
    		this.out = out;
    		this.name = name;
    	}
    	
    	@Override
		public void run() {
    		try {
                while (true) {
                    String input;
					try {
						input = in.readLine();
					
	                    if (input == null) {
	                        return;
	                    }
	                    for (PrintWriter writer : writers) {
	                        writer.println("MESSAGE " + name + ": " + input);
	                    }
	                } catch (IOException e) {
						e.printStackTrace();
					}
                }
    		} finally {
    			if (name != null) {
    				names.remove(name);
    			}
    			
    			if (out != null) {
    				writers.remove(out);
    			}
    			
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
            }
    	}
    }
}