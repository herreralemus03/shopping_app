package com.hl.shopping.services.web.country;

import com.hl.shopping.dto.CountryDto;
import org.springframework.data.domain.Page;

public interface CountriesServiceWeb {
    Page<CountryDto> pageCountries(Integer page, Integer size);

}
