package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class UpdatePage extends AppCompatActivity {
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fromClass = getIntent().getStringExtra("fromClass");

        if ("palsetineclass".equals(fromClass)) {
            palestinemethod();
        } else if ("italyclass".equals(fromClass)) {
            italymethod();
        } else if ("japanclass".equals(fromClass)) {
            japanmethod();
        } else if ("russiaclass".equals(fromClass)) {
            russiamethod();
        } else {
            spainmethd();
        }
    }

    private void palestinemethod() {
        setContentView(R.layout.activity_updatepalestine);
        EditText name = findViewById(R.id.name);
        EditText date = findViewById(R.id.date);
        EditText location = findViewById(R.id.location);
        Spinner spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
       ImageButton check=findViewById(R.id.check);
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


        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    UpdatePage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);

                    },
                    year, month, day
            );

            dpd.show();
        });

        check.setOnClickListener(e -> {

            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter the name of the Item", Toast.LENGTH_SHORT).show();
            } else {
                item = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.equals(name.getText().toString())) {
                        item = MainActivity.items.get(i);
                    }
                }
                if (item == null) {
                    Toast.makeText(this, "The Item Name does not exist", Toast.LENGTH_SHORT).show();

                } else {
                    if (item.country.equalsIgnoreCase("Palestine")) {
                        name.setEnabled(false);
                        date.setText(item.date);
                        location.setText(item.Location);
                        int p = arr.getPosition(item.type);
                        spinner.setSelection(p);
                    } else {
                        Toast.makeText(this, "The Item in this Country " + item.country, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        update.setOnClickListener(e -> {
            if (date.getText().toString().trim().isEmpty() || location.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else {
                String country = "Palestine";
                String datetxt = date.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String locationtxt = location.getText().toString();


                if (isAvailable(datetxt)) {
                    MainActivity.items.remove(item);
                    Items item = new Items(name.getText().toString(), datetxt, locationtxt, type, country);
                    MainActivity.items.add(item);
                    MainActivity.acc.updatear(MainActivity.items);

                    Toast.makeText(this, "Item process updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void italymethod() {
        setContentView(R.layout.activity_updateitaly);
        EditText name = findViewById(R.id.name);
        EditText date = findViewById(R.id.date);
        EditText location = findViewById(R.id.location);
        Spinner spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
        ImageButton check=findViewById(R.id.check);

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


        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    UpdatePage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);

                    },
                    year, month, day
            );

            dpd.show();
        });
        check.setOnClickListener(e -> {
            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter the name of the Item", Toast.LENGTH_SHORT).show();
            } else {
                item = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.equals(name.getText().toString())) {
                        item = MainActivity.items.get(i);
                        Log.i("item value","item is"+item.name);
                    }
                }
                if (item == null) {
                    Toast.makeText(this, "The Item Name does not exist", Toast.LENGTH_SHORT).show();

                } else {
                    if (item.country.equalsIgnoreCase("Italy")) {
                        name.setEnabled(false);
                        date.setText(item.date);
                        location.setText(item.Location);
                        int p = arr.getPosition(item.type);
                        spinner.setSelection(p);
                    } else {
                        Toast.makeText(this, "The Item in this Country " + item.country, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        update.setOnClickListener(e -> {
            if (date.getText().toString().trim().isEmpty() || location.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else {
                String country = "Italy";
                String datetxt = date.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String locationtxt = location.getText().toString();

                if (isAvailable(datetxt)) {
                    MainActivity.items.remove(item);
                    Items item = new Items(name.getText().toString(), datetxt, locationtxt, type, country);
                    MainActivity.items.add(item);
                    MainActivity.acc.updatear(MainActivity.items);

                    Toast.makeText(this, "Item process updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void japanmethod() {
        setContentView(R.layout.activity_updatejapan);
        EditText name = findViewById(R.id.name);
        EditText date = findViewById(R.id.date);
        EditText location = findViewById(R.id.location);
        Spinner spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
        ImageButton check=findViewById(R.id.check);

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


        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    UpdatePage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);

                    },
                    year, month, day
            );

            dpd.show();
        });
        
        check.setOnClickListener(e -> {
            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter the name of the Item", Toast.LENGTH_SHORT).show();
            } else {
                item = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.trim().equals(name.getText().toString().trim())) {
                        item = MainActivity.items.get(i);
                    }
                }
                if (item == null) {
                    Toast.makeText(this, "The Item Name does not exist", Toast.LENGTH_SHORT).show();

                } else {
                    if (item.country.equalsIgnoreCase("Japan")) {
                        Log.i("Item ","Item value is"+item.name);

                        name.setEnabled(false);
                        date.setText(item.date);
                        location.setText(item.Location);
                        int p = arr.getPosition(item.type);
                        spinner.setSelection(p);
                    } else {
                        Toast.makeText(this, "The Item in this Country " + item.country, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        update.setOnClickListener(e -> {
            if (date.getText().toString().trim().isEmpty() || location.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else {
                String country = "Japan";
                String datetxt = date.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String locationtxt = location.getText().toString();


                if (isAvailable(datetxt)) {
                    MainActivity.items.remove(item);
                    Items item = new Items(name.getText().toString(), datetxt, locationtxt, type, country);
                    MainActivity.items.add(item);
                    name.setText("");
                    date.setText("");
                    location.setText("");
                    spinner.setSelection(0);
                    MainActivity.acc.updatear(MainActivity.items);
                    Toast.makeText(this, "Item process updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void russiamethod() {
        setContentView(R.layout.activity_updaterussia);
        EditText name = findViewById(R.id.name);
        EditText date = findViewById(R.id.date);
        EditText location = findViewById(R.id.location);
        Spinner spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
        ImageButton check=findViewById(R.id.check);

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


        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    UpdatePage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);

                    },
                    year, month, day
            );

            dpd.show();
        });
        check.setOnClickListener(e -> {
            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter the name of the Item", Toast.LENGTH_SHORT).show();
            } else {
                item = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.equals(name.getText().toString())) {
                        item = MainActivity.items.get(i);
                    }
                }
                if (item == null) {
                    Toast.makeText(this, "The Item Name does not exist", Toast.LENGTH_SHORT).show();

                } else {
                    if (item.country.equalsIgnoreCase("Russia")) {
                        name.setEnabled(false);
                        date.setText(item.date);
                        location.setText(item.Location);
                        int p = arr.getPosition(item.type);
                        spinner.setSelection(p);
                    } else {
                        Toast.makeText(this, "The Item in this Country " + item.country, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        update.setOnClickListener(e -> {
            if (date.getText().toString().trim().isEmpty() || location.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else {
                String country = "Russia";
                String datetxt = date.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String locationtxt = location.getText().toString();

                if (isAvailable(datetxt)) {
                    MainActivity.items.remove(item);
                    Items item = new Items(name.getText().toString(), datetxt, locationtxt, type, country);
                    MainActivity.items.add(item);
                    name.setText("");
                    date.setText("");
                    location.setText("");
                    spinner.setSelection(0);
                    MainActivity.acc.updatear(MainActivity.items);
                    Toast.makeText(this, "Item process updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void spainmethd() {
        setContentView(R.layout.activity_updatespain);
        EditText name = findViewById(R.id.name);
        EditText date = findViewById(R.id.date);
        EditText location = findViewById(R.id.location);
        Spinner spinner = findViewById(R.id.spinner);
        Button update = findViewById(R.id.updatebtn);
        ImageButton check=findViewById(R.id.check);

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


        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spinner.setAdapter(arr);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog dpd = new DatePickerDialog(
                    UpdatePage.this,
                    (view, calyear, calmonth, calday) -> {
                        String d = String.format("%04d-%02d-%02d", calyear, calmonth + 1, calday);
                        date.setText(d);

                    },
                    year, month, day
            );

            dpd.show();
        });
        check.setOnClickListener(e -> {
            if (name.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter the name of the Item", Toast.LENGTH_SHORT).show();
            } else {
                item = null;
                for (int i = 0; i < MainActivity.items.size(); i++) {
                    if (MainActivity.items.get(i).name.equals(name.getText().toString())) {
                        item = MainActivity.items.get(i);
                    }
                }
                if (item == null) {
                    Toast.makeText(this, "The Item Name does not exist", Toast.LENGTH_SHORT).show();

                } else {
                    if (item.country.equalsIgnoreCase("Spain")) {
                        name.setEnabled(false);
                        date.setText(item.date);
                        location.setText(item.Location);
                        int p = arr.getPosition(item.type);
                        spinner.setSelection(p);
                    } else {
                        Toast.makeText(this, "The Item in this Country " + item.country, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        update.setOnClickListener(e -> {
            if (date.getText().toString().trim().isEmpty() || location.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Please Enter all the Data", Toast.LENGTH_SHORT).show();

            } else {
                String country = "Spain";
                String datetxt = date.getText().toString();
                String type = spinner.getSelectedItem().toString();
                String locationtxt = location.getText().toString();


                if (isAvailable(datetxt)) {
                    Items item = new Items(name.getText().toString(), datetxt, locationtxt, type, country);
                    MainActivity.items.remove(item);
                    MainActivity.items.add(item);
                    MainActivity.acc.updatear(MainActivity.items);
                    Toast.makeText(this, "Item process updated successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Date is not Available", Toast.LENGTH_SHORT).show();
                }
            }
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


}
