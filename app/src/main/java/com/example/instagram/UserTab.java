package com.example.instagram;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserTab extends Fragment {
ListView listView;
ArrayList arrayList;
ArrayAdapter arrayAdapter;
    public UserTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_user_tab, container, false);
        listView=view.findViewById(R.id.listview);
        arrayList=new ArrayList();
        arrayAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_2,arrayList);
        final TextView t=view.findViewById(R.id.textView3);
        ParseQuery<ParseUser> parseUserParseQuery=ParseUser.getQuery();
        parseUserParseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        parseUserParseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null)
                {
                    if(objects.size()>0)
                    {
                        for(ParseUser p:objects)
                        {

                            arrayList.add(p.getUsername());
                           t.animate().alpha(0).setDuration(2000);
                            listView.setAdapter(arrayAdapter);
                            listView.setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });

        return view;
    }

}
