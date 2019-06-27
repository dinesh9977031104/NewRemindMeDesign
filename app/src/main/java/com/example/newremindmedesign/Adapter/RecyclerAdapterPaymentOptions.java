package com.example.newremindmedesign.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newremindmedesign.Model.RecyclerModel;
import com.example.newremindmedesign.R;

import java.util.ArrayList;

public class RecyclerAdapterPaymentOptions extends RecyclerView.Adapter<RecyclerAdapterPaymentOptions.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<RecyclerModel> imageModelArrayList;

    public RecyclerAdapterPaymentOptions(Context ctx, ArrayList<RecyclerModel> imageModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public RecyclerAdapterPaymentOptions.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterPaymentOptions.MyViewHolder holder, final int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage());
        holder.time.setText(imageModelArrayList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);


        }
    }
}