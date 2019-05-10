package com.backstabber;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackstabberTest {

	@Test
	public void testRequest() throws Exception {
		Backstabber.init("backstabber.herokuapp.com");
		JSONObject response = Backstabber.spawnConnection("ykomBpWYp3vjjYTpa", "fmdtEuksqGNrgN9tZ").execute(new JSONObject("{}"));
		assertEquals(response.getString("result"), "Hello World!");
	}

}
