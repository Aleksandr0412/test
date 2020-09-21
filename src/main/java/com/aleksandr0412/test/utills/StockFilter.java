package com.aleksandr0412.test.utills;

import com.aleksandr0412.test.entities.Stock;
import com.aleksandr0412.test.repositories.specifications.StockSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;

@Getter
public class StockFilter {
    private Specification<Stock> spec;

    public StockFilter(MultiValueMap<String, String> params) {
        spec = Specification.where(null);
        if (params.containsKey("minPrice") && !params.get("minPrice").get(0).equals("")) {
            String minPrice = params.get("minPrice").get(0);
            spec = spec.and(StockSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(minPrice)));
        }
        if (params.containsKey("maxPrice") && !params.get("maxPrice").get(0).equals("")) {
            String maxPrice = params.get("maxPrice").get(0);
            spec = spec.and(StockSpecifications.priceLesserOrEqualsThan(Integer.parseInt(maxPrice)));
        }
        if (params.containsKey("title") && !params.get("title").get(0).equals("")) {
            String title = params.get("title").get(0);
            spec = spec.and(StockSpecifications.titleEqual(title));
        }
    }
}
