package io.mail7.sdk.util;

import java.util.Map;
import java.util.TreeMap;

public class Mail7SDK {
	private Mail7SDK() {
	}

	private static String API_HOST = "https://api.mail7.io";

	public static class Initialize {

		private static String apiKey;
		private static String apiSecret;

		public static void setApiKey(final String apiKey) {
			Initialize.apiKey = apiKey;
		}

		public static void setApiSecret(final String apiSecret) {
			Initialize.apiSecret = apiSecret;
		}

		public static void setCustomDomain(final String domain) {
			API_HOST = domain;
		}
	}

	public static String getApiKey() {
		return Initialize.apiKey;
	}

	public static String getApiSecret() {
		return Initialize.apiSecret;
	}

	public static String getDomain() {
		return API_HOST;
	}

	public static boolean validate() {
		return Initialize.apiKey == null || Initialize.apiKey.length() == 0 || Initialize.apiSecret == null
				|| Initialize.apiSecret.length() == 0 ? false : true;
	}

	@SuppressWarnings("serial")
	public static class InitializeException extends RuntimeException {
		public InitializeException() {
			super("Mail7 SDK not initialized properly");
		}
	}

	/**
	 * Creates url after appending loginradius api root url and query parameters
	 * 
	 * @param url       url for appending to the api url
	 * @param queryArgs extra parameters for sending with url
	 * @return complete url for fetching data
	 */
	public static String getRequestUrl(String url, final Map<String, String> queryArgs) {
		String keyvalueString = "";
		if (queryArgs != null && !queryArgs.isEmpty()) {
			keyvalueString = createKeyValueString(queryArgs);
		}
		return url.contains("?") ? url + "&" + keyvalueString : url + "?" + keyvalueString;
	}

	/**
	 * Creates key-value string
	 * 
	 * @param queryArgs parameters that will attach to the url
	 * @return query string with the given parameters
	 */
	public static String createKeyValueString(final Map<String, String> queryArgs) {
		String[] sb = new String[queryArgs.size()];
		int i = 0;
		for (Map.Entry<String, String> entry : queryArgs.entrySet()) {
			sb[i] = entry.getKey() + "=" + entry.getValue();
			i++;
		}
		return combine(sb, "&");
	}

	/**
	 * Combine to create key-value string
	 * 
	 * @param s    Array String where the glue will be appended
	 * @param glue String to be appended with Array String
	 * @return appended String
	 */
	public static String combine(String[] s, final String glue) {
		int k = s.length;
		StringBuilder out = new StringBuilder();
		out.append(s[0]);
		for (int x = 1; x < k; ++x)
			out.append(glue).append(s[x]);
		return out.toString();
	}

	/**
	 * Replaces placeholders in path String if necessary
	 *
	 * @param path path for endpoint
	 * @param map  input parameter map
	 * @return String with placeholders replaced (if necessary)
	 */
	public static String getFinalPath(String path, final Map<String, String> map) {
		String finalPath = path;
		Map<String, String> data = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
		if (map != null && !map.isEmpty()) {
			data.putAll(map);
		}
		if (isPlaceholders(path)) {
			finalPath = replacePlaceholders(path, data);
		}
		return finalPath;
	}

	/**
	 * Checks whether a path contains a placeholder in the form {{...}}
	 *
	 * @param path path to check
	 * @return true if contains placeholder, otherwise false
	 */
	private static Boolean isPlaceholders(final String path) {
		return path.contains("{{") && path.contains("}}");
	}

	/**
	 * Replaces placeholders in the form {{...}} with the corresponding field.
	 * ensures map values with {{ | }} will not effect the result
	 *
	 * @param path path containing placeholders
	 * @param data map containing field value (pass treemap for map with
	 *             case-insensitive keys)
	 * @return string with placeholders replaced
	 */
	private static String replacePlaceholders(String path, final Map<String, String> data) {
		String res = path;
		String[] arr = res.split("/");
		for (int i = 0; i < arr.length; i++) {
			if (isPlaceholders(arr[i])) {
				String field = arr[i].substring(arr[i].indexOf("{{") + 2, arr[i].indexOf("}}"));
				arr[i] = data.get(field);
			}
		}
		return join(arr);
	}

	/**
	 * Join array of strings with delimitor "/"
	 *
	 * @param arr
	 * @return string
	 */
	private static String join(final String[] arr) {
		String res = "";
		for (int i = 0; i < arr.length; i++) {
			res += arr[i];
			if (i != arr.length - 1) {
				res += "/";
			}
		}
		return res;
	}
}
