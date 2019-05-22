package com.shopping.shoppingmall.Controller;

import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller for shopping mall
 */
@RestController
public class ShoppingMallController {

    private ShoppingService shoppingService;

    public ShoppingMallController(ShoppingService shoppingService){
        this.shoppingService=shoppingService;
    }

    /**
     * Saves the shop
     * @param shop
     * @return
     */
    @PostMapping(value = "/saveShop")
    public String saveShop(@ModelAttribute("shop")ShoppingMall shop){
            shoppingService.saveShop(shop);
            return "success";
    }

    /**
     * search shops by distance
     * @param lat
     * @param lng
     * @return
     */
    @GetMapping(value = "/shop/search")
    public List<ShoppingMall> searchDetails(@RequestParam("lat")Double lat,@RequestParam("lng")Double lng){
        return shoppingService.getNearByPlace(lat,lng);
    }

    /**
     * get the list of all shops
     * @return
     */
    @GetMapping(value = "/list/shops")
    public List<ShoppingMall> getAllShops(){
            return shoppingService.findAll();
    }
}
