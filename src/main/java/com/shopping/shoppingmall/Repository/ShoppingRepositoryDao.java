package com.shopping.shoppingmall.Repository;

import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingRepositoryDao extends CrudRepository<ShoppingMall,Long> {

    List<ShoppingMall> findByNameIn(List<String> names);
}
