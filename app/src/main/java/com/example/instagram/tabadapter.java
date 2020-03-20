package com.example.instagram;

import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tabadapter extends FragmentPagerAdapter {
    public tabadapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int tabposition) {

        switch (tabposition)
        {
            case 0:
                profileTab profile=new profileTab();
                return  profile;
            case 1:
                return new UserTab();
            case 2:
                return new SharePictureTab();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       switch(position)
       {
           case 0: return "Profile";
           case 1: return "User Tab";
           case 2:return "Share Picture";
           default: return null;
       }
    }
}
