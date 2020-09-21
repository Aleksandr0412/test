package com.aleksandr0412.test.service;

import com.aleksandr0412.test.entities.Stock;
import com.aleksandr0412.test.exceptions.ResourceNotFoundException;
import com.aleksandr0412.test.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private StockRepository repository;

    @Autowired
    public void setRepository(StockRepository repository) {
        this.repository = repository;
    }

    public Stock findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock with id: " + id + " not found"));
    }

    public Page<Stock> findAll(Specification<Stock> spec, int page, int size) {
        return repository.findAll(spec, PageRequest.of(page, size));
    }

    public Stock saveOrUpdate(Stock stock) {
        return repository.save(stock);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
