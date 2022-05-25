package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button btnLogin;
    private String Request_url= "https://studev.groept.be/api/a21pt319/login_check";
    private User u1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        username.setText("madlad");
        password.setText("123");


/*        SharedPreferences sp;
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp.edit().putString("username",username.getText().toString());*/

    }


    public void onBtnLogin_Clicked(View caller) {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);




        /*Intent intent = new Intent(this, LoginCheck.class);
        *//*intent.putExtra("Username", username.getText());
        intent.putExtra("Password", password.getText());*//*

        /// We cannot transfer data using intents between fragments


        Bundle val = new Bundle();
        val.putString("Username",username.getText().toString());
        val.putString("Password",password.getText().toString());

        intent.putExtras(val);

        startActivity(intent);*/
        //IMPORTANT
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    int temp = 0;

                    //JSONArray response = new JSONArray(response1);
                    /*Toast.makeText(MainActivity.this, response1, Toast.LENGTH_SHORT).show();*/
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject o = null;
                        try {
                            o = response.getJSONObject(i);
//                            Toast.makeText(MainActivity.this, o.getString("username").toString()+
//                                    ','+o.getString("password").toString(), Toast.LENGTH_SHORT).show();
                            if (o.getString("username").toString().equals(username.getText().toString())) {
                                if (o.getString("password").toString().equals(password.getText().toString())) {
                                    //pass to home
                                    //addtoTrackUser(o.getString("username").toString());
                                    String name = o.getString("username").toString();

                                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(MainActivity.this, home.class);
                                    intent1.putExtra("Username", o.getString("username"));
                                   /* SharedPreferences.Edif
                                    SharedPreferences sp;
                                    sp = getSharedPreferences("login",MODE_PRIVATE);
                                    sp.edit().putString("username",username.getText().toString());*/

                                    /*intent1.putExtra("Username", username.toString());
                                    intent1.putExtra("Password", password);*/
                                    startActivity(intent1);
                                    temp++;
                                }


                            }

                            if (temp ==0){
                                Toast.makeText(MainActivity.this, "Unable to Login (UserData Not Found)", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Unable to Login", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(submitRequest);


    }

    private void addtoTrackUser(String s) {

        User u1 = null;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://studev.groept.be/api/a21pt319/alluserdetails/"+s;
        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int temp = 0;

                    JSONObject o = null;
                    try {
                        o = response.getJSONObject(1);

                        u1.setIdUser(o.getInt("idUser"));
                        u1.setName(o.getString("Name").toString());
                        u1.setUsername(o.getString("Username").toString());
                        u1.setPassword(o.getString("Password").toString());
                        u1.setScore(o.getInt("Score"));
                        u1.setProfilePic(o.getString("Profilepic").toString());

//

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error!!!!", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(submitRequest);

    }

    public void onBtnRegister_Clicked(View caller){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }
}