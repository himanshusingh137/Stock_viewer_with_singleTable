package com.SpringBoot.StockViewer_SPB.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.SpringBoot.StockViewer_SPB.entity.Stock;
import com.SpringBoot.StockViewer_SPB.repository.StockRepo;

@Service
public class StockService {

	@Autowired
	private StockRepo stockRepo;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	public boolean uploadCSV(MultipartFile file) {

		boolean processed;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {

			String line;

			List<Stock> stocks = new ArrayList();

			boolean firstLineSkipped = false; // Flag to skip the first line

			while ((line = br.readLine()) != null) {

				if (!firstLineSkipped) {
					firstLineSkipped = true;
					continue; // Skip the first line
				}

				String[] data = line.split(","); 
				
				if (data[1].equals("EQ")) { // Check if series equals "EQ"
					Stock stock = new Stock();
					stock.setSymbol(data[0]);
					stock.setSeries(data[1]);
					stock.setOpen(Double.parseDouble(data[2]));
					stock.setHigh(Double.parseDouble(data[3]));
					stock.setLow(Double.parseDouble(data[4]));
					stock.setClose(Double.parseDouble(data[5]));
					stock.setLast(Double.parseDouble(data[6]));
					stock.setPrevclose(Double.parseDouble(data[7]));
					stock.setTottrdqty(Long.parseLong(data[8]));
					stock.setTottrdval(Double.parseDouble(data[9]));
					stock.setTimestamp(dateFormat.parse(data[10]));
					stock.setTotaltrades(Long.parseLong(data[11]));
					stock.setIsin(data[12]);

					stocks.add(stock);
				}
			}

			List<Stock> fileSaved = stockRepo.saveAll(stocks);

			if (fileSaved != null) {
				processed = true;
			} else {
				processed = false;
			}

		} catch (Exception e) {
			throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
		}

		return processed;

	}

}
