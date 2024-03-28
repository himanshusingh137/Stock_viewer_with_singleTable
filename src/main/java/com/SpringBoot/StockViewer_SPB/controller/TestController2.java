package com.SpringBoot.StockViewer_SPB.controller;




import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.StockViewer_SPB.service.StockService;


@Controller
public class TestController2 {

	@Autowired
	StockService stockService2;

    @PostMapping("/upload-csv")
    public ResponseEntity<Map<String, Object>> uploadCSV(@RequestParam("file") MultipartFile file) {
    	
        Map<String, Object> response = new HashMap();
        try {
            if (file.isEmpty()) {
                response.put("success", false);
                response.put("message", "Please select a file to upload.");
                return ResponseEntity.badRequest().body(response);
            }

            boolean success = stockService2.uploadCSV(file);

            if (success) {
                response.put("success", true);
                response.put("message", "File uploaded successfully!");
            } else {
                response.put("success", false);
                response.put("message", "Failed to process the file.");
            }
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred while uploading the file.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

	@GetMapping("/result")
	public String showResult(Model model) {
		// Add any necessary model attributes here before showing the result page
		return "result"; // Return the result page
	}

	@RequestMapping("/home")
	public String home(Model model) {
		return "Upload";
	}

	@RequestMapping("/oye")
	public String oye() {
		return "Details";
	}

}

