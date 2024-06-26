package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {


    private ArrayList<User> users;
    private Context context;
    private OnUserClickListener onUserClickListener;

    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    interface OnUserClickListener{
        void onUserClicked(int position);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.user_holder,parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.usertxt.setText(users.get(position).getFullname());
        Glide.with(context).load(users.get(position).getPhoto()).error(R.drawable.avatar).placeholder(R.drawable.avatar)
                .into(holder.userimageView);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{
        TextView usertxt;
        ImageView userimageView;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.onUserClicked(getAdapterPosition()) ;
                }
            });


            usertxt = itemView.findViewById(R.id.txtUsername);
            userimageView = itemView.findViewById(R.id.img_prof);
        }
    }
}
