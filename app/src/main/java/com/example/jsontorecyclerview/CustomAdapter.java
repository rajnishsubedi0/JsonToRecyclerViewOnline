package com.example.jsontorecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    /*String [] dataInArray={"Hello", "whats up", "Brother", "How are you", "Tell me","About","Yourself","ulala","what is up in sky", "sky is blue"};*/
    Context context;
    ArrayList<DataKeeperClass> arrayListDataKeeper;
    public CustomAdapter(ArrayList<DataKeeperClass> arrayList){

        this.arrayListDataKeeper=arrayList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textViewToReplaceData);
        }
    }


    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        String string=arrayListDataKeeper.get(holder.getAdapterPosition()).name;
        holder.textView.setText(string);

    }

    @Override
    public int getItemCount() {
        return arrayListDataKeeper.size();
    }
}
