package backtype.storm.autoscaling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class ScalingUtil {

	public static int getTasksForThreshold(int threshold){		
		if(threshold > 100){
			return 15;
		}
		else if(threshold > 500 && threshold < 1000){
			return 10;
		}
		else{
			return 5;
		}		
	}
	
	
	static final String FILE_PATH = "C:\\Users\\H\\Desktop\\Storm\\storm.txt"; 
	
	static {

		File file = new File(FILE_PATH);
		try {
			if(!file.exists()){
				file.createNewFile();
			}			
		}
		catch (IOException e) {

		}
	}
	
	public static void writeToFile(String string) {
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)));			
			out.println(string);
		} 
		catch (Exception e) {

		} 
		finally {
			if (out != null)
				out.close();
		}

	}
	
	
}
