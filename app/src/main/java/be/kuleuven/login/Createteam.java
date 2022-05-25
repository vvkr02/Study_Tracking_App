package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Createteam extends AppCompatActivity {

    private String username;
    private EditText teamname;
    private RequestQueue requestQueue;
    boolean ispresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createteam);
        teamname = findViewById(R.id.teamname);



        SharedPreferences sp;
        sp = getSharedPreferences("userdetails",MODE_PRIVATE);
        username = sp.getString("username","");



    }

    public void onSubmit_pressed(View caller)
    {

        checkIfPresent(teamname.getText().toString());
        if(ispresent == true)
        {
            Toast.makeText(this, "Team with name already exists", Toast.LENGTH_SHORT).show();
        }
        /*else
        {
            addtodatabase(username,teamname.getText().toString());

            Intent intent = new Intent(this,friends.class);
            startActivity(intent);
        }*/

    }
    private void addtodatabase(String username, String teamname) {

        String Request_url = "https://studev.groept.be/api/a21pt319/createteam/"+username+"/"+teamname+"/"+username;

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(queueRequest);

    }

    private void checkIfPresent(String s) {
        int count =0;

        String Request_url = "https://studev.groept.be/api/a21pt319/check_teamname/"+username;

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String info = "";
                for (int i=0; i<response.length(); ++i) {
                    JSONObject o = null;
                    try {
                        o = response.getJSONObject(i);
                       if(response.length() !=0)
                       {
                           Intent intent = new Intent(Createteam.this,timer.class);
                           startActivity(intent);
                       }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(queueRequest);

    }

    private void setIspresent() {
        this.ispresent = true;


    }


}