package com.shopping.shoppingmall.ServiceImpl;

import com.shopping.shoppingmall.Repository.ShoppingRepositoryDao;
import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("shoppingService")
public class ShoppingMallServiceImpl implements ShoppingService {

    private final ShoppingRepositoryDao shoppingRepositoryDao;

    RedisOperations<String, String> operations;
    GeoOperations<String, String> geoOperations;

    public ShoppingMallServiceImpl(ShoppingRepositoryDao shoppingRepositoryDao,RedisOperations<String, String> operations){
        this.shoppingRepositoryDao=shoppingRepositoryDao;
        this.operations=operations;
        this.geoOperations=this.operations.opsForGeo();
    }

    @Override
    public Long saveShop(ShoppingMall shoppingMall){
        ShoppingMall shop=shoppingRepositoryDao.save(shoppingMall);
        Double lat=new Double(shop.getLatitude());
        Double lng=new Double(shop.getLongitude());
        geoOperations.geoAdd("location", new Point(lat,lng), shop.getName());
        return shop.getId();
    }

    @Override
    public Optional<ShoppingMall> getShopById(Long id){
        return shoppingRepositoryDao.findById(id);
    }

    @Override
    public List<ShoppingMall> findAll(){
        List<ShoppingMall> mall=new ArrayList<>();
        Iterable<ShoppingMall> itr=shoppingRepositoryDao.findAll();
        if(itr!=null) {
            itr.iterator().forEachRemaining(mall::add);
        }
        return mall;
    }
}
