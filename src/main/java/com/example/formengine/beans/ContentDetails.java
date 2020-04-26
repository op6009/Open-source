package com.example.formengine.beans;

import java.util.Map;

public class ContentDetails {
	private Map<String, String> labelMapDetails;

	public Map<String, String> getLabelMapDetails() {
		return labelMapDetails;
	}

	public void setLabelMapDetails(Map<String, String> labelMapDetails) {
		this.labelMapDetails = labelMapDetails;
	}

	@Override
	public String toString() {
		return "ContentDetails [labelMapDetails=" + labelMapDetails + "]";
	}

}
