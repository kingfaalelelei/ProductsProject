package com.kingfaalelelei.ProductsProject.service

import antlr.StringUtils
import com.kingfaalelelei.ProductsProject.entity.Product
import com.kingfaalelelei.ProductsProject.repository.ProductRepository
import org.hibernate.annotations.Sort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

import java.awt.print.Pageable

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    public Product findById(Integer id) throws Exception {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new Exception("Cannot find Product with id: " + id);
        }
        else return product;
    }

    public List<Product> findAll(int pageNumber, int rowPerPage) {
        List<Product> products = new ArrayList<>();
        org.springframework.data.domain.Pageable sortedByIdAsc =
                PageRequest.of(pageNumber - 1, rowPerPage,
                    org.springframework.data.domain.Sort.by("id").ascending());
        productRepository.findAll(sortedByIdAsc).forEach(products::add);
        return products;
    }

    public Product save(Product product) throws Exception {
        if (!org.apache.commons.lang.StringUtils.isEmpty(product.getName())) {
            if (product.getId() != null && existsById(product.getId())) {
                throw new Exception("Product with id: " + Product.getId() +
                        " already exists");
            }
            return productRepository.save(product);
        }
        else {
            throw new Exception("Title cannot be null or empty");
        }
    }

    public Product saveProductFromUrl(Product product) throws Exception {
        if (product.getId() != null && existsById(product.getId())) {
            throw new Exception("Product with id: " + product.getId() +
                    " already exists");
        }
        return productRepository.save(product)
    }

    public void update(Product product) throws Exception {
        if (!StringUtils.isEmpty(product.getName())) {
            if (!existsById(product.getId())) {
                throw new Exception("Cannot find Product with id: " + Product.getId());
            }
            productRepository.save(product);
        }
        else {
            throw new Exception("Title cannot be null or empty");
        }
    }

    public void deleteById(Integer id) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Cannot find Product with id: " + id);
        }
        else {
            productRepository.deleteById(id);
        }
    }

    public Long count() {
        return productRepository.count();
    }
}
