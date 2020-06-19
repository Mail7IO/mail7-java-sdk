/* 
 * 
 * Created by Mail7 Development Team
   Copyright 2020 Mail7.io All rights reserved.
*/

package com.mail7.sdk.api;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mail7.sdk.helper.JsonDeserializer;
import com.mail7.sdk.helper.Mail7Request;
import com.mail7.sdk.helper.Mail7Validator;
import com.mail7.sdk.models.responsemodels.RoutingRule;
import com.mail7.sdk.models.responsemodels.ApiResponse;
import com.mail7.sdk.util.AsyncHandler;
import com.mail7.sdk.util.ErrorResponse;
import com.mail7.sdk.util.Mail7SDK;

public class RoutingRuleApi {
	private static Gson gson = new Gson();

	public RoutingRuleApi() {
		if (!Mail7SDK.validate()) {
			throw new Mail7SDK.InitializeException();
		}
	}

	/**
	 * 
	 * @param handler
	 */
	public void getRoutingRules(final AsyncHandler<RoutingRule> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String resourcePath = "rules";

		Mail7Request.execute("GET", resourcePath, queryParameters, null, new AsyncHandler<String>() {

			@Override
			public void onSuccess(String response) {
				TypeToken<RoutingRule> typeToken = new TypeToken<RoutingRule>() {
				};
				RoutingRule successResponse = JsonDeserializer.deserializeJson(response, typeToken);
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
	 * @param payload
	 * @param handler
	 */
	public void createUpdateRoutingRules(JsonObject payload, final AsyncHandler<ApiResponse> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		String resourcePath = "rules";

		Mail7Request.execute("POST", resourcePath, queryParameters, gson.toJson(payload), new AsyncHandler<String>() {

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

	/**
	 * 
	 * @param ruleName
	 * @param handler
	 */
	public void deleteRoutingRules(String ruleName, final AsyncHandler<ApiResponse> handler) {

		Map<String, String> queryParameters = new HashMap<String, String>();

		if (!Mail7Validator.isNullOrWhiteSpace(ruleName)) {
			queryParameters.put("name", ruleName);
		}

		String resourcePath = "rules";

		Mail7Request.execute("DELETE", resourcePath, queryParameters, null, new AsyncHandler<String>() {

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
