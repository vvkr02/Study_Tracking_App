package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.util.ArrayList;
import java.util.Locale;

public class timer extends AppCompatActivity {
    private int START_TIME_IN_MILLIS = 60000/10;
    private RequestQueue requestQueue;
    private TextView mTextViewCountDown;
    private ProgressDialog progressDialog;
    private Button mButtonStartPause;
    private Button mButtonReset;
    int id;
    String username;
    static int temp;



    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private int min =START_TIME_IN_MILLIS/60000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("Username");
        getid(username);


        mTextViewCountDown = findViewById(R.id.timer_module);
        mButtonStartPause = findViewById(R.id.mButtonStartPause);
        mButtonReset = findViewById(R.id.mButtonReset);





        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void getid(String username) {

        String Request_url = "https://studev.groept.be/api/a21pt319/alluserdetails/"+username;

        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject o = null;
                try {
                    o = response.getJSONObject(0);
                    putint(o.getInt("idUser"));


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

    private void putint(int idUser) {
        id = idUser;
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateScore(START_TIME_IN_MILLIS);
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);

    }



    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);

    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

    public void onBtnplus(View v)
    {
        START_TIME_IN_MILLIS = START_TIME_IN_MILLIS + (10*60000);
        mTimeLeftInMillis = mTimeLeftInMillis + (10*60000);
        updateCountDownText();
    }

    public void onBtnminus(View v)
    {
        START_TIME_IN_MILLIS = START_TIME_IN_MILLIS - (10*60000);
        mTimeLeftInMillis = mTimeLeftInMillis - (10*60000);
        if(mTimeLeftInMillis<600000)
        {
            START_TIME_IN_MILLIS = 600000;
            mTimeLeftInMillis = 600000;
            Toast.makeText(this, "1 min is the least possible time", Toast.LENGTH_SHORT).show();
        }
        updateCountDownText();
    }



    private void updateScore(int start_time_in_millis) {
        int inmin = start_time_in_millis/60000;
        int scoreupdate = inmin;
        String Request_url = "https://studev.groept.be/api/a21pt319/updatescore/"+scoreupdate+"/"+id;
        Toast.makeText(this, Request_url, Toast.LENGTH_SHORT).show();
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest submitRequest = new JsonArrayRequest(Request.Method.GET,Request_url,null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(timer.this, "Score Updated", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
        requestQueue.add(submitRequest);


    }
}