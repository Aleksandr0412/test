package com.aleksandr0412.test.controllers;

import com.aleksandr0412.test.entities.Stock;
import com.aleksandr0412.test.exceptions.ResourceNotFoundException;
import com.aleksandr0412.test.service.StockService;
import com.aleksandr0412.test.utills.StockFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private StockService service;

    @Autowired
    public void setTaskService(StockService service) {
        this.service = service;
    }

    @GetMapping
    public List<Stock> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(required = false) MultiValueMap<String, String> params) {
        StockFilter stockFilter = new StockFilter(params);

        return service.findAll(stockFilter.getSpec(), page - 1, 5).getContent();
    }

    @GetMapping("/{id}")
    public Stock getTById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestParam Long id) {
        if (!service.existsById(id)) {
            throw new RuntimeException(String.format("Акция с id= %d не найдена", id));
        }
        service.deleteById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Stock createNewStock(@RequestBody Stock stock) {
        if (stock.getId() != null) {
            stock.setId(null);
        }
        return service.saveOrUpdate(stock);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public void modifyStock(@RequestBody Stock stock) {
        if (!service.existsById(stock.getId())) {
            throw new ResourceNotFoundException(String.format("Акция с id= %d не найдена", stock.getId()));
        }
        service.saveOrUpdate(stock);
    }
}
