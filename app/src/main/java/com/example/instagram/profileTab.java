package com.example.instagram;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * A simple {@link Fragment} subclass.
 */
public class profileTab extends Fragment {
EditText e1,e2,e3,e4;
Button b1;

    public profileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile_tab, container, false);
        e1=view.findViewById(R.id.editText);
        e2=view.findViewById(R.id.editText2);
        e3=view.findViewById(R.id.editText3);
        e4=view.findViewById(R.id.editText4);
        b1=view.findViewById(R.id.button5);

        final ParseUser user=ParseUser.getCurrentUser();

        if(user.get("Profilename")==null)
        {
            e1.setText("");
        }
        else {
            e1.setText(user.get("Profilename").toString() + "");
        }
        if(user.get("ProfileBio")==null)
        {
            e2.setText("");
        }
        else {
            e2.setText(user.get("ProfileBio").toString() + "");
        }
        if(user.get("ProfileProfession")==null)
        {
            e3.setText("");
        }
        else {
            e3.setText(user.get("ProfileProfession").toString() + "");
        }
        if(user.get("ProfileFavSport")==null)
        {
            e4.setText("");
        }
        else {
            e4.setText(user.get("ProfileFavSport").toString() + "");
        }
                 b1.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     user.put("ProfileName",e1.getText().toString());
                     user.put("ProfileBio",e2.getText().toString());
                     user.put("ProfileProfession",e3.getText().toString());
                     user.put("ProfileFavSport",e4.getText().toString());
                     final ProgressDialog po = new ProgressDialog(getContext());
                     po.setMessage("Updating..");
                     po.show();
user.saveInBackground(new SaveCallback() {
    @Override
    public void done(ParseException e) {
        if(e==null)
        {
            po.dismiss();
            Toast.makeText(getContext(),"Info Updated",Toast.LENGTH_SHORT).show();
        }
        else
        {
            po.dismiss();
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
});
                 }
             });
 return view;
    }

}
