package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login_Activity extends AppCompatActivity {
EditText e1,e3;
Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        e1=(EditText)findViewById(R.id.e4);
        e3=(EditText)findViewById(R.id.e5);
        b1=(Button)findViewById(R.id.button3);
        String aa=e1.getText().toString();
        if(ParseUser.getCurrentUser()!=null)
        {
            ParseUser.getCurrentUser().logOut();
        }
        e3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN)
                    login(b1);
                return false;
            }
        });
    }

    public void signup(View view) {
finish();
    }

    public void login(View view) {
        String aa=e1.getText().toString();
        ParseUser user=new ParseUser();
        final ProgressDialog po=new ProgressDialog(this);
        po.setMessage("Signing In "+aa);
        po.show();
        user.logInInBackground(e1.getText().toString(), e3.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                po.dismiss();
                if(user!=null&&e==null)
                {
                    Intent intent=new Intent(Login_Activity.this,SocialMedia.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login_Activity.this,e.getMessage(),Toast.LENGTH_SHORT);
                }
            }
        });


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
