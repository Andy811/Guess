package com.andy.guess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int guessTime = 0;
    private int random;
    String TAG = MainActivity.class.getSimpleName();
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        random = new Random().nextInt(10)+1;


        Log.d(TAG,"secret:"+random);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }

    public void check(View view) {
        String message = "";
        guessTime+=1;
        EditText answer = findViewById(R.id.ed_Answer);
        TextView info = findViewById(R.id.tv_info);
        TextView guess = findViewById(R.id.tv_guessTime);
        int  num = Integer.valueOf(answer.getText().toString());
       if (num<random){
           // info.setText("大一點");
           message = "大一點";
           listener = null;
        }else if (num>random){
           // info.setText("小一點")
           message = "小一點";
           listener = null;
        }else{
           message = "答對拉";
           listener = new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   random = new Random().nextInt(10)+1;
                   guessTime = 0;
                   Log.d(TAG,"secret:"+random);
               }
           };
       }
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("友情提示")
                .setMessage(message)
                .setPositiveButton("OK",listener)
                .show();
        guess.setText("猜了"+Integer.toString(guessTime)+"次");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
