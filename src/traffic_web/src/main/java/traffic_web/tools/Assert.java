package traffic_web.tools;

public final class Assert {

	public static boolean isNullOrEmpty(String text)
	{
		return ((text == null) || (text != null && text.isEmpty()));
	}
}
