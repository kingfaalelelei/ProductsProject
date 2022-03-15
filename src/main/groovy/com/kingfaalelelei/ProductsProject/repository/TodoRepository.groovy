package com.kingfaalelelei.ProductsProject.repository

import com.kingfaalelelei.ProductsProject.entity.Todo
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository

public interface TodoRepository extends PagingAndSortingRepository<Todo, Integer>,
        JpaSpecificationExecutor<Todo> {
}
