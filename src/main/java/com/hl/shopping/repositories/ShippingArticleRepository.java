package com.hl.shopping.repositories;

import com.hl.shopping.entites.ShippingArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShippingArticleRepository extends JpaRepository<ShippingArticle, UUID> {

}
