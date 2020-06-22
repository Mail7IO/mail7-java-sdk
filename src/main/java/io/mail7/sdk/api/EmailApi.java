/* 
 * 
 * Created by Mail7 Development Team
   Copyright 2020 Mail7.io All rights reserved.
*/

package io.mail7.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import io.mail7.sdk.helper.JsonDeserializer;
import io.mail7.sdk.helper.Mail7Request;
import io.mail7.sdk.helper.Mail7Validator;
import io.mail7.sdk.models.responsemodels.ApiResponse;
import io.mail7.sdk.models.responsemodels.EmailResponse;
import io.mail7.sdk.models.responsemodels.InboxResponse;
import io.mail7.sdk.util.AsyncHandler;
import io.mail7.sdk.util.ErrorResponse;
import io.mail7.sdk.util.Mail7SDK;

public class EmailApi {
	
	public EmailApi() {
		if (!Mail7SDK.validate()) {
			throw new Mail7SDK.InitializeException();
		}
	}

	/**
	 * 
	 * @param username
	 * @param domain
	 * @param handler
	 */
	public void getEmails(String username, String domain, final AsyncHandler<InboxResponse> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String resourcePath = "inbox";
		queryParameters.put("to", username);
		if (!Mail7Validator.isNullOrWhiteSpace(domain)) {
			queryParameters.put("domain", domain);
		}
		
		Mail7Request.execute("GET", resourcePath, queryParameters, null, new AsyncHandler<String>() {

			@Override
			public void onSuccess(String response) {
				TypeToken<InboxResponse> typeToken = new TypeToken<InboxResponse>() {
				};
				InboxResponse successResponse = JsonDeserializer.deserializeJson(response, typeToken);
				handler.onSuccess(successResponse);
			}

			@Override
			public void onFailure(ErrorResponse errorResponse) {
				handler.onFailure(errorResponse);
			}
		});
	}
	/**
	 * 
	 * @param username
	 * @param domain
	 * @param MessageId
	 * @param handler
	 */
	public void getEmailByMesssageId(String username, String domain, String MessageId, final AsyncHandler<EmailResponse> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String resourcePath = "email";
		queryParameters.put("to", username);
		queryParameters.put("mesid", MessageId);
		if (!Mail7Validator.isNullOrWhiteSpace(domain)) {
			queryParameters.put("domain", domain);
		}
		
		Mail7Request.execute("GET", resourcePath, queryParameters, null, new AsyncHandler<String>() {

			@Override
			public void onSuccess(String response) {
				TypeToken<EmailResponse> typeToken = new TypeToken<EmailResponse>() {
				};
				EmailResponse successResponse = JsonDeserializer.deserializeJson(response, typeToken);
				handler.onSuccess(successResponse);
			}

			@Override
			public void onFailure(ErrorResponse errorResponse) {
				handler.onFailure(errorResponse);
			}
		});
	}
	/**
	 * 
	 * @param username
	 * @param domain
	 * @param MessageId
	 * @param handler
	 */
	public void deleteEmailByMesssageId(String username, String domain, String MessageId, final AsyncHandler<ApiResponse> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String resourcePath = "delete";
		queryParameters.put("to", username);
		queryParameters.put("mesid", MessageId);
		if (!Mail7Validator.isNullOrWhiteSpace(domain)) {
			queryParameters.put("domain", domain);
		}
		
		Mail7Request.execute("GET", resourcePath, queryParameters, null, new AsyncHandler<String>() {

			@Override
			public void onSuccess(String response) {
				TypeToken<ApiResponse> typeToken = new TypeToken<ApiResponse>() {
				};
				ApiResponse successResponse = JsonDeserializer.deserializeJson(response, typeToken);
				handler.onSuccess(successResponse);
			}

			@Override
			public void onFailure(ErrorResponse errorResponse) {
				handler.onFailure(errorResponse);
			}
		});
	}
}