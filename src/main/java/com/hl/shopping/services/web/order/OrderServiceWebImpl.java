package com.hl.shopping.services.web.order;

import com.hl.shopping.dto.OrderDto;
import com.hl.shopping.entites.Order;
import com.hl.shopping.entites.Product;
import com.hl.shopping.services.datasource.order.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceWebImpl implements OrderServiceWeb {

    final OrderService orderService;
    final ModelMapper modelMapper;

    public OrderServiceWebImpl(
            OrderService orderService,
            ModelMapper modelMapper){
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDto> listOrders() {
        return orderService.findAllListed().stream()
                .map(e -> modelMapper.map(e, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDto> pageOrders(int page, int size) {
        return orderService.findAllPaged(PageRequest.of(page, size))
                .map(e -> modelMapper.map(e, OrderDto.class));
    }

    @Override
    public OrderDto findOrderById(UUID id) {
        return modelMapper.map(orderService.findById(id), OrderDto.class);
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.add(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.add(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

    @Override
    public OrderDto deleteOrder(OrderDto orderDto) {
        Order order =  modelMapper.map(orderDto, Order.class);
        Order orderResult = orderService.delete(order);
        return modelMapper.map(orderResult, OrderDto.class);
    }

}
