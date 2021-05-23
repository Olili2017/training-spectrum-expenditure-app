package com.ugnext.training.expenditureapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.textfield.TextInputEditText;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AddIncomeActivity extends AppCompatActivity {


    private CalendarView calendarView;
    private EditText name, amount;
    private String currentDate;
    private Button button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);

        calendarView = (CalendarView) findViewById(R.id.calendar);

        long todayDate = Calendar.getInstance().getTimeInMillis();
        calendarView.setDate(todayDate, true, true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                currentDate = String.format("%02d", dayOfMonth) + "-" + String.format("%02d", month) + "-" + String.format("%04d", year);

            }
        });

        progressDialog = new ProgressDialog(this);

        amount = (EditText) findViewById(R.id.amount);
        name = (EditText) findViewById(R.id.name);

        button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.ADD_INCOME, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            progressDialog.dismiss();
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(AddIncomeActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            if (jsonObject.getBoolean("status")){
                                amount.setText("");
                                name.setText("");
                                long todayDate = Calendar.getInstance().getTimeInMillis();
                                calendarView.setDate(todayDate, true, true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(AddIncomeActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", name.getText().toString());
                        params.put("amount", amount.getText().toString());
                        params.put("date", currentDate);
                        return params;
                    }
                };

                RequestHandler.getInstance(AddIncomeActivity.this).addToRequestQueue(stringRequest);

            }
        });

    }
}