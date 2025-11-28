package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SpainPage extends AppCompatActivity {
    SharedPreferences pref;
    private static final String PREF_NAME = "SaveSpain";
    EditText date, location, name;
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spain);
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        date = findViewById(R.id.date);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);

        spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
        Button add = findViewById(R.id.add);
        name.setText("");
        date.setText("");
        location.setText("");
        spinner.setSelection(0);
        String items[] = {
                "Camping",
                "Reserve Hotel",
                "Swimming",
                "Hiking",
                "Walking",
                "Sightseeing",
                "Museum Visit",
                "Boat Ride",
                "Photography"
        };

        ArrayAdapter<String> arr = new ArrayAdapter<>(
                this,
                R.layout.spinner_style,
                items
        );

        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // layout for dropdown
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    SpainPage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);
                    },
                    year, month, day
            );

            dpd.show();
        });
        add.setOnClickListener(e -> {

            if (location.getText().toString().trim().isEmpty() || date.getText().toString().trim().isEmpty() || name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_LONG).show();
            } else {

                String nametxt = name.getText().toString();
                Items item1 = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.equals(nametxt)) {
                        item1 = MainActivity.items.get(i);
                        break;
                    }
                }
                if (item1 != null) {
                    Toast.makeText(this, "The Name is already Exist.", Toast.LENGTH_LONG).show();

                } else {
                    String country = "Spain";
                    String datetxt = date.getText().toString();
                    String type = spinner.getSelectedItem().toString();
                    String locationtxt = location.getText().toString();


                    if (isAvailable(datetxt)) {
                        Items item = new Items(nametxt, datetxt, locationtxt, type, country);
                        MainActivity.items.add(item);
                        name.setText("");
                        date.setText("");
                        location.setText("");
                        spinner.setSelection(0);

                        MainActivity.acc.updatear(MainActivity.items);
                        Toast.makeText(this, "Item process added successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                    }


                }


            }

        });
        update.setOnClickListener(e -> {
            Intent intent = new Intent(SpainPage.this, UpdatePage.class);
            intent.putExtra("fromClass", "spainclass");
            startActivity(intent);
        });

    }

    public boolean isAvailable(String date) {
        if (date == null || date.trim().isEmpty()) {
            return false;
        }

        try {
            String[] part = date.split("\\-");
            if (part.length != 3) {
                return false;
            }

            int year = Integer.parseInt(part[0].trim());
            int month = Integer.parseInt(part[1].trim()) - 1;
            int day = Integer.parseInt(part[2].trim());

            Calendar inputDate = new GregorianCalendar(year, month, day);

            if (inputDate.get(Calendar.YEAR) != year || inputDate.get(Calendar.MONTH) != month
                    || inputDate.get(Calendar.DAY_OF_MONTH) != day) {
                return false;
            }

            Calendar tomorrow = Calendar.getInstance();
            tomorrow.add(Calendar.DAY_OF_MONTH, 1);

            return inputDate.compareTo(tomorrow) >= 0;

        } catch (Exception e) {
            return false;
        }
    }

    private void saveItemData() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", name.getText().toString());
        editor.putString("date", date.getText().toString());
        editor.putString("location", location.getText().toString());
        editor.putString("type", spinner.getSelectedItem().toString());
        editor.apply();
    }

    private void loadItemData() {
        name.setText(pref.getString("name", ""));
        date.setText(pref.getString("date", ""));
        location.setText(pref.getString("location", ""));
        String type = pref.getString("type", "");
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equals(type)) {
                spinner.setSelection(i);
                break;
            }
        }
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
    @Override
    protected void onRestart() {
        super.onRestart();
        loadItemData();
    }


}
