package com.hl.shopping.services.datasource.product;

import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.BaseDataSourceService;

public interface ProductService extends BaseDataSourceService<Product> {

    Product findProductByName(String name);
}
