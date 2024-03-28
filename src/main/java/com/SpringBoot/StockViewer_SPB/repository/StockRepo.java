package com.SpringBoot.StockViewer_SPB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBoot.StockViewer_SPB.entity.Stock;

@Repository
public interface StockRepo extends JpaRepository<Stock, String>{

}
