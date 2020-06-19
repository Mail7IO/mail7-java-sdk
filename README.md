  
# Mail7 Java SDK
Mail7 Java library form disposable email service. That's  help to Automat Selenium, cucumber and Java related application.

## Introduction ##

Testing email workflow is a painful and time sucking task for both software testers and software developers. With our instant disposal email creation and automation, Mail7 makes it effortless and accurate. We have over 50,000 testers and developers using our product and testing 1 million emails per month.

Please visit [here](https://www.mail7.io/) for more information.


# Installing
## Manual Installation
1.  Right click on your project in eclipse.
2.  Select **Build Path**.
3.  Click on **Configure Build Path**.
4.  Click on **Libraries** and select **Add External JARs**.
5.  Select the jar file from your computer.
6.  Hit and **Apply** button.

## Automatic Installation
[Mail7.io](https://www.mail7.io) is now using Maven. At present the jars **are** available from a public [maven]( http://search.maven.org/#search%7Cga%7C1%7Cmail7) repository.

Use the following dependency in your project:

```
<dependency>
  <groupId>com.mail7.sdk</groupId>
  <artifactId>java-sdk</artifactId>
  <version>1.0.0</version>
</dependency>

```

The jars are also available [here](http://search.maven.org/#search%7Cga%7C1%7Cmail7). Select the directory for
the latest version and download the jar files.

## Documentation

Java Library
=====

-----

>**Disclaimer**
>This library is meant to help you with a quick implementation of the Mail7 platform and also to serve as a reference point for the Mail7 API. Keep in mind that it is an open source library, which means you are free to download and customize the library functions based on your specific application needs.



## Installation

This documentation presumes you have worked through java and eclipse libararies. API Details on this can be found in the [Document](https://docs.mail7.io/overview).

Use the following dependency in your project:

You can also compile the source by running the following commands. This will generate the javadocs in java-sdk/target/apidocs


```
$ git clone https://github.com/Mail7/java-sdk.git
```
```
$ cd java-sdk
```
`$ mvn install` # Requires maven, download from http://maven.apache.org/download.html
  
`$ mvn dependency:copy-dependencies`   # This will generate all dependencies here: java-sdk/target/dependency
The jars are also available at [Maven](https://mvnrepository.com/artifact/com.mail7.sdk/java-sdk).

Select the directory for the latest version and download the jar files.

## Initialize SDK
Before using the SDK, you must initialize the SDK with the help of following code:
API Key and secret of your Mail7.io site. You can get one from [here](http://console.mail7.io/)

```
Mail7SDK.Initialize init = new Mail7SDK.Initialize();
init.setApiKey("___MAIL7_APIKEY___");
init.setApiSecret("___MAIL7_APISECRET___");
```
## Quickstart Guide


### Emails API


List of APIs in this Section:

 - GET : [Get Emails](https://docs.mail7.io/mail-apis/get-email)
	  This API is used to get all emails are available in email account.
	  ```
	  EmailApi Mail7EmailAPI = new EmailApi();

        Mail7EmailAPI.getEmails(username, domain, new AsyncHandler<InboxResponse>() {
		@Override
		public void onSuccess(InboxResponse response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}
	});

	  ```
 - GET : [Get Email By Message ID](https://docs.mail7.io/mail-apis/get-single-email)
 	  This API is used to get single emails are available in email account Message ID.
 	  ```
 	  EmailApi Mail7EmailAPI = new EmailApi();

      Mail7EmailAPI.getEmailByMesssageId(username, domain, messageId, new AsyncHandler<EmailResponse>() {
		@Override
		public void onSuccess(EmailResponse response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}
	});

 	  ```
 - GET : [Delete Email By Message ID](https://docs.mail7.io/mail-apis/delete-email)
  	  This API is used to delete single emails are available in email account by Message ID.
  	  ```
  	   EmailApi Mail7EmailAPI = new EmailApi();

       Mail7EmailAPI.deleteEmailByMesssageId(username, domain, messageId, new AsyncHandler<ApiResponse>() {
		@Override
		public void onSuccess(ApiResponse response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}
	});

  	  ```

### Routing Rules API

List of APIs in this Section:
- GET : [Get Rules](https://docs.mail7.io/routing-rules/get-rules)
  	  This API is used to Get Routing Rules are available in your account.
  	  
 ```
  RoutingRuleApi Mail7RoutingRuleAPI = new RoutingRuleApi();
  Mail7RoutingRuleAPI.getRoutingRules(new AsyncHandler<RoutingRule>() {
		@Override
		public void onSuccess(RoutingRule response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}});
```

- POST : [Create/Update Routing Rules](https://docs.mail7.io/routing-rules/create-update-rule)
  	  This API is used to Create / Update Routing Rules in your Mail7 account.
  	  
 ```
  RoutingRuleApi Mail7RoutingRuleAPI = new RoutingRuleApi();
  Mail7RoutingRuleAPI.createUpdateRoutingRules(payload, new AsyncHandler<ApiResponse>() {
		@Override
		public void onSuccess(ApiResponse response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}
	});
```

- DELETE : [Delete Routing Rules](https://docs.mail7.io/routing-rules/delete-rule)
  	  This API is used to Delete Routing Rules in your Mail7 account.
  	  
 ```
  RoutingRuleApi Mail7RoutingRuleAPI = new RoutingRuleApi();
  Mail7RoutingRuleAPI.deleteRoutingRules(ruleName, new AsyncHandler<ApiResponse>() {
		@Override
		public void onSuccess(ApiResponse response) {
			System.out.printlngson.toJson(response));
		}
		@Override
		public void onFailure(ErrorResponse error) {
			System.out.println(error.getMessage());
		}
	});

```
  	  
## Reference Manual

Please find the reference manual [here](http://docs.mail7.io/).