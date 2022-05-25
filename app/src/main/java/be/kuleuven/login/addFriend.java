package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class addFriend extends AppCompatActivity {
    private EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this,friends.class);
        startActivity(intent);
    }


    public void onBtnADDFRIEND_clicked(View caller){
        username = findViewById(R.id.teamname);
        String name1;
        String username1;
        String score1;
        String Request_url = "https://studev.groept.be/api/a21pt319/check_user_exists/"+username.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject o = null;
                boolean count = false;
                if (response.length()!=0){

                    try {

                        o = response.getJSONObject(0);

                        String temp = o.getString("IF(Username is not null,\"Yes\",\"No\")").toString();
                        if (temp.equals("Yes")){

                            count = true;
                            addToDB(o.getString("Username").toString(), o.getString("Name").toString(), o.getString("Score").toString());

                        }


                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(count == false)
                {
                    username.setText("");
                    Toast.makeText(addFriend.this, "User not Found", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    username.setText("");

                    Toast.makeText(addFriend.this, "User Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(addFriend.this,friends.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(addFriend.this, "Some Error Occured", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(submitRequest);


    }

    private void addToDB(String username, String name, String score) {

        String Request_url = "https://studev.groept.be/api/a21pt319/add_friend/" + username+"/" + name+"/"+ score;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(submitRequest);


    }
}