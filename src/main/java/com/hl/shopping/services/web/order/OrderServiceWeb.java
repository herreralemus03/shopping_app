package com.hl.shopping.services.web.order;

import com.hl.shopping.dto.OrderDto;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderServiceWeb {

    public List<Product> listOrders();

    public Page<Product> pageOrders(int page, int size);

    public Product findOrderById(UUID id);

    public Product findOrderByName(String name);

    public Product addOrder(OrderDto order);

    public Product updateOrder(OrderDto order);

    public Product deleteOrder(OrderDto order);

}
