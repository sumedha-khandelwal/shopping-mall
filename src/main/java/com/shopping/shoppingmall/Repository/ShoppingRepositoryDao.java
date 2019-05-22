package com.shopping.shoppingmall.Repository;

import com.shopping.shoppingmall.model.ShoppingMall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepositoryDao extends CrudRepository<ShoppingMall,Long> {
}
