package com.example.finalproject.listener;

import com.example.finalproject.model.ProductModel;

import java.util.List;

public interface IProductLoadListener {

    void onProductLoadSuccess(List<ProductModel> productModelList);
    void onDrinkLoadFailed(String message);
}
