package storm.starter.bolt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public  class SplitSentence implements IRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754895560377935697L;
	OutputCollector _collector;
		
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		_collector = collector;			
	}

	@Override
	public void execute(Tuple input) {
			
		String sentence = input.getString(0);
		
		String[] split = sentence.split(" ");	
		
		final List<Object> values = new ArrayList<Object>(split.length);
		for(final String s: split){
			values.add(s);
			_collector.emit(input, new Values(s));
		}
		
		_collector.ack(input);
	}

	@Override
	public void cleanup() {
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