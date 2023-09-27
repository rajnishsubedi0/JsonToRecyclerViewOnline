package com.example.jsontorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<DataKeeperClass> arrayListObjectData;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayListObjectData=new ArrayList<DataKeeperClass>();
        recyclerView=findViewById(R.id.recyclerViewForJsonLoad);

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String url="https://www.jsonkeeper.com/b/9KON";
        CustomAdapter customAdapter=new CustomAdapter(arrayListObjectData);



       JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "response got", Toast.LENGTH_SHORT).show();
               try {
                    JSONArray jsonArray=response.getJSONArray("lists");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObjectName=jsonArray.getJSONObject(i);
                        arrayListObjectData.add(new DataKeeperClass(jsonObjectName.getString("name")));

                    }

                    CustomAdapter customAdapter=new CustomAdapter(arrayListObjectData);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(customAdapter);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "response Not got", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);



    }


}
