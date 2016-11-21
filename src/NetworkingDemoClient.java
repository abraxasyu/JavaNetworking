import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class NetworkingDemoClient implements MouseMotionListener{
	
	String output;
	
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
	}

}
