package com.hl.shopping.controllers;

import com.hl.shopping.dto.ProductDto;
import com.hl.shopping.services.web.product.ProductServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(path = "/product")
public class ProductsController {

    final ProductServiceWeb productServiceWeb;

    public ProductsController(
            ProductServiceWeb productServiceWeb
    ) {
        this.productServiceWeb = productServiceWeb;
    }

    @GetMapping("/get/paged")
    public ResponseEntity<?> getProductsPaged(
            @RequestParam("page") final Integer page,
            @RequestParam("size") final Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.pageProducts(page, size));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/listed")
    public ResponseEntity<?> getProductsListed(){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.listProducts());
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProduct(
            @PathVariable("id") final String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.findProductById(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> getProduct(
            @RequestBody ProductDto productDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.addProduct(productDto));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestBody ProductDto productDto
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.updateProduct(productDto));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(
            @PathVariable String id
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("status", "ok");
                put("body", productServiceWeb.deleteProduct(UUID.fromString(id)));
            }});
        } catch (Exception e){
            return ResponseEntity.status(500).body(e);
        }
    }
}
