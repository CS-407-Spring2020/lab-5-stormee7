package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        EditText myTextField = (EditText) findViewById(R.id.username_box);
        String str = myTextField.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username",str).apply();
        goToWelcome(str);
    }

    public void goToWelcome(String s){
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString(usernameKey,"").equals("")){
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.putExtra("message", sharedPreferences.getString(usernameKey,""));
            startActivity(intent);
        }else{
            setContentView(R.layout.activity_main);

        }
    }
}
