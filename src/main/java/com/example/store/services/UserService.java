package com.example.store.services;

import com.example.store.dtos.UserSummary;
import com.example.store.entities.*;
import com.example.store.repositories.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
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
        List<Product> products = productRepository.findProducts(BigDecimal.valueOf(5), BigDecimal.valueOf(15));
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
}
