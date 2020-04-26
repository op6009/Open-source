package com.example.formengine.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.formengine.business.FormEngineService;

@Controller
public class RestController {
	@Autowired
	FormEngineService formEngineService;

	@RequestMapping(value = "/landingPage", method = RequestMethod.GET)
	public String showLandingPage(ModelMap model) {
		return "landingPage";
	}

	@RequestMapping(value = "/uploadExcelSheet", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String uploadExcelSheet(@RequestParam("file") MultipartFile file, ModelMap model) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		String htmlString = formEngineService.getHtmlAsString(convFile);
		model.addAttribute("data", htmlString);
		return "responseForm";
	}

}
