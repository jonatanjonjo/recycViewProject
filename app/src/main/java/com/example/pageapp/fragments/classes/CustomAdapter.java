package com.example.pageapp.fragments.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pageapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {

    private ArrayList<DataModel> dataSet; // Current displayed list
    private ArrayList<DataModel> originalDataSet;

    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = new ArrayList<>(dataSet);
        this.originalDataSet=new ArrayList<>(dataSet);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDetail;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewTitle);
            textViewDetail = itemView.findViewById(R.id.textViewSubtitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel dataModel = dataSet.get(position);
        holder.textViewName.setText(dataModel.getName());
        holder.textViewDetail.setText(dataModel.getDetail());
        holder.imageView.setImageResource(dataModel.getImage());
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public void filter(String query) {
        if (query == null || query.isEmpty()) {

            dataSet.clear();
            dataSet.addAll(originalDataSet);
        } else {
            ArrayList<DataModel> filteredList = new ArrayList<>();
            for (DataModel item : originalDataSet) {

                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
            dataSet.clear();
            dataSet.addAll(filteredList);
        }
        notifyDataSetChanged();
    }


}