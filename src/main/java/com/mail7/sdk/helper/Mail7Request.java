package com.mail7.sdk.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.reflect.TypeToken;
import com.mail7.sdk.util.AsyncHandler;
import com.mail7.sdk.util.ErrorResponse;
import com.mail7.sdk.util.Mail7SDK;

public class Mail7Request {
	private Mail7Request() {
	}

	private static final String encoding = "UTF-8";

	static ErrorResponse errorResponse;
	private static Integer code = 0;

	public static void execute(String method, String resourcePath, Map<String, String> params, String payload,
			final AsyncHandler<String> asyncHandler) {
		String serviceUrl = Mail7SDK.getDomain() + "/" + resourcePath;
		params.put("apikey", Mail7SDK.getApiKey());
		params.put("apisecret", Mail7SDK.getApiSecret());
		String task = Mail7RequestRunner(method, Mail7SDK.getRequestUrl(serviceUrl, params), payload);
		if (code == 200) {
			asyncHandler.onSuccess(task);
		} else {
			ErrorResponse errorResponse = exception(task);
			asyncHandler.onFailure(errorResponse);
		}
	}

	private static String Mail7RequestRunner(String method, String url, String payload) {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(15000); // set timeout to 15 seconds
			con.setReadTimeout(15000);
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");

			con.setDoOutput(true);
			if (!method.equals("GET")) {
				OutputStream os = con.getOutputStream();
				OutputStreamWriter body = new OutputStreamWriter(os, encoding);
				String p = payload != null ? payload : "{}";
				body.write(p);
				body.flush();
				body.close();
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				code = responseCode;
				return readStream(con.getInputStream(), con.getContentEncoding());
			} else {
				code = responseCode;
				return readStream(con.getErrorStream(), con.getContentEncoding());
			}

		} catch (UnknownHostException e) {
			code = 101;
			return e.toString();
		} catch (IllegalArgumentException e) {
			code = 102;
			return e.toString();
		} catch (MalformedURLException e) {
			code = 103;
			return e.toString();
		} catch (SocketTimeoutException e) {
			code = 104;
			return e.toString();
		} catch (IOException e) {
			code = 105;
			return e.toString();
		}
	}

	private static String readStream(InputStream in, String en) {
		BufferedReader reader = null;
		StringBuilder response = new StringBuilder();
		try {
			if ("gzip".equals(en)) {
				reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(in)));
			} else {
				reader = new BufferedReader(new InputStreamReader(in));
			}
			String line = "";
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response.toString();
	}

	public static String encode(String key, final String data) {
		String s = null;
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(encoding), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			s = Base64.encodeBase64String(sha256_HMAC.doFinal(data.getBytes(encoding)));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return s;
	}

	private static ErrorResponse exception(String error) {
		ErrorResponse obj = new ErrorResponse();
		switch (code) {
		case 101:
			obj.setMessage(error);
			obj.setStatus("UnknownHostException");
			break;
		case 102:
			obj.setMessage(error);
			obj.setStatus("IllegalArgumentException");
			break;
		case 103:
			obj.setMessage(error);
			obj.setStatus("MalformedURLException");
			break;
		case 104:
			obj.setMessage(error);
			obj.setStatus("SocketTimeoutException");
			break;
		case 105:
			obj.setMessage(error);
			obj.setStatus("IOException");
			break;
		default:
			TypeToken<ErrorResponse> typeToken = new TypeToken<ErrorResponse>() {
			};
			obj = JsonDeserializer.deserializeJson(error, typeToken);
			break;
		}

		return obj;
	}
}
