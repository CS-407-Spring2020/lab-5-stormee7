package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEditor extends AppCompatActivity {

    int noteid = -1;
    Button save;
    EditText noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        save = (Button) findViewById(R.id.save_button);
        noteText = (EditText) findViewById(R.id.note_content);
        noteid = getIntent().getIntExtra("noteid",-1);
        if(noteid != -1){
            Note note = WelcomeActivity.notes.get(noteid);
            String noteContent = note.getContent();
            noteText.setText(noteContent);
        }
    }

    public void clickFunction(View view){
        //1. Get editText view and the content that the user entered
        EditText myTextField = (EditText) findViewById(R.id.note_content);
        String content = myTextField.getText().toString();
        //2. initialize SQLiteDatabase instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        //3. Initialize DBHelper class
        DBHelper myDB = new DBHelper(sqLiteDatabase);
        //4. Set username by fetching it from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        // 5. Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){
            title = "NOTE_" + (WelcomeActivity.notes.size() + 1);
            myDB.saveNotes(username, title, content, date);
        } else{
            title = "NOTE_" + (noteid + 1);
            myDB.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
