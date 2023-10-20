package com.hl.shopping.services.web.store;

import com.hl.shopping.dto.StoreDto;
import com.hl.shopping.services.datasource.store.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceWebImpl implements StoreServiceWeb {

    final StoreService storeService;
    final ModelMapper modelMapper;

    public StoreServiceWebImpl(
            StoreService storeService,
            ModelMapper modelMapper
    ) {
        this.storeService = storeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<StoreDto> pageStores(Integer page, Integer size) {
        return storeService.findAllPaged(PageRequest.of(page, size))
                .map(store -> modelMapper.map(store, StoreDto.class));
    }

}
