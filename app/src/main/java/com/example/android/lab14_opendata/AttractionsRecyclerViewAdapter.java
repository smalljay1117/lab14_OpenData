package com.example.android.lab14_opendata;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.lab14_opendata.model.TaipeiAttractions;
import com.example.android.lab14_opendata.myapp.MyApp;

import java.util.List;

public class AttractionsRecyclerViewAdapter
        extends RecyclerView.Adapter<AttractionsRecyclerViewAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attraction, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaipeiAttractions ta = MyApp.getTaipeiAttractions();
        if (ta == null) {
            holder.m_tv_title.setText("" + position);
            return;
        }
        holder.m_tv_title.setText(position + " " + ta.getSubTitle(position));
        holder.m_tv_category.setText(ta.getCategory(position));

        Context context = holder.m_im_image.getContext();
        List<List<String>> list = MyApp.getTaipeiAttractions().getImageUrlsList();

        if (list.size() > 0) {
            List<String> imagesUrls = list.get(position);
            String imageUrl = imagesUrls.get(0);

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.no_image_box)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.m_im_image);
        }
    }

    @Override
    public int getItemCount() {
        TaipeiAttractions ta = MyApp.getTaipeiAttractions();
        if (ta == null) {
            return 10;
        }
        return ta.getCount();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView m_tv_title;
        private TextView m_tv_category;
        private ImageView m_im_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            m_tv_title = (TextView) itemView.findViewById(R.id.tv_stitle);
            m_tv_category = (TextView) itemView.findViewById(R.id.tv_category);
            m_im_image = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
