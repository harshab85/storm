package storm.starter;

import storm.starter.bolt.CountWords;
import storm.starter.bolt.SplitSentence;
import storm.starter.spout.TestSpout;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordCountTopologyRemote {

	public static void main(String[] args) throws Exception{				
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new TestSpout()).setNumTasks(10);
		builder.setBolt("split", new SplitSentence()).setNumTasks(10).shuffleGrouping("spout");		
		builder.setBolt("count", new CountWords()).setNumTasks(10).fieldsGrouping("split", new Fields("word"));
		
		Config config = new Config();
		config.setDebug(true);
		
		StormSubmitter.submitTopologyWithProgressBar("autoscaletest", config, builder.createAutoScaleTopology());	    
	 }
}
