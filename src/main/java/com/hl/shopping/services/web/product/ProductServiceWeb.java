package com.hl.shopping.services.web.product;


import com.hl.shopping.dto.ProductDto;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductServiceWeb {

    public List<ProductDto> listProducts();

    public Page<ProductDto> pageProducts(int page, int size);

    public ProductDto findProductById(UUID id);

    public ProductDto addProduct(ProductDto product);

    public ProductDto updateProduct(ProductDto product);

    public ProductDto deleteProduct(UUID id);

}
