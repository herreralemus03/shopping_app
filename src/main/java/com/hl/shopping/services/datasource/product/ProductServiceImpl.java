package com.hl.shopping.services.datasource.product;

import com.hl.shopping.entites.Product;
import com.hl.shopping.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductRepository productRepository;

    public ProductServiceImpl(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllListed() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllPaged(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product delete(Product product) {
        Product current = this.findById(product.getId());
        productRepository.delete(current);
        return current;
    }
}
