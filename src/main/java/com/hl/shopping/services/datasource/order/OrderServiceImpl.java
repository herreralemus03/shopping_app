package com.hl.shopping.services.datasource.order;

import com.hl.shopping.entites.Order;
import com.hl.shopping.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllListed() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAllPaged(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Order order) {
        Order current = this.findById(order.getId());
        orderRepository.delete(current);
        return current;
    }
}
