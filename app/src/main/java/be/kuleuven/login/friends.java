package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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


public class friends extends AppCompatActivity {

    private RequestQueue requestQueue;
    private TextView txt1;
    private TextView[] txtArr = new TextView[9];
    Dialog mDialog;
    private Button btn_create;
    private Button btn_delete;

    private AlertDialog.Builder dialogBuilder;
    private  AlertDialog dialog;

    String username;
    String group;
    String team;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        SharedPreferences sp;
        sp = getSharedPreferences("userdetails",MODE_PRIVATE);
        team = sp.getString("team","");
        username = sp.getString("username","");
        int ir = team.length();
        String t = ""+ir;
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();


        /*Bundle extras = getIntent().getExtras();
        username = extras.getString("Username");*/
        getgroup(username);
        btn_create = findViewById(R.id.button6);
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setVisibility(View.VISIBLE);
        btn_create.setVisibility(View.INVISIBLE);

        String te = "null";

        if (team.toString().equals(te))
        {
            btn_create.setVisibility(View.VISIBLE);
        }




        txtArr[0] = findViewById(R.id.txt_frnd1);
        txtArr[1] = findViewById(R.id.txt_frnd2);
        txtArr[2] = findViewById(R.id.txt_frnd3);
        txtArr[3] = findViewById(R.id.txt_frnd4);
        txtArr[4] = findViewById(R.id.txt_frnd5);
        txtArr[5] = findViewById(R.id.txt_frnd6);
        txtArr[6] = findViewById(R.id.txt_frnd7);
        txtArr[7] = findViewById(R.id.txt_frnd8);
        txtArr[8] = findViewById(R.id.txt_frnd9);

        for(int i=0; i<9;i++) {
            txtArr[i].setText("");
        }


        String Request_url = "https://studev.groept.be/api/a21pt319/get_friends";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject o = null;
                for(int i=0; i< response.length();i++) {
                try {



                        o = response.getJSONObject(i);

                        String temp = o.getString("Name").toString() + " Score: " + o.getString("Score").toString();
                        txtArr[i].setText(temp);


                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(friends.this, "Unable to Display Friends", Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(submitRequest);


    }

    private void getgroup(String username) {

        String Request_url = "https://studev.groept.be/api/a21pt319/getgroup/"+username;

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject o = null;
                try {
                    o = response.getJSONObject(0);
                    putgroup(o.getString("team"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
        requestQueue.add(submitRequest);
    }

    private void putgroup(String group) {
        this.group = group;
    }


    public void onBtnaddFriend_Clicked(View caller){
        Intent intent = new Intent(this,addFriend.class);
        startActivity(intent);

    }

    public void onBtn_profileclicked(View valler){

        /*mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.activity_new_popup);
        mDialog.getWindow();
        mDialog.show();*/

        Intent intent = new Intent(this,PopupBox.class);
        startActivity(intent);

        /*dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.activity_new_popup,null);

        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
*/
    }

    public void onBtnChallenge_Clicked(View caller)
    {

        Intent intent = new Intent(this,challenge.class);
        intent.putExtra("Username",username);

        startActivity(intent);
    }

    public void onBtnAnswer_Clicked(View caller)
    {
        Intent intent = new Intent(this,answer_challenge.class);
        startActivity(intent);
    }

    public void onBtnProfile_Clicked(View caller)
    {
        Intent intent = new Intent(this,PopupBox.class);
        startActivity(intent);
    }

    public void onBtnGrade_Clicked(View caller)
    {

    }
    public void onBtn_CreateCLicked(View v)
    {
        Intent intent = new Intent(this,Createteam.class);
        startActivity(intent);
    }

}