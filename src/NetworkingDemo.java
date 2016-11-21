import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class NetworkingDemo {

	private static int port=33333;
	
	public static void main(String[] args) throws Exception{
        ServerSocket listener = new ServerSocket(port);
        //System.out.println("The server is running (inet) : "+listener.getInetAddress());
        //System.out.println("The server is running (local): "+listener.getLocalSocketAddress());
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for(NetworkInterface ni : Collections.list(interfaces)){
                for(InetAddress address : Collections.list(ni.getInetAddresses()))
                {
                    if(address instanceof Inet4Address){
                    	System.out.println("ipv4: "+address);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        
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
