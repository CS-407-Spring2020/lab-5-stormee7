package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    TextView welcome_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome_message = (TextView) findViewById(R.id.welcome_message);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        welcome_message.setText("Welcome " + str + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.welcome_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
                SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                sharedPreferences.edit().remove("username").apply();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}