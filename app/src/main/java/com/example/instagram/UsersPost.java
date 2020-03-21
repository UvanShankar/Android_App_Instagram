package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class UsersPost extends AppCompatActivity {
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);
        linearLayout=(LinearLayout)findViewById(R.id.linearlay);
        Intent recieved=getIntent();
        final String username=recieved.getStringExtra("username");
        this.setTitle(username+"'s Post");
        ParseQuery<ParseObject> parseQuery=new ParseQuery("photo");
        parseQuery.whereEqualTo("username",username);
        parseQuery.orderByDescending("createdAt");
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null&&objects.size()>0)
                {
                    for(ParseObject in :objects)
                    {
                        final TextView textView=new TextView(UsersPost.this);
                        textView.setText(in.getString("imagedes"+""));
                        ParseFile img=(ParseFile)in.get("picture");
                        img.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(e==null&&data!=null)
                                {
                                    Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
                                    ImageView imageView=new ImageView(UsersPost.this);
                                    LinearLayout.LayoutParams imgparam=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                                    imgparam.setMargins(5,5,5,5);
                                    imageView.setLayoutParams(imgparam);;
                                    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

                                    textView.setLayoutParams(imgparam);
                                    textView.setGravity(Gravity.CENTER);
                                    textView.setBackgroundColor(Color.BLUE);
                                    textView.setTextColor(Color.WHITE);;
                                    textView.setTextSize(30f);

                                    linearLayout.addView(imageView);
                                    linearLayout.addView(textView);
                                }
                            }
                        });

                    }
                }else{
                    Toast.makeText(UsersPost.this,username+" doesn't have any post ",Toast.LENGTH_SHORT).show();
                    finish();
                }
                dialog.dismiss();
            }
        });
    }
}
