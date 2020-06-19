/* 
 * 
 * Created by Mail7 Development Team
   Copyright 2019 Mail7 All rights reserved.
   
 */

package com.mail7.sdk.models.responsemodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// <summary>
//	Response containing Definition of Complete Profile data
// </summary>
public class EmailResponse {

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("data")
	@Expose
	private Object data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}