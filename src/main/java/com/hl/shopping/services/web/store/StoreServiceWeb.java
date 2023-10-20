package com.hl.shopping.services.web.store;

import com.hl.shopping.dto.StoreDto;
import org.springframework.data.domain.Page;

public interface StoreServiceWeb {

    Page<StoreDto> pageStores(Integer page, Integer size);
}
