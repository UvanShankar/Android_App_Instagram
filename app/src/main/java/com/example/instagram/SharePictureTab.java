package com.example.instagram;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharePictureTab extends Fragment implements View.OnClickListener {
ImageView imageView;
Button button;
EditText editText;

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

                break;
            case R.id.imageView:

                break;


        }

    }
}
