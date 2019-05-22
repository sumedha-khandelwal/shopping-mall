package com.shopping.shoppingmall.Service;

import com.shopping.shoppingmall.model.ShoppingMall;

import java.util.List;
import java.util.Optional;

public interface ShoppingService {

    Long saveShop(ShoppingMall shoppingMall);

    Optional<ShoppingMall> getShopById(Long id);

    List<ShoppingMall> findAll();
}
