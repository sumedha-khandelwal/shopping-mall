package com.shopping.shoppingmall.BootStrap;

import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShopType;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ShoppingMallBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private final ShoppingService shoppingService;

    @Autowired RedisOperations<String, String> operations;
    GeoOperations<String, String> geoOperations;

    public ShoppingMallBootStrap(ShoppingService shoppingService){
        this.shoppingService=shoppingService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        geoOperations = operations.opsForGeo();
        ShoppingMall shoppingMall=new ShoppingMall();
        shoppingMall.setId(1l);
        shoppingMall.setName("zara");
        shoppingMall.setOwnerName("sumedha");
        shoppingMall.setAddress("Noida");
        shoppingMall.setShopType(ShopType.MALL);
        shoppingMall.setLatitude("28.6047966");
        shoppingMall.setLongitude("77.323172");
        Long id=shoppingService.saveShop(shoppingMall);
        geoOperations.geoAdd("location", new Point(28.6047966, 77.323172), "zara");


        ShoppingMall shoppingMall2=new ShoppingMall();
        shoppingMall2.setId(2l);
        shoppingMall2.setName("h&m");
        shoppingMall2.setOwnerName("vipul");
        shoppingMall2.setAddress("Gurgaon");
        shoppingMall2.setShopType(ShopType.MALL);
        shoppingMall2.setLatitude("28.500587");
        shoppingMall2.setLongitude("77.08571949999998");
        Long id2=shoppingService.saveShop(shoppingMall2);
        geoOperations.geoAdd("location", new Point(28.500587, 77.08571949999998), "h&m");

        ShoppingMall shoppingMall3=new ShoppingMall();
        shoppingMall3.setId(3l);
        shoppingMall3.setName("medicine");
        shoppingMall3.setOwnerName("rahul");
        shoppingMall3.setAddress("saket");
        shoppingMall3.setShopType(ShopType.MEDICAL_STORE);
        shoppingMall3.setLatitude("28.5296939");
        shoppingMall3.setLongitude("77.21871190000002");
        Long id3=shoppingService.saveShop(shoppingMall3);
        geoOperations.geoAdd("location", new Point(28.5296939, 77.21871190000002), "medicine");

        ShoppingMall shoppingMall4=new ShoppingMall();
        shoppingMall4.setId(4l);
        shoppingMall4.setName("super store");
        shoppingMall4.setOwnerName("shreya");
        shoppingMall4.setAddress("model town");
        shoppingMall4.setShopType(ShopType.SUPERMARKET);
        shoppingMall4.setLatitude("28.7122675");
        shoppingMall4.setLongitude("77.1892613");
        Long id4=shoppingService.saveShop(shoppingMall4);
        geoOperations.geoAdd("location", new Point(28.7122675, 77.1892613), "super store");

        ShoppingMall shoppingMall5=new ShoppingMall();
        shoppingMall5.setId(5l);
        shoppingMall5.setName("bata");
        shoppingMall5.setOwnerName("piyush");
        shoppingMall5.setAddress("karol bagh");
        shoppingMall5.setShopType(ShopType.GENERAL_STORE);
        shoppingMall5.setLatitude("28.644165673562306");
        shoppingMall5.setLongitude("77.8888888");
        Long id5=shoppingService.saveShop(shoppingMall5);

        geoOperations.geoAdd("location", new Point(28.644165673562306, 77.8888888), "bata");
        Circle circle = new Circle(new Point(28.644165673562306,77.8888888),
                new Distance(10, RedisGeoCommands.DistanceUnit.KILOMETERS));
        GeoResults<RedisGeoCommands.GeoLocation<String>> result=geoOperations.geoRadius("location",circle);

        System.out.println(result.iterator().next().getContent().getName());

    }
}
