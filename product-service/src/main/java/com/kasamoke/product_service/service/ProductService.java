package com.kasamoke.product_service.service;

import com.kasamoke.product_service.model.ProductModel;
import com.kasamoke.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductModel productModel) {
        try {
            productRepository.save(productModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isProductAvailable(Integer id, Integer orderedQuantity) {
        try{
            Optional<ProductModel> product = productRepository.findById(id);
            return product.isPresent() && product.get().getQuantity() > orderedQuantity;
        } catch (Exception e) {
            throw new RuntimeException("Not enough stock available for product");
        }
    }

    public void decreaseProductQuantity(Integer id, Integer quantity) {
        try {
            Optional<ProductModel> product = productRepository.findById(id);
            if(product.isPresent()){
                product.get().setQuantity(product.get().getQuantity() - quantity);
                productRepository.save(product.get());
            }
            else {
                throw new RuntimeException("Product not found with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrease product quantity: " + e.getMessage(), e);
        }
    }



}
