package com.androidp.homesearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private ArrayList<String> arrayList;

    public HouseAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
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
        arrayList.get(position);
        holder.housePrice.setText("60,00,000/-");
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView houseimg;
        private EditText housePrice, rent_sale, area, address, no_bhk;
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
