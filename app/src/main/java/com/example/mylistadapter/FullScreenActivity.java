package com.example.mylistadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.widget.Toast.LENGTH_SHORT;

public class FullScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView fullScreen;
    private Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen);
        fullScreen = findViewById(R.id.iv_full_screen);

        Intent intent = getIntent();

        String imageUrl = intent.getStringExtra("image_url");

        Glide.with((this))
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(fullScreen);
        btnShare = findViewById(R.id.btn_share);
        btnShare.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        startActivity(Intent.createChooser(intent,"send to"));
    }
}
