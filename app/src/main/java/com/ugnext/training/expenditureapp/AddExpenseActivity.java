package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class AddExpenseActivity extends AppCompatActivity {
    private Button submitExpense;
    ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        submitExpense = (Button) findViewById(R.id.submit);

        spinner = (ProgressBar) findViewById(R.id.progressbar);

        submitExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
            }
        });
    }
}