package com.example.adam.wallee.Activities;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adam.wallee.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<ItemList> lists;
    public MyAdapter(List<ItemList> lits){
        this.lists = lits;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemrow, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return  vh;

    }
    //zorbrazi data na specificke pozici podle int i
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ItemList curritem = this.lists.get(i);
        viewHolder.task_title.setText(curritem.title);
        viewHolder.task_content.setText(curritem.content);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView task_title;
        public TextView task_content;
        public ViewHolder(View itemView) {
            super(itemView);
            task_title = (TextView)itemView.findViewById(R.id.task_title);
            task_content = (TextView)itemView.findViewById(R.id.task_content);
        }
    }
}