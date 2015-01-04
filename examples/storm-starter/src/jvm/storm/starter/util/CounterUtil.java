package storm.starter.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CounterUtil{
	
	private Map<String, Integer> counter = new HashMap<String, Integer>();
	private static CounterUtil INSTANCE = new CounterUtil();
	private boolean printed = false;
		
	private CounterUtil(){
	}
	
	public static CounterUtil getInstance(){
		return INSTANCE;
	}
	
	public void addWord(String word){
		
		if(word == null){
			return;
		}
		
		word = word.trim();
		if(word.isEmpty()){
			return;
		}
		
		word = word.toLowerCase();
		
		synchronized (INSTANCE) {				
			if(counter.containsKey(word)){		
				counter.put(word, (counter.get(word) + 1) );
			}
			else{
				counter.put(word, 1);
			}
		}
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return -(o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}	
	
	public synchronized void print(){
		
		FileUtil.writeToFile("Printing");
		
		if(printed){
			return;
		}
		
		printed = true;				
		
		Map<String, Integer> m = sortByValue(counter);
		
		Iterator<String> iter = m.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			Integer value = m.get(key);			
			FileUtil.writeToFile(key + " " + value);			
		}
	}
		
}