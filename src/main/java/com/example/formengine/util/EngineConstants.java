package com.example.formengine.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EngineConstants {
	@SuppressWarnings("serial")
	static final HashMap<String, String> LABELTYPES = new HashMap<String, String>() {
		{
			put("First Name", "text");
			put("Last Name", "text");
			put("Hobby", "optional");
			put("default", "text");
		}
	};
	@SuppressWarnings("serial")
	static final List<String> OPTIONALVALUES = new ArrayList<String>() {
		{
			add("Singing");
			add("Reading");
			add("Travelling");
		}
	};
	public static final String OPTIONALDROPDOWN = "optional";
}
