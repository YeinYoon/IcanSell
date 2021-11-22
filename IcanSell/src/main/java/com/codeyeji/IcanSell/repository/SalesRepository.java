package com.codeyeji.IcanSell.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codeyeji.IcanSell.data.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>{

	@Query(value="SELECT * FROM Sales WHERE DATE(s_date) BETWEEN :startDate AND :endDate AND s_name LIKE CONCAT('%',:drinkName,'%') ORDER BY s_date ASC", nativeQuery = true)
	List<Sales> findSales (
			@Param("drinkName") String drinkName, 
		    @Param("startDate") String startDate,
		    @Param("endDate") String endDate
	);
	
	@Query(value="SELECT * FROM Sales WHERE DATE(s_date) BETWEEN :startDate AND :endDate ORDER BY s_date ASC", nativeQuery = true)
	List<Sales> findSalesDate (
		    @Param("startDate") String startDate,
		    @Param("endDate") String endDate
	);
}
