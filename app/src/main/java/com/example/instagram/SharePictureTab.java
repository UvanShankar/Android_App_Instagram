package com.example.instagram;


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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharePictureTab extends Fragment implements View.OnClickListener {
ImageView imageView;
Button button;
EditText editText;
    Bitmap r;
    public SharePictureTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_share_picture_tab, container, false);
    imageView=view.findViewById(R.id.imageView);
    button=view.findViewById(R.id.button6);
    editText=view.findViewById(R.id.editText5);
        imageView.setOnClickListener(SharePictureTab.this);
        button.setOnClickListener(SharePictureTab.this);

        return view;

    }

    @Override
    public void onClick(View v) {
switch(v.getId())
        {
            case R.id.button6:
                if(r!=null)
                {
                    if(editText.getText().toString().equals(""))
                    {
                        Toast.makeText(getContext(),"Enter Description ",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                        r.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                        byte[] bytes=byteArrayOutputStream.toByteArray();
                        ParseFile parseFile=new ParseFile("pic.png",bytes);
                        ParseObject parseObject=new ParseObject("photo");
                        parseObject.put("picture",parseFile);
                        parseObject.put("imagedes",editText.getText().toString());
                        parseObject.put("username", ParseUser.getCurrentUser().getUsername());
                        final ProgressDialog progressDialog= new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading");
                        progressDialog.show();
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                progressDialog.dismiss();
                                if(e==null){
                                    Toast.makeText(getContext(),"Updated ",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getContext(),"Try again ",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                else{

Toast.makeText(getContext(),"Select Image ",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imageView:
                if(Build.VERSION.SDK_INT>=23&& ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1000);
                }
                else{
                    getChosenImage();
                }
                break;


        }

    }

    private void getChosenImage() {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
       // Toast.makeText(getContext(),"enter coisen image",Toast.LENGTH_SHORT).show();
        startActivityForResult(intent,2000);
        //Toast.makeText(getContext(),"out coisen image",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                getChosenImage();
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       // Toast.makeText(getContext(),"enter onactres",Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==2000){
        //Toast.makeText(getContext(),"enter 1stif",Toast.LENGTH_SHORT).show();
        if(resultCode== Activity.RESULT_OK){
            Toast.makeText(getContext(),"enter 2ndife",Toast.LENGTH_SHORT).show();
            try {
            Uri selectedImage = data.getData();
            String[] path = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, path, null, null, null);
            cursor.moveToFirst();
            int col = cursor.getColumnIndex(path[0]);
            String pth = cursor.getString(col);
            cursor.close();
            r = BitmapFactory.decodeFile(pth);
            imageView.setImageBitmap(r);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        }
    }
    }
}
