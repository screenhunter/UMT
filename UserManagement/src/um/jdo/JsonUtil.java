package um.jdo;

/**
 * 
 * A few handy utilities for generating JSON
 * 
 * @author rajatkhanna
 *
 */

public class JsonUtil {
	
	/**
	 * Replace a backslash in the string with a double backslash.
	 * @param string The String to be escaped
	 * @return the processed result
	 */
	public static String escapeJsonString(String string) {
		return string.replace("\\", "\\\\");
	}

	/**
	 * Take a string and convert it in a JSON string to be returned as a result.
	 * @param string The initial string
	 * @return The escaped string.
	 */
	public static String toJsonString(String string) {
		if (string == null)
			return null;
		return "\"" + escapeJsonString(string) + "\"";
	}
	
	/**
	 * Take an exception and convert it into a JSON string to be returned as a result
	 * @param e The exception
	 * @return The escaped string
	 */
	public static String exceptionToJsonString(Exception e) {
		String json = "{";
		json += "\"errorMessage\":" + JsonUtil.toJsonString(e.getMessage());
		json += "}";
		return json;
	}
	
}
