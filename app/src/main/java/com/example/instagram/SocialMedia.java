package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import androidx.appcompat.widget.Toolbar;

import java.io.ByteArrayOutputStream;

public class SocialMedia extends AppCompatActivity {
Toolbar toolbar;
    ViewPager viewPager;
        TabLayout tableLayout;
    tabadapter tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
        setTitle("Soical media");

        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.viewPager);
        tab=new tabadapter(getSupportFragmentManager());
        viewPager.setAdapter(tab);

        tableLayout=findViewById(R.id.tabLayout);
        tableLayout.setupWithViewPager(viewPager,false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.postImageItem)
        {
            if(Build.VERSION.SDK_INT>=23&& checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3000);
            }
            else{
                getChosenImage();
            }
        }
        else if(item.getItemId()==R.id.logoutUserItem)
        {
            ParseUser.getCurrentUser().logOut();
            finish();
            Intent in=new Intent(SocialMedia.this,MainActivity.class);
            startActivity(in);
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==3000){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                getChosenImage();
            }

        }

    }

    private void getChosenImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Toast.makeText(getContext(),"enter coisen image",Toast.LENGTH_SHORT).show();
        startActivityForResult(intent,4000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
if(requestCode==4000&&resultCode== Activity.RESULT_OK&&data!=null)
{
    try {
        Uri selectedImage = data.getData();
        Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
        ByteArrayOutputStream btye=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,btye);
        byte[] bytes=btye.toByteArray();

        ParseFile parseFile=new ParseFile("pic.png",bytes);
        ParseObject parseObject=new ParseObject("photo");
        parseObject.put("picture",parseFile);
        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
        final ProgressDialog progressDialog= new ProgressDialog(SocialMedia.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                progressDialog.dismiss();
                if(e==null){
                    Toast.makeText(SocialMedia.this,"Updated ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(SocialMedia.this,"Try again ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }catch (Exception e)
    {
        e.printStackTrace();
    }
}
    }
}
