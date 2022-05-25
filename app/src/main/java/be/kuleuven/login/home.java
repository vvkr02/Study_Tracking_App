package be.kuleuven.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    String username;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle extras = getIntent().getExtras();
        username = extras.getString("Username");
        /*Toast.makeText(this, username, Toast.LENGTH_SHORT).show();*/
        textView = findViewById(R.id.textView);


        /*SharedPreferences sp;
        sp = getSharedPreferences("userdetails",MODE_PRIVATE);
        String temp = sp.getString("team","");
        Toast.makeText(this, temp, Toast.LENGTH_SHORT).show();
*/


    }

    public void onBtnTimer_Clicked(View caller){

        Intent intent = new Intent(this,timer.class);
        intent.putExtra("Username",username);
        startActivity(intent);



    }

    public void onBtnFriends_Clicked(View caller){
        Intent intent1  = new Intent(this,friends.class);
        intent1.putExtra("Username",username);
        startActivity(intent1);

    }
}