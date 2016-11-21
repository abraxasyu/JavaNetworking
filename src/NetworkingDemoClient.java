import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkingDemoClient implements MouseMotionListener{
	
	String output;
	private BufferedReader in;
    private PrintWriter out;
    
	public NetworkingDemoClient(){
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		output="("+e.getX()+","+e.getY()+") - "+e.getWhen();
		out.println(output);
		String response;
		try {
            response = in.readLine();
            if (response!=null){
            	log(response);
            }
        } catch (IOException ex) {
               log("Error: " + ex);
        }
	}
	public void connecttoserver() throws IOException{
		String serveraddress="";
		int port=33333;
		Socket socket = new Socket(serveraddress,port);
		in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
	}
	private void log(String Message){
		System.out.println(Message);
	}
	
}
