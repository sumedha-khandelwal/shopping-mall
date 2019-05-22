package com.shopping.shoppingmall.Controller;

import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShoppingMallController {

    private ShoppingService shoppingService;

    public ShoppingMallController(ShoppingService shoppingService){
        this.shoppingService=shoppingService;
    }

    @PostMapping(value = "/saveShop")
    public String saveShop(@ModelAttribute("shop")ShoppingMall shop){
            shoppingService.saveShop(shop);
            return "success";
    }

    @GetMapping(value = "/shop/{id}")
    public ShoppingMall getShopDetails(@PathVariable("id")Long id){
         Optional<ShoppingMall> shop=shoppingService.getShopById(id);
         if(shop.isPresent()){
             return shop.get();
         }
         else {

             return null;
         }
    }

    @GetMapping(value = "/list/shops")
    public List<ShoppingMall> getAllShops(){
            return shoppingService.findAll();
    }
}
