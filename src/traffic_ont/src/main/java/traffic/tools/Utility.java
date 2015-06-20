package traffic.tools;

import java.util.HashSet;
import java.util.List;

public final class Utility {

	@SuppressWarnings("unchecked")
	public static void removeDuplicates(List arlList)
	{
	   HashSet h = new HashSet(arlList);
	   arlList.clear();
	   arlList.addAll(h);
	}
}
