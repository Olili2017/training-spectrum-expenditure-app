package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private CardView addExpense;
    private CardView addIncome;
    private CardView viewTransactions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addExpense = (CardView)findViewById(R.id.addExpense);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToExpenseAddition = new Intent(v.getContext(), AddExpenseActivity.class);
                startActivity(moveToExpenseAddition);
                overridePendingTransition(0,0);
            }
        });
        viewTransactions = (CardView)findViewById(R.id.viewTransaction);
        viewTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToViewTransactions = new Intent(v.getContext(), TransactionsActivity.class);
                startActivity(moveToViewTransactions);
            }
        });

        addIncome = (CardView) findViewById(R.id.addIncome);
        addIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToIncomeAddition = new Intent(v.getContext(), AddIncomeActivity.class);
                startActivity(moveToIncomeAddition);
            }
        });
    }
}
