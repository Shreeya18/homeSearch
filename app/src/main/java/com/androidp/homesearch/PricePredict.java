package com.androidp.homesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class PricePredict extends AppCompatActivity{

    private EditText total_sqft, bath, bhk;
    private Button predbtn;
    private TextView price,loc;
    private String url = "https://bhaveshree.herokuapp.com/";
    private String newItem;
    private ProgressBar prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_predict);

        // Initializing variables for Prediction
        total_sqft = findViewById(R.id.sqft);
        bath = findViewById(R.id.bath);
        bhk = findViewById(R.id.bhk);
        predbtn = findViewById(R.id.predbtn);
        price = findViewById(R.id.price);

        //Other
        prog = (ProgressBar)findViewById(R.id.prog);

        // Creating Spinner
        Spinner spinner = findViewById(R.id.spin1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Spinner Added 
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newItem = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        predbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prog.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        prog.setVisibility(View.GONE);
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String data = jsonObject.getString("estimated_price");
                            price.setText((data + " Lakhs Only"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PricePredict.this,error.getMessage() + "Something Went Wrong!",Toast.LENGTH_SHORT).show();
                    }
                }){

                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("total_sqft", total_sqft.getText().toString());
                        params.put("location", newItem);
                        params.put("bhk", bhk.getText().toString());
                        params.put("bath", bath.getText().toString());
                        return params;
                    }

                };

                RequestQueue queue = Volley.newRequestQueue(PricePredict.this);
                queue.add(stringRequest);
            }
        });
    }
}