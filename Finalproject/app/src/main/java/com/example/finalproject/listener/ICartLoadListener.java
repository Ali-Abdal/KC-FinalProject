package com.example.finalproject.listener;

import com.example.finalproject.model.CartModel;
import com.example.finalproject.model.ProductModel;

import java.util.List;

public interface ICartLoadListener {

    void onCartLoadSuccess(List<CartModel> cartModelList);
    void onCartLoadFailed(String message);
}
