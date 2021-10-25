package com.layout.reecycle_view_api;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    private EditText searchBar1;
    private RecyclerView recycle;
    private ArrayList<GetSet> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        searchBar1 = (EditText) findViewById(R.id.search_bar1);
        recycle = (RecyclerView) findViewById(R.id.recycle);

        get_data();
        recycle.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(arrayList, this);

        searchBar1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }

            public void filter(String text) {
                ArrayList<GetSet> searchList = new ArrayList<>();
                for (GetSet list : arrayList)
                {
                    if (list.getName().toLowerCase().contains(text.toString()))
                    {
                        searchList.add(list);
                    }
                }
                myAdapter.filtered_list(searchList);
            }

        });

    }

    private void get_data() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://192.168.157.93/API-INTEGRATION/api_fetch_all.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String s = jsonObject.getString("success");

                    if (s.equals("1")) {

                        String data = jsonObject.getString("data");
                        JSONArray jsonArray = new JSONArray(data);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String id = jsonObject1.getString("id");
                            String name = jsonObject1.getString("name");
                            String email = jsonObject1.getString("email");
                            String age = jsonObject1.getString("age");

                            GetSet getSet = new GetSet(id, name, email, age);

                            arrayList.add(getSet);
                            recycle.setAdapter(myAdapter);
                        }
                        myAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(MainActivity.this, "data can not be accessed", Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                HashMap hashMap = new HashMap();
                hashMap.put("id", "hacker");
                hashMap.put("pass", "007");

                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}