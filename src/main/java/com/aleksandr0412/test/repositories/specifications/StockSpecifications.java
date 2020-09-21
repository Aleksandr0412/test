package com.aleksandr0412.test.repositories.specifications;

import com.aleksandr0412.test.entities.Stock;
import org.springframework.data.jpa.domain.Specification;

public class StockSpecifications {
    public static Specification<Stock> titleEqual(String title) {
        return (Specification<Stock>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("title"), title);
    }

    public static Specification<Stock> priceGreaterOrEqualsThan(int maxPrice) {
        return (Specification<Stock>) (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Stock> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<Stock>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
