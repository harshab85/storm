package storm.starter.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

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



	public static Object getRandomId(String sentence) {
		
		double random = Math.random() * 10000 * sentence.hashCode();		
		if(random % 2 == 0)
			return sentence + "_" + random;
		else
			return sentence + ":" + random;
	}
	
}
