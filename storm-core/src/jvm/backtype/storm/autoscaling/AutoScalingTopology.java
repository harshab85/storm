package backtype.storm.autoscaling;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.generated.Bolt;
import backtype.storm.generated.SpoutSpec;
import backtype.storm.generated.StateSpoutSpec;
import backtype.storm.generated.StormTopology;

public class AutoScalingTopology extends StormTopology {

	private static final long serialVersionUID = -8836793118315671121L;
	
	public AutoScalingTopology(Map<String, SpoutSpec> spoutSpecs, Map<String, Bolt> boltSpecs, HashMap<String, StateSpoutSpec> hashMap/*, int threshold*/) {
		super(spoutSpecs, boltSpecs, hashMap);
	}
}
