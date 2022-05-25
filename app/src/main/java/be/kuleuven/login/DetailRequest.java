package be.kuleuven.login;

import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailRequest extends AppCompatActivity {
    private String username;
    private RequestQueue requestQueue;
    static String temp;

    private int idUser;
    private String password;
    private String name;
    private int score;
    private String profilePic;

    public DetailRequest(String username)
    {
        this.username = username;
    }

    public void getdetails()
    {

        String Request_url = "https://studev.groept.be/api/a21pt319/alluserdetails/"+username;

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest queueRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String info = "";
                for (int i=0; i<response.length(); ++i) {
                    JSONObject o = null;
                    try {
                        o = response.getJSONObject(i);
                        setIdUser(o.getInt("idUser"));
                        setName(o.getString("Name"));
                        setUsername(o.getString("Username"));
                        setPassword(o.getString("Password"));
                        setScore(o.getInt("Score"));
                        setProfilePic(o.getString("Profilepic"));

                        setonshared();
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

    private void setonshared() {
        SharedPreferences sp;
        sp = getSharedPreferences("login",MODE_PRIVATE);
        sp.edit().putString("name",this.name).apply();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }


}
