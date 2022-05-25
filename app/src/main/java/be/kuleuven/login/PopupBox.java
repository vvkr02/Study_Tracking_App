package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopupBox extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_box);
        Button btn_challenge = findViewById(R.id.btn_challenge);
        Button btn_answer = findViewById(R.id.btn_ans);

        btn_challenge.setOnClickListener(this);

    }

    public void btnProfile(View Caller)
    {

    }

    public void btnChallenge(View Caller)
    {
        Intent intent = new Intent(this,challenge.class);
        startActivity(intent);

    }

    public void btnAnswer(View Caller)
    {
        Intent intent = new Intent(this,answer_challenge.class);
        startActivity(intent);

    }

    public void btnGrade(View Caller)
    {


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_challenge:
                Toast.makeText(this, "Challenge", Toast.LENGTH_SHORT).show();

        }
    }
}