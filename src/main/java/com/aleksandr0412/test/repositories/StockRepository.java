package com.aleksandr0412.test.repositories;

import com.aleksandr0412.test.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long>, JpaSpecificationExecutor<Stock> {
}
