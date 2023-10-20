package com.hl.shopping.services.web.order;

import com.hl.shopping.dto.OrderDto;
import com.hl.shopping.entites.Order;
import com.hl.shopping.entites.OrderShipping;
import com.hl.shopping.entites.ShippingArticle;
import com.hl.shopping.services.datasource.article.ShippingArticleService;
import com.hl.shopping.services.datasource.order.OrderService;
import com.hl.shopping.services.datasource.shipping.OrderShippingService;
import com.hl.shopping.utils.DtoParser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceWebImpl implements OrderServiceWeb {

    final OrderService orderService;
    final OrderShippingService orderShippingService;
    final ShippingArticleService shippingArticleService;
    final ModelMapper modelMapper;
    final DtoParser dtoParser;

    public OrderServiceWebImpl(
            OrderService orderService,
            OrderShippingService orderShippingService,
            ShippingArticleService shippingArticleService,
            ModelMapper modelMapper,
            DtoParser dtoParser
    ){
        this.orderService = orderService;
        this.orderShippingService = orderShippingService;
        this.shippingArticleService = shippingArticleService;
        this.modelMapper = modelMapper;
        this.dtoParser = dtoParser;
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

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = dtoParser.parseDtoToOrder(orderDto);
        Order result = orderService.add(order);
        //TODO: SAVE ORDER SHIPPING
        List<OrderShipping> orderShippingList = order.getOrderShipping();
        orderShippingList.forEach(shipping -> shipping.setOrder(result));
        List<OrderShipping> orderShippingListResult = orderShippingService.addAll(orderShippingList);

        //TODO: SAVE SHIPPING ARTICLES
        orderShippingListResult.forEach(shipping -> {
            List<ShippingArticle> articles = shipping.getArticles();
            articles.forEach(article -> {
                article.setShipping(shipping);
            });
        });

        List<ShippingArticle> articles = orderShippingListResult.stream()
                .map(OrderShipping::getArticles)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        List<ShippingArticle> articlesResult = shippingArticleService.addAll(articles);

        //TODO: ASIGNAR DB RESULT EN DTO

        orderShippingListResult.forEach(shipping -> {
            List<ShippingArticle> shippingArticlesCopy = articlesResult.stream()
                                .takeWhile(e -> e.getShipping().getId().equals(shipping.getId()))
                                .collect(Collectors.toList());
            shipping.setArticles(shippingArticlesCopy);
        });

        result.setOrderShipping(orderShippingListResult);

        System.out.println(orderShippingListResult);
        return dtoParser.parseOrderToDto(result);
    }
}
