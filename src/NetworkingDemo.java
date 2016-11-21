import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkingDemo {

	private static int port=33333;
	
	public static void main(String[] args) throws Exception{
		System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(port);
        try {
            while (true) {
                new NetworkingDemoThread(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
		
	}
	
	
	private static class NetworkingDemoThread extends Thread{
		private Socket socket;
		private String clientname;
		private void log(String message){
			System.out.println(message);
		}
		public NetworkingDemoThread(Socket socketin){
			socket=socketin;
		}
		public void run(){
			try{
				BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                //connected
                out.println("connected");
                
                while(true){
                	String input = in.readLine();
                	if (input==null){return;}
                	else{log(input);}
                }
                
                
		 } catch (IOException e) {
             log("Error handling client " + clientname + ": " + e);
         } finally {
             try {
                 socket.close();
             } catch (IOException e) {
                 log("Error: can't close socket: "+e);
             }
             log("Connection with client " + clientname + " closed");
         }
			
			
			
		}
	}

}
