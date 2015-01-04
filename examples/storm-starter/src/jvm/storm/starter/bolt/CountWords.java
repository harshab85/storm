package storm.starter.bolt;

import java.util.Map;

import storm.starter.util.CounterUtil;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;


public class CountWords implements IRichBolt{

	private static final long serialVersionUID = 1L;
	
	private OutputCollector _collector;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {					
		_collector = collector;
	}

	@Override
	public void execute(Tuple tuple) {  	
		String word = tuple.getString(0);
		CounterUtil.getInstance().addWord(word);
	   	_collector.ack(tuple);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word", "count"));
	}
		
	@Override
	public void cleanup() {
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}
}