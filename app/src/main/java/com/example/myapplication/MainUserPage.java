package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainUserPage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        RelativeLayout myImage = findViewById(R.id.relative1);
        RelativeLayout image2 = findViewById(R.id.relative2);
        RelativeLayout image3 = findViewById(R.id.relative3);
        RelativeLayout image4 = findViewById(R.id.relative4);
        RelativeLayout image5 = findViewById(R.id.relative5);
        Button logout=findViewById(R.id.logout);
        Button account=findViewById(R.id.account);
        myImage.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, JerusalemPage.class);
                    startActivity(intent);
                }).start();
            }).start();
        });

        image2.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, ItalyPage.class);
                    startActivity(intent);
                }).start();
            }).start();
        });

        image3.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, SpainPage.class);
                    startActivity(intent);
                }).start();
            }).start();
        });
        image4.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, RussiaPage.class);
                    startActivity(intent);
                }).start();
            }).start();
        });


        image5.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, JapanPage.class);
                    startActivity(intent);
                }).start();
            }).start();
        });
        account.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, MyAccount.class);
                    startActivity(intent);
                }).start();
            }).start();
        });
        logout.setOnClickListener(v -> {
            v.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(100).withEndAction(() -> {
                    Intent intent = new Intent(MainUserPage.this, MainActivity.class);
                    intent.putExtra("class","logout");
                    startActivity(intent);
                }).start();
            }).start();
        });
    }
}
