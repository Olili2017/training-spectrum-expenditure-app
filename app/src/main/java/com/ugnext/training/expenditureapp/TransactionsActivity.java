package com.ugnext.training.expenditureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    private static final String URL = "http://192.168.1.102/android/fetch.php";
    private ArrayList<TransactionsClass> transactionsClassArrayList;
    private TransactionsAdapter transactionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        transactionsClassArrayList = new ArrayList<>();

//        transactionsAdapter.setTransactions(transactionsClassArrayList);
//        recyclerView.setAdapter(transactionsAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    /*
                    {
    "income": [
        {
            "id": "14",
            "name": "",
            "amount": "0.0000",
            "date": "06-04-2021"
        },
        {
            "id": "13",
            "name": "scovia",
            "amount": "15000.0000",
            "date": "06-04-2021"
        },
        {
            "id": "12",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "11",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "10",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        }
    ],
    "expense": [
        {
            "id": "14",
            "name": "",
            "amount": "0.0000",
            "date": "06-04-2021"
        },
        {
            "id": "13",
            "name": "scovia",
            "amount": "15000.0000",
            "date": "06-04-2021"
        },
        {
            "id": "12",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "11",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "10",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "17",
            "name": "Alex work",
            "amount": "2500.0000",
            "date": "12-04-2021"
        },
        {
            "id": "16",
            "name": "meals",
            "amount": "25000.0000",
            "date": "18-04-2021"
        },
        {
            "id": "15",
            "name": "fredowampz",
            "amount": "58000.0000",
            "date": "14-04-2021"
        },
        {
            "id": "14",
            "name": "fredrick",
            "amount": "25000.0000",
            "date": "13-04-2021"
        },
        {
            "id": "13",
            "name": "fredrick",
            "amount": "17055.0000",
            "date": "05-04-2021"
        },
        {
            "id": "12",
            "name": "dfd",
            "amount": "5688.0000",
            "date": "22-04-2021"
        },
        {
            "id": "11",
            "name": "dfd",
            "amount": "5688.0000",
            "date": "13-04-2021"
        },
        {
            "id": "10",
            "name": "",
            "amount": "0.0000",
            "date": "03-04-2021"
        },
        {
            "id": "9",
            "name": "fredrick",
            "amount": "200000.0000",
            "date": "03-04-2021"
        },
        {
            "id": "8",
            "name": "fg",
            "amount": "565.0000",
            "date": "13-04-2021"
        },
        {
            "id": "7",
            "name": "dhdgh",
            "amount": "56866.0000",
            "date": "26-04-2021"
        },
        {
            "id": "6",
            "name": "fredowampz",
            "amount": "25000.0000",
            "date": "19-04-2021"
        },
        {
            "id": "5",
            "name": "frre",
            "amount": "665.0000",
            "date": "11-04-2021"
        },
        {
            "id": "4",
            "name": "fodr",
            "amount": "9494.0000",
            "date": "18-04-2021"
        },
        {
            "id": "3",
            "name": "fhg",
            "amount": "554.0000",
            "date": "06-04-2021"
        },
        {
            "id": "2",
            "name": "Frederick",
            "amount": "150000.0000",
            "date": "12-04-2021"
        },
        {
            "id": "1",
            "name": "full mine entrepreneurs logistics limited",
            "amount": "10000.0000",
            "date": "03-04-2021"
        }
    ],
    "status": true
}*/
                    JSONArray jsonArray = new JSONArray(response.getJSONArray("income"));

//                    Toast.makeText(TransactionsActivity.this, jsonArray.toString(), Toast.LENGTH_LONG).show();

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
        requestQueue.add(jsonObjectRequest);

    }
}