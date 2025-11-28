package com.example.myapplication;

import android.content.*;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.*;
import android.widget.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    EditText name, email, pass;
    static  User user;
    public static Account acc;
    public static ArrayList<Items> items;

    public static ArrayList<Account> account;
    SharedPreferences shared;
    SharedPreferences pref;

    private static final String PREF_NAME = "LoginPref";
    private static final String pref_Data = "savelogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        pref = getSharedPreferences(pref_Data, MODE_PRIVATE);

        items = new ArrayList<>();
        account = new ArrayList<>();
        setContentView(R.layout.acitivity_signin);

        shared = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        name = findViewById(R.id.name);
        Button btn = findViewById(R.id.btn);
        TextView create = findViewById(R.id.create);
        String intent=getIntent().getStringExtra("class");
        name.setText("");
        pass.setText("");
        email.setText("");

        create.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, CreateAccount.class));
        });

        btn.setOnClickListener(e -> {
            String nametxt = name.getText().toString();
            String emailtxt = email.getText().toString();
            String passtxt = pass.getText().toString();

            if (emailtxt.trim().isEmpty() || passtxt.trim().isEmpty() || nametxt.trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_LONG).show();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailtxt).matches()) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_LONG).show();
                return;
            }

            String savedEmail = shared.getString("email", "");
            String savedPass = shared.getString("password", "");
            String savedName = shared.getString("name", "");
              if(account.size()==0){

              }
            if (emailtxt.equals(savedEmail) && passtxt.equals(savedPass) && nametxt.equals(savedName)) {
                name.setText("");
                pass.setText("");
                email.setText("");
                user = new User(savedName, savedEmail, savedPass);
                acc = null;

                for (int i = 0; i < account.size(); i++) {
                    if (account.get(i).user.email.equals(user.email)) {
                        acc = account.get(i);
                    }
                }

                if (acc == null) {
                    acc = new Account(user, items);
                } else {
                    items = acc.items;
                }
                Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show();



                startActivity(new Intent(MainActivity.this, MainUserPage.class));
                finish();
            } else {
                Toast.makeText(
                        this,
                        "Account not found. Create a new account or ensure that the entered information is correct.",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        loadItemData();
    }

    private void saveItemData() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.putString("pass", pass.getText().toString());
        editor.apply();
    }

    private void loadItemData() {
        name.setText(pref.getString("name", ""));
        email.setText(pref.getString("email", ""));
        pass.setText(pref.getString("pass", ""));
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadItemData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItemData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveItemData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveItemData();
    }
}
