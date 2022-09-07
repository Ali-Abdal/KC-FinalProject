package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.finalproject.adapter.MyProductAdapter;
import com.example.finalproject.eventbus.MyUpdateCartEvent;
import com.example.finalproject.listener.ICartLoadListener;
import com.example.finalproject.listener.IProductLoadListener;
import com.example.finalproject.model.CartModel;
import com.example.finalproject.model.ProductModel;
import com.example.finalproject.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Shop extends AppCompatActivity implements IProductLoadListener, ICartLoadListener {
    @BindView(R.id.recycler_products)
    RecyclerView recyclerProducts;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;
//    @BindView(R.id.btnback)
//    ImageView backbtn;
    IProductLoadListener productLoadListener;
    ICartLoadListener cartLoadListener;
    ProgressBar progressBar1;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if(EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
        super.onStop();

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event){
        countCartItem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        progressBar1 =findViewById(R.id.progressBar5);

//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Shop.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

        init();
        loadProductFromFirebase();
        countCartItem();
    }

    private void loadProductFromFirebase() {
        List<ProductModel> productModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Products")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        for (DataSnapshot prodoutSnapshot:dataSnapshot.getChildren()){
                            ProductModel productModel = prodoutSnapshot.getValue(ProductModel.class);
                            Uri uri = Uri.parse(productModel.getPhoto());
                            productModel.setKey(prodoutSnapshot.getKey());
                            productModels.add(productModel);
                        }
                        productLoadListener.onProductLoadSuccess(productModels);
                        progressBar1.setVisibility(View.GONE);
                    }
                    else {
                        productLoadListener.onDrinkLoadFailed("Can't find Product");
                    }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        productLoadListener.onDrinkLoadFailed(databaseError.getMessage());
                    }
                });

    }

    private void init(){
        ButterKnife.bind(this);

        productLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerProducts.setLayoutManager(gridLayoutManager);
        recyclerProducts.addItemDecoration(new SpaceItemDecoration());

        btnCart.setOnClickListener(v -> startActivity(new Intent(this,CartActivity.class)));
    }

    @Override
    public void onProductLoadSuccess(List<ProductModel> productModelList) {
        MyProductAdapter adapter = new MyProductAdapter(this,productModelList,cartLoadListener);
        recyclerProducts.setAdapter(adapter);
    }

    @Override
    public void onDrinkLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {

        int cartSum = 0;
        for(CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
        badge.setNumber(cartSum);

    }

    @Override
    public void onCartLoadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot cartSnapshot:dataSnapshot.getChildren()){

                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);
                        }
                        cartLoadListener.onCartLoadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        cartLoadListener.onCartLoadFailed(databaseError.getMessage());
                    }
                });
    }
}