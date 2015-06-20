package traffic_web.tools;

import java.util.Comparator;
import java.util.Map.Entry;

public class StringKeyEntryComparator implements Comparator<Entry<String, Boolean>> {

	@Override
	public int compare(Entry<String, Boolean> o1, Entry<String, Boolean> o2) {
		return o1.getKey().compareTo(o2.getKey());
	}

}
