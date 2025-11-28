package com.example.myapplication;

import android.content.*;

import android.os.Bundle;
import android.widget.EditText;
import android.content.SharedPreferences;

import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.*;

import java.util.ArrayList;

public class CreateAccount extends AppCompatActivity {
    EditText name, email, pass1, conf;
    Switch sw;
    SharedPreferences shared;
    SharedPreferences pref;
    private static final String PREF_NAME = "LoginPref";
    private static final String Pref_Data = "Savedata";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_createaccount);
        pref=getSharedPreferences(Pref_Data,MODE_PRIVATE);
        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        email = findViewById(R.id.email);
        pass1 = findViewById(R.id.password);
        conf = findViewById(R.id.conf);
        name = findViewById(R.id.name);
        sw = findViewById(R.id.sw);
        name.setText("");
        pass1.setText("");
        conf.setText("");
        email.setText("");
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(e -> {
            String emailtxt = email.getText().toString();
            String pass = pass1.getText().toString();
            String con = conf.getText().toString();
            String nametxt = name.getText().toString();
            String savedEmail = shared.getString("email", "");

            if (emailtxt.trim().isEmpty() || pass.trim().isEmpty() || con.trim().isEmpty() || nametxt.trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                Toast.makeText(this, "Enter a valid email address\n Ex:(text@gmail.com)", Toast.LENGTH_LONG).show();

            } else if (!pass.equals(con)) {
                Toast.makeText(this, "The password and confirmation password do not match.", Toast.LENGTH_SHORT).show();

            } else if (emailtxt.equals(savedEmail)) {
                Toast.makeText(this, "This Email " + emailtxt + " already has an account", Toast.LENGTH_LONG).show();

            } else {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("email", emailtxt);
                editor.putString("password", pass);
                editor.putString("name", nametxt);
                editor.apply();
                name.setText("");
                pass1.setText("");
                conf.setText("");
                email.setText("");
                User user=new User(name.getText().toString(),emailtxt,pass);
                ArrayList<Items> item=new ArrayList<>();
                MainActivity.acc=new Account(user,item);
                MainActivity.account.add(MainActivity.acc);
                Toast.makeText(this, "Account created successfully\n Welcome to Trip planner", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
        );

            loadItemData();
    }

    private void saveItemData() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("pass1", pass1.getText().toString());
        editor.putString("conf", conf.getText().toString());
        editor.putBoolean("switch", sw.isChecked());
        editor.apply();
    }
    private void loadItemData() {
        name.setText(pref.getString("name", ""));
        pass1.setText(pref.getString("pass1", ""));
        email.setText(pref.getString("email", ""));
        sw.setChecked(pref.getBoolean("switch", false));
    }
    protected void onStart() {
        super.onStart();
        loadItemData();
    }
    protected void onResume() {
        super.onResume();
        loadItemData();
    }
    protected void onPause() {
        super.onPause();
        saveItemData();
    }
    protected void onStop() {
        super.onStop();
        saveItemData();
    }
}


