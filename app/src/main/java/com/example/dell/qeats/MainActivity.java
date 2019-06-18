package com.example.dell.qeats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    JSONObject restaurantResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("loginFile",
                MODE_PRIVATE);
        String userId = prefs.getString("userId","");


        String URL="http://35.200.177.211:8081/qeats/v1/orders?userId="+userId;

        RequestQueue requestQueue= Volley.newRequestQueue(this);


        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Rest Response",response.toString());
                        displayOrder(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response",error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);



        //System.out.println("Cart-"+cartId);
        //System.out.println("Resturant Id-"+restaurantId);
        //System.out.println("userId-"+userId);
        //System.out.println("items-"+items);
        //System.out.println("timeplaced-"+timePlaced);






    }
    public void displayOrder(JSONObject jsonOrder)
    {
        List<Order> orderList=new ArrayList<>();
        OrderAdapter orderAdapter;

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] itemName=new String[100];
        String[] total=new String[100];
        String[] timePlaced=new String[100];
        String[] status=new String[100];
        String[] restaurantName=new String[100];
        String[] city=new String[100];
        String[] imageURL=new String[100];
        String sItem="";
        int n=0;
        JSONArray[] items=new JSONArray[100];

        try {
            //cartId=response.getString("id");
            JSONArray insideOrder=jsonOrder.getJSONArray("orders");
            n=insideOrder.length();
            for(int i=0; i < insideOrder.length(); i++) {
                JSONObject jsonobject = insideOrder.getJSONObject(i);
                items[i]=jsonobject.getJSONArray("items");
                int j;
                for(j=0;j<items[i].length()-1;j++)
                {
                    JSONObject item = items[i].getJSONObject(j);
                    sItem+=item.getString("name");
                    sItem+=",";
                }
                JSONObject item = items[i].getJSONObject(j);
                sItem+=item.getString("name");
                itemName[i]=sItem;
                timePlaced[i]=jsonobject.getString("timePlaced");
                total[i]=jsonobject.getString("total");
                status[i]=jsonobject.getString("status");
                JSONObject restaurant=jsonobject.getJSONObject("restaurant");
                restaurantName[i]=restaurant.getString("name");
                city[i]=restaurant.getString("city");
                imageURL[i]=restaurant.getString("imageUrl");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


       // System.out.println(rest.toString());
        for(int i=0;i<n;i++) {
            orderList.add(new Order(restaurantName[i], city[i], itemName[i], timePlaced[i], total[i], status[i], imageURL[i]));
        }

        orderAdapter=new OrderAdapter(this,orderList);
        recyclerView.setAdapter(orderAdapter);

    }

}
