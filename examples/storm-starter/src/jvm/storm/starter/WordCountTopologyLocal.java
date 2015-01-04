package storm.starter;

import java.util.Date;
import backtype.storm.LocalCluster;
import storm.starter.bolt.CountWords;
import storm.starter.bolt.SplitSentence;
import storm.starter.spout.TestSpout;
import storm.starter.util.FileUtil;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordCountTopologyLocal {

	public static void main(String[] args) throws Exception{
				
		int spout1 = Integer.parseInt(args[0]);
		int bolt1 = Integer.parseInt(args[1]);		
		int bolt2 = Integer.parseInt(args[2]);
		
		FileUtil.writeToFile("Spout1 Tasks = " + spout1);		
		FileUtil.writeToFile("Bolt1 Tasks= " + bolt1);
		FileUtil.writeToFile("Bolt2 Tasks= " + bolt2);
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("TestSpout", new TestSpout(), spout1).setNumTasks(spout1);
		builder.setBolt("Split", new SplitSentence(), bolt1).shuffleGrouping("TestSpout").setNumTasks(bolt1);		
		builder.setBolt("Count", new CountWords(), bolt2).fieldsGrouping("Split", new Fields("word")).setNumTasks(bolt2);
		
		Config config = new Config();
		config.setDebug(true);
		
		LocalCluster cluster = new LocalCluster();
	    cluster.submitTopology("test", config, builder.createTopology());
		
		FileUtil.writeToFile("Submitted Topology : " + new Date());	    
	 }
}
