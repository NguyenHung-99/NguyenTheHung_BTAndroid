package test.room.api.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    List<User> data = new ArrayList<>();
    public static final String TAG = "MyTag";
    StringRequest stringRequest; // Assume this exists.
    RequestQueue requestQueue;  // Assume this exists.
    private RecyclerView dataview;
    Gson gson = new Gson();
    Button them;
    Button refesh;
    UserAdapter adapter;
   EditText ten;
   EditText tuoi;
     TextView id;
   public static String url ="https://5fd22a67b485ea0016eef90a.mockapi.io/api/test";
// Set the tag on the request.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

ten = findViewById(R.id.tencanthem);
tuoi = findViewById(R.id.tuoicanthem);
them = findViewById(R.id.buttonthem);
refesh = findViewById(R.id.refesh);
id= findViewById(R.id.idcanthem);
        dataview = findViewById(R.id.dataview);

// Instantiate the RequestQueue.
      //  RequestQueue queue = Volley.newRequestQueue(this);
        getApi();

        refesh.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();
                getApi();
                adapter.notifyDataSetChanged();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!id.getText().toString().equals(""))
               {
                   PutApi(url,id.getText().toString());
                   data.clear();
                   getApi();
                   adapter.notifyDataSetChanged();

               }
               else{
                   PostApi(url);
                   data.clear();
                   getApi();
                   adapter.notifyDataSetChanged();
               }

            }
        });

// Request a string response from the provided URL.



// Add the request to the RequestQueue.
        //queue.add(stringRequest);


    }

    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }


    public  void  getApi(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        data= gson.fromJson(response, new TypeToken<List<User>>(){}.getType());
                        adapter= new UserAdapter(data,getApplicationContext());

                        dataview.setAdapter(adapter);
        Log.d("tabbbb", data.toString());
                        adapter.notifyDataSetChanged();
        dataview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("erre", error.toString());

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void PostApi(String url){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
              //  params.put("id", "Lâm");
                params.put("ten", ten.getText().toString());
                params.put("tuoi", tuoi.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void PutApi(String url,String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("ten", ten.getText().toString());
                params.put("tuoi", tuoi.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void DeleteApi(String url, String id){
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}