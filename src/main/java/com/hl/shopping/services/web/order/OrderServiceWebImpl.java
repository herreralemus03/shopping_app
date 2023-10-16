package com.hl.shopping.services.web.order;

import com.hl.shopping.dto.OrderDto;
import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.order.OrderService;
import com.hl.shopping.services.datasource.order.OrderServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceWebImpl implements OrderServiceWeb {

    final OrderService orderService;

    public OrderServiceWebImpl(
            OrderService orderService
    ){
        this.orderService = orderService;
    }

    @Override
    public List<Product> listOrders() {
        return null;
    }

    @Override
    public Page<Product> pageOrders(int page, int size) {
        return null;
    }

    @Override
    public Product findOrderById(UUID id) {
        return null;
    }

    @Override
    public Product findOrderByName(String name) {
        return null;
    }

    @Override
    public Product addOrder(OrderDto order) {
        return null;
    }

    @Override
    public Product updateOrder(OrderDto order) {
        return null;
    }

    @Override
    public Product deleteOrder(OrderDto order) {
        return null;
    }

}
