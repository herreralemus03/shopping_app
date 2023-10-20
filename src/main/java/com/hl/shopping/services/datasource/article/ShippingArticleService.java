package com.hl.shopping.services.datasource.article;

import com.hl.shopping.entites.ShippingArticle;
import com.hl.shopping.services.datasource.BaseDataSourceService;

import java.util.Collection;
import java.util.List;


public interface ShippingArticleService extends BaseDataSourceService<ShippingArticle> {

    List<ShippingArticle> addAll(Collection<ShippingArticle> shippingArticles);

}
