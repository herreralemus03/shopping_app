package com.hl.shopping.services.web.order;

import com.hl.shopping.dto.OrderDto;
import com.hl.shopping.entites.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderServiceWeb {

    public List<OrderDto> listOrders();

    public Page<OrderDto> pageOrders(int page, int size);

    public OrderDto findOrderById(UUID id);

    public OrderDto addOrder(OrderDto order);

    public OrderDto updateOrder(OrderDto order);

    public OrderDto deleteOrder(OrderDto order);

}
