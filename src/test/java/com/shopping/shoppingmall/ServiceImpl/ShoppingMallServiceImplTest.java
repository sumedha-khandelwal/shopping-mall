package com.shopping.shoppingmall.ServiceImpl;

import com.shopping.shoppingmall.Repository.ShoppingRepositoryDao;
import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShoppingMallServiceImplTest {

    ShoppingService shoppingService;

    @Mock
    ShoppingRepositoryDao shoppingRepositoryDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // shoppingService=new ShoppingMallServiceImpl(shoppingRepositoryDao);
    }


    @Test
    public void findAll() {
        ShoppingMall mall=new ShoppingMall();
        List<ShoppingMall> list =new ArrayList<>();
        list.add(mall);

        when(shoppingRepositoryDao.findAll()).thenReturn(list);

        List<ShoppingMall> list2 = shoppingService.findAll();

        assertEquals(list2.size(), 1);
        verify(shoppingRepositoryDao, times(1)).findAll();
    }
}