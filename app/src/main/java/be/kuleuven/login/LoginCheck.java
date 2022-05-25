package be.kuleuven.login;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import java.lang.String;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginCheck extends AppCompatActivity {

    private RequestQueue requestQueue;
    /*Bundle extras = getIntent().getExtras();
    */

    Intent intent = getIntent();
    Bundle extras = intent.getExtras();
    String user = extras.getString("Username");
    String pass = extras.getString("Password");

    String tempp = "ron";



    private String Request_url= "https://studev.groept.be/api/a21pt315/LOGIN_CHECK";

    StringRequest submitRequest = new StringRequest(Request.Method.GET,Request_url,new Response.Listener<String>() {
        @Override
        public void onResponse(String response1) {
            try {
                JSONArray response = new JSONArray(response1);
                for (int i = 0; i < response.length(); ++i) {
                    JSONObject o = new JSONObject();
                    try {
                        o = response.getJSONObject(i);
                        if (o.get("username") == user) {
                            if (o.get("password") == pass) {
                                //pass to home
                                Intent intent1 = new Intent(LoginCheck.this, home.class);
                                intent1.putExtra("Username", user);
                                intent1.putExtra("Password", pass);
                                startActivity(intent1);
                            }

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

            Toast.makeText(LoginCheck.this, "Unable to Login", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(LoginCheck.this,MainActivity.class);
            startActivity(intent1);
        }
    });



}

