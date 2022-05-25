package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Deleteteam extends AppCompatActivity {
    private RequestQueue requestQueue;
    boolean iscreator;
    private String username;
    private String teamname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteteam);

        SharedPreferences sp;
        sp = getSharedPreferences("userdetails",MODE_PRIVATE);
        username = sp.getString("username", "");
        teamname = sp.getString("team", "");
    }

    public void checkifcreator()
    {
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
}