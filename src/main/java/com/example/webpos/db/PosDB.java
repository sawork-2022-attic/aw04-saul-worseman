package com.example.webpos.db;

import com.example.webpos.model.OptimizedProduct;

import java.util.List;

public interface PosDB {

    public List<OptimizedProduct> getProducts();

    public OptimizedProduct getProduct(String productId);

}
