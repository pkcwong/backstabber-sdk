package com.backstabber;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Request {

	private String host;
	private String api;
	private String token;

	protected Request(String host, String api, String token) {
		this.host = host;
		this.api = api;
		this.token = token;
	}

	public JSONObject execute() throws Exception {
		return this.execute(new JSONObject());
	}

	public JSONObject execute(JSONObject body) throws Exception {
		URL url;
		url = new URL("http://" + URLEncoder.encode(this.host, "UTF-8") + "/api/program/" + URLEncoder.encode(this.api, "UTF-8"));
		HttpURLConnection urlConnection = (HttpURLConnection) (url.openConnection());
		urlConnection.setRequestMethod("POST");
		urlConnection.setRequestProperty("Content-Type", "application/json");
		urlConnection.setRequestProperty("token", this.token);
		urlConnection.setConnectTimeout(10000);
		urlConnection.setDoOutput(true);
		urlConnection.setDoOutput(true);
		OutputStream outputStream = urlConnection.getOutputStream();
		outputStream.write(body.toString().getBytes());
		outputStream.flush();
		outputStream.close();
		if (urlConnection.getResponseCode() != 200) {
			throw new Exception(String.valueOf(urlConnection.getResponseCode()));
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line;
		StringBuilder response = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		bufferedReader.close();
		return new JSONObject(response.toString());
	}

}
