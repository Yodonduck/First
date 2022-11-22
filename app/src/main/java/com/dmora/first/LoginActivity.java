package com.dmora.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {

    TextView textSignup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textSignup = findViewById(R.id.textSignup);
        login = findViewById(R.id.login);

        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        ImageView mDog = findViewById(R.id.dog);


        Glide.with(this)
                .load("https://images.unsplash.com/photo-1561037404-61cd46aa615b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80")
//                .transition(DrawableTransitionOptions.withCrossFade(2000))
                .centerCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.teal_200)))
                .into(mDog);



    }

    public void openSignup(){
        Intent intent = new Intent(this, SignupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void openMain(){
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }

}