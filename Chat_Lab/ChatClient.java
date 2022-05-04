import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
//import javafx.stage.Stage;
import javafx.stage.Stage;

import javax.swing.JOptionPane;


public class ChatClient implements Runnable, ChatClientInterface {
	private static int CHAT_ROOM_PORT = 0;
	boolean local = true;
    BufferedReader in;
    PrintWriter out = null;
    BorderPane frame = new BorderPane();
    TextField textField = new TextField();
    TextArea messageArea = new TextArea();
    Stage stage;
    String name = "";
    
	ChatClient(int port) {
		CHAT_ROOM_PORT = port;

        // Layout GUI
		stage = new Stage();
		stage.setScene(new Scene(frame, 500, 200));
		stage.setX(ChatClientExec.getClientX());
		stage.setY(ChatClientExec.getClientY());
		// Set stage title and show the stage.
		stage.setTitle("Chat Client");
		stage.show();
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.setTop(textField); 
        frame.setCenter(messageArea); 
        frame.setVisible(true);
        
        /**
         * Responds to pressing the enter key in the textfield by sending
         * the contents of the text field to the server.    Then clear
         * the text area in preparation for the next message.
         */
        textField.setOnAction(event -> {
        			try {
        				out.println(textField.getText());
                        textField.setText("");
					} catch (Exception e) {
						e.printStackTrace();
					}
        });
	}

	/**
     * Prompt for and return the address of the server.
     */
    private String getServerAddress() {
    	return "localHost";
    }

    /**
     * Prompt for and return the desired screen name.
     */
    public String getName() {
        return JOptionPane.showInputDialog(
            null,
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    public void run() {
		try {
			Socket clientSocket = new Socket(getServerAddress(),CHAT_ROOM_PORT);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(),true);
	        while (true) {
	            String line = in.readLine();
	            if (line.startsWith("SUBMITNAME")) {
	            	System.out.println("Client must submit a name.");
	            	name = getName();
	                out.println(name);
	            } else if (line.startsWith("NAMEACCEPTED")) {
	            	System.out.println("Client's name has been accepted.");
	                textField.setEditable(true);
	            } else if (line.startsWith("WRONGNAME")) {
	            	System.out.println("Client's name is in use. Select another name.");
	            	JOptionPane.showMessageDialog(null, "Screen Name '" + name + "' is already in use.");
	            } else if (line.startsWith("MESSAGE")) {
	            	System.out.println("Client has received a message.");
	            	//MESSAGE " + name + ": " + input
	                messageArea.appendText(line.substring(8) + "\n");
	            }
	        }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public int getServerPort() {
		return CHAT_ROOM_PORT;
	}
}