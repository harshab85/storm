package storm.starter.spout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import storm.starter.util.CounterUtil;
import storm.starter.util.FileUtil;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class TestSpout implements IRichSpout{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3930203713377574778L;
	private static final String bookURL = "C:\\Users\\H\\Desktop\\Storm\\test2.txt";
	private static List<String> sentences = new ArrayList<String>();
	private static int index = 0;
	
	static{		
		// Read from a book
		if(sentences.isEmpty()){
			synchronized (sentences) {
				if(sentences.isEmpty()){
					try {
						readFile(bookURL);
					} 
					catch (IOException e) {
						FileUtil.writeToFile("Error while reading book");
						FileUtil.writeToFile(e.getMessage());
					}										
				}
			}
		}		
	}
	
	private static void readFile(String url) throws IOException{
		BufferedReader in = null;
		try{
			File book = new File(url);			
			in = new BufferedReader(new FileReader(book));
		        
			String inputLine;
			while ((inputLine = in.readLine()) != null){ 
				sentences.add(inputLine.trim());								
			}
		}
		finally{
			if(in != null)
				in.close();
		}
	}
	
	SpoutOutputCollector _collector;
		
	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		_collector = collector;			
	}

	@Override
	public void close() {
		_collector = null;			
	}

	@Override
	public void activate() {
	}

	@Override
	public void deactivate() {
	}
		
	private static boolean END = false;
	private static long START = 0;
	
	
	@Override
	public void nextTuple() {			
	    		
		if(END){ 
			return;
		}
				
		Utils.sleep(100);		
		
		if(index == 0){
			START = System.nanoTime();			
		}
		
		if (index < sentences.size()-1){
			final String sentence = sentences.get(index++);
			_collector.emit(new Values(sentence), FileUtil.getRandomId(sentence));
		}
		else{
			FileUtil.writeToFile("Time = " + (System.nanoTime()-START)/1000000000.0);
			CounterUtil.getInstance().print();
			END = true;
		}			
	}

	@Override
	public void ack(Object msgId) {		
	}

	@Override
	public void fail(Object msgId) {			
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		 declarer.declare(new Fields("word"));			
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {			
		return null;
	}		
}