package com.androidp.homesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private ArrayList<FirebaseModal> arr_modals;
    private Context context;

    public HouseAdapter(Context context, ArrayList<FirebaseModal> arr_modals) {
        this.arr_modals = arr_modals;
        this.context = context;
    }

    @NonNull
    @Override
    public HouseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.houses_recycler, parent, false);
        return new HouseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseAdapter.ViewHolder holder, int position) {
       FirebaseModal modal =  arr_modals.get(position);
        holder.housePrice.setText("Rs. " + modal.getPrice());
        holder.address.setText(modal.getLoca());
        holder.rent_sale.setText(modal.getRent_sale());
        holder.no_bhk.setText(modal.getBhk() + "BHK");
        holder.area.setText(modal.getSqft() + "Sq.ft");
        Glide.with(context).load(modal.getImageurl()).into(holder.houseimg);
    }
    @Override
    public int getItemCount() {
        return arr_modals.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView houseimg;
        private TextView housePrice, rent_sale, area, address, no_bhk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            houseimg = itemView.findViewById(R.id.houseimg);
            housePrice = itemView.findViewById(R.id.housePrice);
            rent_sale = itemView.findViewById(R.id.rent_sale);
            area = itemView.findViewById(R.id.area);
            address = itemView.findViewById(R.id.address);
            no_bhk = itemView.findViewById(R.id.no_bhk);
        }
    }
}
