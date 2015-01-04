package backtype.storm.autoscaling;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class DaemonSubmitter {

	public static void submitTopology(String topologyName){	
		
		Socket socket = null;
		OutputStream os = null;
		DataOutputStream osw = null;

		try {
			socket = new Socket("localhost", 9091);
			os = socket.getOutputStream();			
			osw = new DataOutputStream(os);
			osw.writeBytes(topologyName);						
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(osw != null){
					osw.flush();
					osw.close();
				}
				
				if(os != null){
					os.flush();
					os.close();
				}
				
				if(socket != null){					
					socket.close();
				}								
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}	
}
