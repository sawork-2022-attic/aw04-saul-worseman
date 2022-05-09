package com.example.webpos.biz;

import com.example.webpos.model.Cart;
import com.example.webpos.model.OptimizedProduct;

import java.util.List;

public interface PosService {

    public void checkout(Cart cart);

    public Cart add(Cart cart, OptimizedProduct product, int amount);

    public Cart add(Cart cart, String productId, int amount);

    public List<OptimizedProduct> products();

    public OptimizedProduct randomProduct();
}
