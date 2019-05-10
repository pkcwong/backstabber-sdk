package com.backstabber;

public class Backstabber {

	private static String host = "backstabber.herokuapp.com";

	private Backstabber() {

	}

	static void init() {
		Backstabber.init("backstabber.herokuapp.com");
	}

	static void init(String host) {
		Backstabber.host = host;
	}

	static Request spawnConnection(String api, String token) {
		return new Request(Backstabber.host, api, token);
	}

}
