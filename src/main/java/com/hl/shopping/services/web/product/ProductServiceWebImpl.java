package com.hl.shopping.services.web.product;

import com.hl.shopping.dto.ProductDto;
import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceWebImpl implements ProductServiceWeb {

    final ProductService productService;

    public ProductServiceWebImpl(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @Override
    public List<Product> listProducts() {
        return productService.findAllListed();
    }

    @Override
    public Page<Product> pageProducts(int page, int size) {
        return productService.findAllPaged(PageRequest.of(page, size));
    }

    @Override
    public Product findProductById(UUID id) {
        return productService.findById(id);
    }

    @Override
    public Product findProductByName(String name) {
        return productService.findProductByName(name);
    }

    @Override
    public Product addProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product updateProduct(ProductDto product) {
        return null;
    }

    @Override
    public Product deleteProduct(UUID id) {
        Product current = productService.findById(id);
        return productService.delete(current);
    }

}