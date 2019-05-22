package com.shopping.shoppingmall.Controller;

import com.shopping.shoppingmall.Service.ShoppingService;
import com.shopping.shoppingmall.model.ShoppingMall;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppingMallControllerTest {

    @Mock
    ShoppingService shoppingService;

    ShoppingMallController shoppingMallController;

    @Mock
    ShoppingMall shoppingMall;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        shoppingMallController = new ShoppingMallController(shoppingService);
    }

    @Test
    public void getAllShops() {
        ShoppingMall mall=new ShoppingMall();
        List<ShoppingMall> list =new ArrayList<>();
        list.add(mall);

        when(shoppingService.findAll()).thenReturn(list);
        List<ShoppingMall> list2 = shoppingService.findAll();

        assertEquals(list2.size(), 1);
        verify(shoppingService, times(1)).findAll();
    }
}