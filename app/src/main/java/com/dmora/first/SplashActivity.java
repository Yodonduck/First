package com.dmora.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        openApp();

        ImageView mLogo = findViewById(R.id.logo);
        ImageView mBackground = findViewById(R.id.background);


        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.zoomin_fadein);
        mLogo.startAnimation(myAnim);

        Animation backgroundAnim = AnimationUtils.loadAnimation(this, R.anim.move_background);
        mBackground.startAnimation(backgroundAnim);

        Glide.with(this)
                .load(R.drawable.logo_perro)
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.teal_200)))
//                .circleCrop()
                .into(mLogo);

        Glide.with(this)
                .load(R.drawable.background)
//                .transition(DrawableTransitionOptions.withCrossFade(2000))
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.teal_200)))
                .into(mBackground);

    }





    private void openApp(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 5000);
    }
}