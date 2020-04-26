package com.example.formengine.service;

import java.io.File;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.formengine.beans.ContentDetails;
import com.example.formengine.beans.ExcelSheetBO;
import com.example.formengine.business.FormEngineService;
import com.example.formengine.util.EngineConstants;
import com.example.formengine.util.ExcelSheetExtractor;

@Service
public class FormEngineServiceImpl implements FormEngineService{
	@Autowired
	ExcelSheetExtractor excelSheetExtractor;

	private static Logger LOGGER = LogManager.getLogger(FormEngineServiceImpl.class);

	public String getHtmlAsString(File convFile) {
		ExcelSheetBO excelSheetBO = null;
		Map<String, String> labels = null;
		String htmlString = "";
		try {

			excelSheetBO = excelSheetExtractor.extractSheetToBo(convFile);
			labels = excelSheetBO.getLabelDetails();
			for (ContentDetails content : excelSheetBO.getContentDetails()) {
				for (Map.Entry<String, String> insidevalues : content.getLabelMapDetails().entrySet()) {
					if (!EngineConstants.OPTIONALDROPDOWN.equalsIgnoreCase(labels.get(insidevalues.getKey()))) {
						htmlString += "<label for= \"" + insidevalues.getKey() + "\"><b>" + insidevalues.getKey()
								+ "</b></label>" + "<input type=\"" + labels.get(insidevalues.getKey()) + "\" name=\""
								+ insidevalues.getKey() + "\" value=\"" + insidevalues.getValue() + "\"> <br>";
					} else {
						htmlString += "<label for= \"" + insidevalues.getKey() + "\"><b>" + insidevalues.getKey()
								+ "</b></label>" + "<select id=\"" + insidevalues.getKey() + "\">"
								+ excelSheetExtractor.getOptionalHtmlString(insidevalues.getValue()) + "</select><br>";

					}

				}
				htmlString += "<hr>";
			}

		} catch (Exception e) {
			LOGGER.error("Exception while coverting data to html string", e);
		}
		return htmlString;
	}
}
