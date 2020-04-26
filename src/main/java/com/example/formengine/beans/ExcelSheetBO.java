package com.example.formengine.beans;

import java.util.List;
import java.util.Map;

public class ExcelSheetBO {
	private Map<String, String> labelDetails;

	private List<ContentDetails> contentDetails;

	public Map<String, String> getLabelDetails() {
		return labelDetails;
	}

	public void setLabelDetails(Map<String, String> labelDetails) {
		this.labelDetails = labelDetails;
	}

	public List<ContentDetails> getContentDetails() {
		return contentDetails;
	}

	public void setContentDetails(List<ContentDetails> contentDetails) {
		this.contentDetails = contentDetails;
	}

	@Override
	public String toString() {
		return "ExcelSheetBO [labelDetails=" + labelDetails + ", contentDetails=" + contentDetails + "]";
	}

}
