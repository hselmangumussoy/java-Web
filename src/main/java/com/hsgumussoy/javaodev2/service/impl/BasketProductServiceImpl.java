package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.repository.BasketProductRepository;
import com.hsgumussoy.javaodev2.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    @Autowired
    private BasketProductRepository repository;

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        // Basket ve Product varlığını kontrol edin
        if (basketProduct.getBasket() == null || basketProduct.getProduct() == null) {
            throw new IllegalArgumentException("Basket and Product must not be null");
        }

        // İlgili Basket ve Product varlıklarını doğrulayın
        basketService.findBasketById(basketProduct.getBasket().getId());
        productService.findProductById(basketProduct.getProduct().getId());

        // Ürün miktarına göre toplam fiyatı hesaplayın
        double totalPrice = calculateTotalAmount(basketProduct.getProduct().getPrice(), basketProduct.getCount());

        // BasketProduct varlığını kaydedin
        return repository.save(basketProduct);
    }

    public BasketProduct findBasketProductByBasketIdAndProductId(Long basketId, Long productId) {
        return repository.findByBasketIdAndProductId(basketId, productId)
                .orElseThrow(() -> new IllegalArgumentException("BasketProduct not found with given basketId and productId"));
    }
    private double calculateTotalAmount(double productPrice, int count) {
        return productPrice * count;
    }
}