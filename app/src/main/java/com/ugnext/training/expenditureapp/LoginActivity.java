package com.ugnext.training.expenditureapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextView register;
    private TextView forgot;
    private EditText email;
    private EditText password;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.register);
        forgot = findViewById(R.id.forgot);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = email.getText().toString().trim();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.RESET_PASSWORD+emailAddress, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "An error Occured", Toast.LENGTH_SHORT).show();
                    }
                });

                if (!emailAddress.isEmpty()){
                    progressDialog.setMessage("Reseting email");
                    progressDialog.show();
                    RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
                }else{
                    email.setError("Enter email to reset password");
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
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

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_USER, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                if (jsonObject.getBoolean("status")){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(LoginActivity.this, jsonObject.getJSONObject("data").toString(), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "An Error Occured", Toast.LENGTH_SHORT).show();
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

                    RequestHandler.getInstance(LoginActivity.this).addToRequestQueue(stringRequest);
                }

            }
        });
    }
}