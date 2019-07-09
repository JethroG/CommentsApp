package com.yourcompany.bro.hi.comments;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class RecyclerVViewAdapter extends RecyclerView.Adapter<RecyclerVViewAdapter.ViewHolder> {

    private List<ModelJson> list_data;
    private Context context;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;

    public RecyclerVViewAdapter(List<ModelJson> list_data, Context context) {
        this.list_data = list_data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelJson listData=list_data.get(position);

        holder.id.setText(listData.getId());
        holder.body.setText(listData.getBody());

    }

    @Override
    public int getItemCount() {
         return list_data == null ? 0 : list_data.size();
    }


    @Override
    public int getItemViewType(int position) {
        boolean isLoaderVisible = false;
        if (isLoaderVisible) {
            return position == list_data.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }





    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView id,body;
        ViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            body= itemView.findViewById(R.id.body);
        }
    }
}