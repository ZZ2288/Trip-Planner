package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import java.util.ArrayList;

public class MyAccount extends AppCompatActivity {

    ItemAdapter adapter;
    SharedPreferences pref;
    private static final String PREF_NAME = "savesearch";

    RecyclerView recyclerView;
    EditText search;
    Items item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount_activity);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        TextView account = findViewById(R.id.account);
        String str = account.getText().toString();
        str += MainActivity.user.name + "!";
        account.setText(str);
        search = findViewById(R.id.name);


        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter(this, MainActivity.items);
        recyclerView.setAdapter(adapter);
        search.setText(pref.getString("name", ""));

            search.setOnClickListener(e -> {
                if (search.getText().toString().trim().isEmpty()) {
                    Toast.makeText(this, "Please Enter The Name of Item", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < MainActivity.items.size(); i++) {
                        if (MainActivity.items.get(i).name.trim().equals(search.getText().toString())) {
                            item = MainActivity.items.get(i);
                        }
                    }
                    if (item == null) {
                        Toast.makeText(this, "There is no Item have this name" + search.getText().toString(), Toast.LENGTH_LONG).show();
                    } else {
                        ArrayList<Items> l=new ArrayList();
                        l.add(item);
                        ItemAdapter list = new ItemAdapter(this, l);
                        recyclerView.setAdapter(list);
                    }
                }
            });

    }

    @Override
    protected void onStart() {
        super.onStart();
        search.setText(pref.getString("name", ""));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        search.setText(pref.getString("name", ""));

    }

    @Override
    protected void onResume() {
        super.onResume();
        search.setText(pref.getString("name", ""));

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", search.getText().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", search.getText().toString());
    }
}
