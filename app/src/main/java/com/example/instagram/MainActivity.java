package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3;
Button b1;
ConstraintLayout l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        b1=(Button)findViewById(R.id.button);
        l1=(ConstraintLayout)findViewById(R.id.con);
        if(ParseUser.getCurrentUser()!=null)
        {
            ParseUser.getCurrentUser().logOut();
        }
        e3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN)
                    signup(b1);
                return false;
            }
        });
    }

    public void login(View view) {
        Intent in=new Intent(MainActivity.this,Login_Activity.class);
        startActivity(in);
    }

    public void signup(View view) {
        ParseUser user = new ParseUser();
        user.setUsername(e1.getText().toString());
        user.setEmail(e2.getText().toString());
        user.setPassword(e3.getText().toString());
       // Toast.makeText(this,e2.getText().toString()+"jj",Toast.LENGTH_SHORT).show();
        if (e1.getText().toString().equals("") || e2.getText().toString().equals("") || e3.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Fill all the columns", Toast.LENGTH_SHORT).show();
        } else {
            final ProgressDialog po = new ProgressDialog(this);
            po.setMessage("Signing In" + user.getUsername());
            po.show();

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    po.dismiss();

                    if (e == null) {
                        Intent intent=new Intent(MainActivity.this,SocialMedia.class);
                        startActivity(intent);
                        //MainActivity.this.finish();
                    } else {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public void layoutclicked(View v)
    {try {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    }
    }
