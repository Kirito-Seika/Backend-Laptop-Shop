package vn.minhduc.laptopshop.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.minhduc.laptopshop.domain.Product;
import vn.minhduc.laptopshop.domain.Product_;

public class ProductSpecification {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }
}
