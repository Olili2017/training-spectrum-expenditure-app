package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class TransactionsActivity extends AppCompatActivity {
    private static final String URL = "https://system.kessd.org/api/v1/fetch.php";
    private ArrayList<TransactionsClass> transactionsClassArrayList;
    private TransactionsAdapter transactionsAdapter;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        progressDialog = new ProgressDialog(this);
//
        progressDialog.setMessage("Fetching Data ...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerview);
        transactionsClassArrayList = new ArrayList<>();
        transactionsAdapter = new TransactionsAdapter();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray incomeArray = jsonObject.getJSONArray("income");

                    for (int i = 0;  i< incomeArray.length(); i++){
                        JSONObject fields = incomeArray.getJSONObject(i);

                        transactionsClassArrayList.add(new TransactionsClass(
                                fields.getString("amount"),
                                fields.getString("name"),
                                fields.getString("date")));
                    }

                    JSONArray expenseArray = jsonObject.getJSONArray("income");

                    for (int i = 0;  i< expenseArray.length(); i++){
                        JSONObject fields = expenseArray.getJSONObject(i);

                        transactionsClassArrayList.add(new TransactionsClass(
                                fields.getString("amount"),
                                fields.getString("name"),
                                fields.getString("date")));
                    }

                    transactionsAdapter.setTransactions(transactionsClassArrayList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionsActivity.this,RecyclerView.VERTICAL, false));
                    recyclerView.setAdapter(transactionsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Log.d("Transactions",transactionsClassArrayList.toString());
    }

}