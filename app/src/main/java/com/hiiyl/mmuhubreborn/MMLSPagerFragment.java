package com.hiiyl.mmuhubreborn;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MMLSPagerFragment extends Fragment {
    MyAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_mmlspager, container, false);
        viewPager = (ViewPager) root.findViewById(R.id.pager);
        /** Important: Must use the child FragmentManager or you will see side effects. */
        adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);


        if (mListener != null) {
            mListener.onFragmentInteraction("Custom Title");
        }


        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
//        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        SparseArray tagMap = new SparseArray();
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String uri = UserSingleton.getInstance().getUser().getSubjects().get(position).getUri();
            return MMLSFragment.newInstance(uri, "WOW");
        }

        @Override
        public int getCount() {
            return UserSingleton.getInstance().getUser().getSubjects().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return UserSingleton.getInstance().getUser().getSubjects().get(position).getName().split("\\(")[0];
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
            // get the tags set by FragmentPagerAdapter
            if (createdFragment != null) {
                Log.d("POSITION ADDED", String.valueOf(position));
                tagMap.put(position, createdFragment.getTag());
            }
            // ... save the tags somewhere so you can reference them later
            return createdFragment;
        }
        public SparseArray getTagMap() {
            return tagMap;
        }

    }
    public void onDownloadBtnClicked() {
        Log.d("PRESSED ", "PRESSED ");
        Log.d("POSITION CALLED", String.valueOf(viewPager.getCurrentItem()));
        Log.d("FRAGMENT ADDRESS", (String) adapter.getTagMap().get(viewPager.getCurrentItem()));
        String tag = (String) adapter.getTagMap().get(viewPager.getCurrentItem());
        Fragment fragment = getChildFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            ((MMLSFragment) fragment).onDownloadBtnClicked();
        }else {
            Log.e("FRAGMENT", "FRAGMENT IS NULL BOYZ");
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tabLayout.removeAllTabs();
    }
    private String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String title);
    }
}
