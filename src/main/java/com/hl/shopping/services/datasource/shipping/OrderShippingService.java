package com.hl.shopping.services.datasource.shipping;

import com.hl.shopping.entites.OrderShipping;
import com.hl.shopping.services.datasource.BaseDataSourceService;

import java.util.Collection;
import java.util.List;

public interface OrderShippingService extends BaseDataSourceService<OrderShipping> {

    List<OrderShipping> addAll(Collection<OrderShipping> orderShippingCollection);

}
