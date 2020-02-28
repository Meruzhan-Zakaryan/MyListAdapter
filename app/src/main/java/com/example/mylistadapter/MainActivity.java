package com.example.mylistadapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnNewsClickListener {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<News> newsList = new ArrayList<>();
    //private MyDiffUtil myDiffUtil;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myDiffUtil = new MyDiffUtil();
        myAdapter = new MyAdapter(new DiffUtil.ItemCallback<News>() {
            @Override
            public boolean areItemsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getIdModel() == newItem.getIdModel();
            }

            @Override
            public boolean areContentsTheSame(@NonNull News oldItem, @NonNull News newItem) {
                return oldItem.getTitle().equals(newItem.getTitle()) &&
                        oldItem.getDescription().equals(newItem.getDescription()) &&
                        oldItem.getUrl().equals(newItem.getUrl());
            }
        });
        myAdapter.setOnNewsClickListener(this);
        recyclerView = findViewById(R.id.rv_news);
        NewsCreator.creatNews(newsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        myAdapter.submitList(newsList);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                newsList.remove(myAdapter.getNews(viewHolder.getAdapterPosition()));
                myAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerView);


    }

    @Override
    public void onNewsClicable(int positon) {

        Intent intent=new Intent(this,FullScreenActivity.class);
        intent.putExtra("image_url",newsList.get(positon).getUrl());
        startActivity(intent);

    }

}
