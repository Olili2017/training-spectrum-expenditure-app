package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddIncomeActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextInputEditText name;
    private EditText amount;
    private String currentDate;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                currentDate = String.format("%02d", dayOfMonth) + "-" + String.format("%02d", month) + "-" + String.format("%04d", year);

            }
        });

        amount = (EditText) findViewById(R.id.amount);
        name = (TextInputEditText) findViewById(R.id.name);

        button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),name.getText().toString()+" "+currentDate+" "+amount.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });



    }
}