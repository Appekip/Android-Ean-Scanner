package com.example.skannaus;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvViewHolder> {
    Context context;
    ArrayList<Model> models;
    Onclick onclick;

    public interface Onclick {
        void onEvent(Model model,int pos);
    }

    public RvAdapter(Context context, ArrayList<Model> models, Onclick onclick) {
        this.context = context;
        this.models = models;
        this.onclick = onclick;
    }

    View view;

    @Override
    public RvAdapter.RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        RvViewHolder rvViewHolder = new RvViewHolder(view);
        return rvViewHolder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.RvViewHolder holder, final int position) {
        final Model model = models.get(position);
        if (model.getName() != null) {
            holder.itemName.setText(model.getName());
        }


        holder.removeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclick.onEvent(model,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class RvViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        ImageView removeImg;
        LinearLayout llItem;

        public RvViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.tv_name);
            removeImg = itemView.findViewById(R.id.img_remove);
            llItem = itemView.findViewById(R.id.ll_item);
        }
    }
}



