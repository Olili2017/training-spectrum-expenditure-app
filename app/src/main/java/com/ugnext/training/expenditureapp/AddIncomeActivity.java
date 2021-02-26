package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.textfield.TextInputEditText;


import java.util.HashMap;
import java.util.Map;


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
                final String URL = "http://192.168.1.11/android/income.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                        @Override
                        protected Map<String,String> getParams(){
                            Map<String,String> params = new HashMap<String, String>();

                            params.put("name", name.getText().toString());
                            params.put("amount", amount.getText().toString());
                            params.put("date", currentDate);
                            return params;
                        }

                        // add the request object to the queue to be executed
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest).

                    //Toast.makeText(getApplicationContext(),name.getText().toString()+" "+currentDate+" "+amount.getText().toString(), Toast.LENGTH_LONG).show();
                    };
            }
        });






    }
}