package com.fabrice;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class memory {
	
	private static volatile HashMap<String, User> map;
	private static volatile int calls = 0;
	
	private static void initiate() {
		if(map == null) map = new LinkedHashMap<>();
		calls++;
		System.out.println("Memory map size: "+ map.size() +" @ call count: "+ calls);
	}
	
	public static User getUser(String s) {
		initiate();
		return map.get(s);
	}
	
	public static void addUser(String s, User u) {
		initiate();
		map.put(s, u);
	}
}
