package com.SpringBoot.StockViewer_SPB.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
public class Stock {

	@Id
	private String symbol;
	
	private String series;
	
	private double open;
	
	private double high;
	
	private double low;
	
	private double close;
	
	private double last;
	
	private double prevclose;
	
	private long tottrdqty;
	
	private double tottrdval;
	
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	
	private long totaltrades;
	
	private String isin;

}
