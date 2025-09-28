package com.example.store.services;

import com.example.store.dtos.UserSummary;
import com.example.store.entities.*;
import com.example.store.repositories.*;
import com.example.store.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("john")
                .email("johon@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Entity is in Persistent state");
        } else {
            System.out.println("Entity is in Transient/Detached state");
        }
        userRepository.save(user);
        if (entityManager.contains(user)) {
            System.out.println("Entity is in Persistent state");
        } else {
            System.out.println("Entity is in Transient/Detached state");
        }

    }

    @Transactional
    public void showRelatedEntities() {
        Address address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated() {
        var user = User.builder()
                .name("harry")
                .email("harry@gmail.com")
                .password("password")
                .build();
        var address = Address.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();
        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated() {
        User user = userRepository.findById(5L).orElseThrow();
        Address address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    public void createProduct() {
        var category = new Category("category 1");

        var product = Product.builder()
                .price(BigDecimal.valueOf(10.22))
                .description("product description")
                .name("milk")
                .category(category)
                .build();

        productRepository.save(product);
    }

    @Transactional
    public void createProduct2() {
        Category category = categoryRepository.findById((byte) 1).orElseThrow();
        Product product = Product.builder()
                .name("product 2")
                .description("description of product 2")
                .price(BigDecimal.valueOf(20.22))
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void addProduct() {
        User user = userRepository.findById(4L).orElseThrow();
        Iterable<Product> products = productRepository.findAll();
        products.forEach(user::addProductToWishlist);
        userRepository.save(user);
    }

    @Transactional
    public void deleteProduct() {
        productRepository.deleteById(8L);
//        Manually modify wishlist table
//        Product product = productRepository.findById(9L).orElseThrow();
//        for (User user : product.getUsers()) {
//            user.getWishlist().remove(product);
//        }
//        product.getUsers().clear();
//        productRepository.delete(product);
    }

    @Transactional
    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(12), (byte) 1);
    }

    @Transactional
    public void fetchProducts() {
        Product product = new Product();
        product.setName("product");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        
        Example<Product> example = Example.of(product, matcher);
        List<Product> products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUser() {
        Iterable<User> users = userRepository.findAllWithAddress();
        users.forEach(user -> {
            System.out.println(user);
            user.getAddresses().forEach(System.out::println);
        });
    }

    public void findProfiles() {
        List<UserSummary> userSummaries = userRepository.findLoyalUsers(2);
        userSummaries.forEach(user -> {
            System.out.println(user.getId() + ": " + user.getEmail());
        });
    }

    public void fetchProductsByCriteria() {
        List<Product> products = productRepository.findProductsByCriteria(null, BigDecimal.valueOf(1), BigDecimal.valueOf(10));
        products.forEach(System.out::println);
    }

    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);
        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void fetchSortedProducts() {
        Sort sort = Sort.by("name").and(
                Sort.by("price").descending()
        );
        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);
        List<Product> products = page.getContent();
        products.forEach(System.out::println);

        int totalPages = page.getTotalPages();
        long totalElements = page.getTotalElements();
        System.out.println("total pages: " + totalPages + " total elements: " + totalElements);

    }
}
