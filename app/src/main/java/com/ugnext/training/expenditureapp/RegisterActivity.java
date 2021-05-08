package com.ugnext.training.expenditureapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextView login;
    private EditText email;
    private EditText password;
    private Button register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (SharedPrefUser.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            return;
        }

        register = findViewById(R.id.registerBtn);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validation = true;
                if (email.getText().toString().isEmpty()){
                    email.setError("Email is empty");
                    validation = false;
                }

                if (password.getText().toString().trim().isEmpty()){
                    password.setError("Password can't be empty");
                    validation = false;
                }

                if (validation){
                    progressDialog.setMessage("Registering");
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTER_USER, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                if (jsonObject.getBoolean("status")){
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", email.getText().toString().trim());
                            params.put("password", password.getText().toString().trim());
                            return params;
                        }
                    };

                    RequestHandler.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
                }

            }
        });
    }
}