package com.yourcompany.bro.hi.comments;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
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
import java.util.List;
import java.util.Objects;

public class CommentsActivity extends AppCompatActivity {


    private static final String JSON_URL = "http://jsonplaceholder.typicode.com/comments";
    RecyclerView recyclerView;
    RecyclerVViewAdapter adapter;
    List<ModelJson> modelJsons;
    int itemRow = 0;
    ProgressBar progressBar;
    private String urlBonus;
    RequestQueue requestQueue;
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String upperBound = intent.getStringExtra("upperBound");
        String lowerBound = intent.getStringExtra("lowerBound");

        Log.i("Data upper:", upperBound);
        Log.i("Data lower:", lowerBound);

        urlBonus = "?_start=" + lowerBound + "&_end=" + upperBound;
        modelJsons = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.item_progress_bar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        adapter = new RecyclerVViewAdapter(modelJsons, getApplicationContext());
        recyclerView.setAdapter(adapter);

        takeJson();


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == modelJsons.size() - 1) {


                        takeJson();
                        isLoading = true;
                    }
                }
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(CommentsActivity.this, InputActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


//    private boolean isNetworkConnected() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        return cm.getActiveNetworkInfo() != null;
//    }

    private void takeJson() {
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {

                StringRequest stringrequest = new StringRequest(Request.Method.GET, JSON_URL + urlBonus,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("URL", JSON_URL + urlBonus);
                                try {
                                    JSONArray jarray = new JSONArray(response);

                                    for (int i = 0; i < 10; i++) {

                                        JSONObject demoObject = jarray.getJSONObject(itemRow);

                                        ModelJson modelJson = new ModelJson(demoObject.getString("id"), demoObject.getString("body"));
                                        modelJsons.add(modelJson);
                                        itemRow++;
                                        adapter.notifyDataSetChanged();
                                    }


                                    isLoading = false;

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(stringrequest);

                progressBar.setVisibility(View.GONE);
            }
        }, 1000);
    }


}




