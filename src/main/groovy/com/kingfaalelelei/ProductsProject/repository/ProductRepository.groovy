package com.kingfaalelelei.ProductsProject.repository

import com.kingfaalelelei.ProductsProject.entity.Product
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {
}
