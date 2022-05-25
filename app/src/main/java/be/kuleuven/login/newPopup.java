package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class newPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_popup);
    }

    public void onBtnChallenge_CLicked(View v)
    {
        Intent intent = new Intent(this,challenge.class);
        startActivity(intent);
    }
}