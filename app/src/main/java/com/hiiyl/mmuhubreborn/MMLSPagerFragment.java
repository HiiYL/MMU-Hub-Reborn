package com.hiiyl.mmuhubreborn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

public class MMLSPagerFragment extends Fragment {
    FragmentPagerAdapter adapterViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_mmlspager, container, false);
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.pager);
        /** Important: Must use the child FragmentManager or you will see side effects. */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        return root;
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        Firebase myFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com/");
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                   return MMLSFragment.newInstance("67:1459119551", "WOW");
                case 1:
                    return MMLSFragment.newInstance("260:1459119520", "WOW");
                case 2:
                    return MMLSFragment.newInstance("446:1459126723", "WOW");
//                case 3:
//                    return MMLSFragment.newInstance("https://mmu-hub.firebaseio.com/subjects2/260:1459119520/weeks/0/announcements", "WOW");
            }
            return MMLSFragment.newInstance("67:1459119551", "WOW");
        }

        @Override
        public int getCount() {
            return UserSingleton.getInstance().getUser().getSubjects().length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UserSingleton.getInstance().getUser().getSubjects()[position].getName();
        }

    }
}
