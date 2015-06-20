package traffic.tools;

import java.util.Map.Entry;

public class MapEntry<K,V> implements Entry<K,V> {

	private K key;
	private V value;
	
	public MapEntry(K key, V value)
	{
		this.key = key;
		this.value = value;
	}
	
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		return this.value = value;
	}
}
