package com.hl.shopping.services.web.product;

import com.hl.shopping.dto.ProductDto;
import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.product.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceWebImpl implements ProductServiceWeb {

    final ProductService productService;
    final ModelMapper modelMapper;

    public ProductServiceWebImpl(
            ProductService productService,
            ModelMapper modelMapper
    ) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> listProducts() {
        return productService.findAllListed().stream()
                .map(e -> modelMapper.map(e, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> pageProducts(int page, int size) {
        return productService.findAllPaged(PageRequest.of(page, size))
                .map(e -> modelMapper.map(e, ProductDto.class));
    }

    @Override
    public ProductDto findProductById(UUID id) {
        return modelMapper.map(productService.findById(id), ProductDto.class);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = productService.add(modelMapper.map(productDto, Product.class));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productService.update(modelMapper.map(productDto, Product.class));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(UUID id) {
        Product productFound = productService.findById(id);
        Product product = productService.delete(productFound);
        return modelMapper.map(product, ProductDto.class);
    }

}