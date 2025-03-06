package vn.minhduc.laptopshop.service;

import org.springframework.stereotype.Service;
import vn.minhduc.laptopshop.domain.Product;
import vn.minhduc.laptopshop.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

}
