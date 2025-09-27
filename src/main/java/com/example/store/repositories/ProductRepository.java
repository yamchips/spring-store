package com.example.store.repositories;

import com.example.store.dtos.ProductSummary;
import com.example.store.dtos.ProductSummaryDTO;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // String
    List<Product> findByName(String name);
    List<Product> findByNameLike(String name);
    List<Product> findByNameNotLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameEndingWithIgnoreCase(String name);

    // numbers
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // null
    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    // multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    // sort
    List<Product> findByNameOrderByPriceDesc(String name);

    // limit
    List<Product> findTop5ByNameOrderByPrice(String name);
    List<Product> findFirst5ByNameOrderByPrice(String name);

    // find products whose prices are in a given range and sort by name
    // SQL or JPQL
//    @Query(
//            value = "select * from products p where p.price between :min and :max order by p.name",
//            nativeQuery = true)
    // JPQL
    @Procedure("findProductByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);

    @Query(
            "select count(*) from Product p where p.price between :min and :max"
    )
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query(
            "update Product p set p.price = :newPrice where p.category.id = :categoryId"
    )
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

//    @Query(
//            "select new com.example.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category"
//    )
    List<ProductSummary> findByCategory(@Param("category") Category category);
}
