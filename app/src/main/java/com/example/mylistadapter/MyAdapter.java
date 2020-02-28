package com.example.mylistadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends ListAdapter<News, MyAdapter.NewsHplder> {

    //private List<News> listNews;
    private OnNewsClickListener onNewsClickListener;

    public void setOnNewsClickListener(OnNewsClickListener onNewsClickListener) {
        this.onNewsClickListener = onNewsClickListener;
    }
    public News getNews(int position){
        return getItem(position);
    }

    protected MyAdapter(@NonNull DiffUtil.ItemCallback<News> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public NewsHplder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHplder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHplder holder, int position) {
        //News currentNews = listNews.get(position);
        holder.title.setText(getItem(position).getTitle());
        holder.description.setText(getItem(position).getDescription());

        Glide.with(((NewsHplder) holder).imageUrl.getContext())
                .load(getItem(position).getUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageUrl);


    }


    class NewsHplder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private ImageView imageUrl;

        public NewsHplder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_description);
            imageUrl = itemView.findViewById(R.id.iv_image_url);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onNewsClickListener != null){
                        onNewsClickListener.onNewsClicable(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface OnNewsClickListener {
        void onNewsClicable(int positon);
    }

}
