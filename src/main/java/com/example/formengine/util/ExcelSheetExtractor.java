package com.example.formengine.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.example.formengine.beans.ContentDetails;
import com.example.formengine.beans.ExcelSheetBO;

@Service
public class ExcelSheetExtractor {
	private static Logger LOGGER = LogManager.getLogger(ExcelSheetExtractor.class);

	@SuppressWarnings("static-access")
	public ExcelSheetBO extractSheetToBo(File file) {
		XSSFWorkbook workbook = null;
		ExcelSheetBO excelSheetBO = new ExcelSheetBO();
		Map<String, String> rowDetails = null;
		ContentDetails contentDetails = null;
		Map<Integer, String> headers = new HashMap<Integer, String>();
		Map<String, String> labelDetails = new HashMap<String, String>();
		Map<String, String> getTypes = EngineConstants.LABELTYPES;
		List<ContentDetails> listContents = new ArrayList<>();
		try {
			if (file.exists()) {
				workbook = new XSSFWorkbook(new FileInputStream(file));
				XSSFSheet sheet = workbook.getSheetAt(0);

				for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
					XSSFRow row = sheet.getRow(i);
					Iterator<Cell> cellIterator = row.cellIterator();
					contentDetails = new ContentDetails();
					rowDetails = new LinkedHashMap<>();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						if (cell.getRowIndex() != 0) {
							if (cell.getCellType() == cell.CELL_TYPE_STRING) {
								rowDetails.put(headers.get(cell.getColumnIndex()), cell.getStringCellValue());
							} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
								rowDetails.put(headers.get(cell.getColumnIndex()),
										Integer.toString((int) cell.getNumericCellValue()));
							} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
								rowDetails.put(headers.get(cell.getColumnIndex()),
										Boolean.toString(cell.getBooleanCellValue()));
							}

							contentDetails.setLabelMapDetails(rowDetails);

						} else {
							if (getTypes.get(cell.getStringCellValue()) != null) {
								labelDetails.put(cell.getStringCellValue(), getTypes.get(cell.getStringCellValue()));
							} else {
								labelDetails.put(cell.getStringCellValue(), getTypes.get("default"));
							}

							headers.put(cell.getColumnIndex(), cell.getStringCellValue());
						}
					}
					if (row.getRowNum() != 0)
						listContents.add(contentDetails);
				}
				excelSheetBO.setContentDetails(listContents);
				excelSheetBO.setLabelDetails(labelDetails);

			}
		} catch (Exception e) {
			LOGGER.error("Exception while extracting xlsx sheet to BO", e);
		}

		return excelSheetBO;

	}

	public String getOptionalHtmlString(String selected) {
		String response = "";
		for (String str : EngineConstants.OPTIONALVALUES) {
			if (!str.equalsIgnoreCase(selected)) {
				response += "<option value=\"" + str + "\">" + str + "</option>";
			} else {
				response += "<option value=\"" + str + "\" selected=\"selected\">" + str + "</option>";
			}
		}
		return response;
	}
}
