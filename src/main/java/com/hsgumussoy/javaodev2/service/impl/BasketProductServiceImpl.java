package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.repository.BasketProductRepository;
import com.hsgumussoy.javaodev2.service.BasketProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketProductServiceImpl implements BasketProductService {
    @Autowired
    private BasketProductRepository repository;

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        // Basket ve Product varlığını kontrol edin
        if (basketProduct.getBasket() == null || basketProduct.getProduct() == null) {
            throw new IllegalArgumentException("Basket and Product must not be null");
        }

       /* // İlgili Basket ve Product varlıklarını doğrulayın
        Basket basket = basketService.findBasketById(basketProduct.getBasket().getId());
        basketProduct.getBasket().setId(basket.getId());

        Product product = productService.findProductById(basketProduct.getProduct().getId());
        basketProduct.getBasket().setId(product.getId());

        // Ürün miktarına göre toplam fiyatı hesaplayın
        double totalPrice = calculateTotalAmount(basketProduct.getProduct().getPrice(), basketProduct.getCount());
        basketProduct.setTotalAmount(totalPrice);*/

        // BasketProduct varlığını kaydedin
        return repository.save(basketProduct);
    }

    /*private double calculateTotalAmount(double productPrice, int count) {
        return productPrice * count;
    }*/
}
