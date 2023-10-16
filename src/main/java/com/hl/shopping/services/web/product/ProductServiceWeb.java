package com.hl.shopping.services.web.product;


import com.hl.shopping.dto.ProductDto;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductServiceWeb {

    public List<Product> listProducts();

    public Page<Product> pageProducts(int page, int size);

    public Product findProductById(UUID id);

    public Product findProductByName(String name);

    public Product addProduct(ProductDto product);

    public Product updateProduct(ProductDto product);

    public Product deleteProduct(UUID id);

}
