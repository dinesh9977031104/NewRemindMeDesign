package com.example.newremindmedesign.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newremindmedesign.Model.RecyclerModel;
import com.example.newremindmedesign.R;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerAdapterProvider extends RecyclerView.Adapter<RecyclerAdapterProvider.MyViewHolder> {

    private int lastPosition = -1;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<RecyclerModel> imageModelArrayList;

    public RecyclerAdapterProvider(Context ctx, ArrayList<RecyclerModel> imageModelArrayList) {

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public RecyclerAdapterProvider.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_provider_recycler_item, parent, false);
        RecyclerAdapterProvider.MyViewHolder holder = new RecyclerAdapterProvider.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterProvider.MyViewHolder holder, final int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage());
        holder.time.setText(imageModelArrayList.get(position).getName());

       // setAnimation(holder.itemView, position);
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

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }
}