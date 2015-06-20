package traffic_web.tools;

public final class Utility {
	
	public static String replaceCharsIfPossible(String text, char oldChar, char newChar)
	{
		return (text != null) ? text.replace(oldChar, newChar) : text; 
	}
}
