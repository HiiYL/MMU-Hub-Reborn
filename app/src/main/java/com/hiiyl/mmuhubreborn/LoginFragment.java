package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "bulletin_uid";
    Map<String, String> mUserDetails;


    public LoginFragment() {
        if (getArguments() != null) {
            mUserDetails =  (Map<String, String>) getArguments().getSerializable(ARG_PARAM1);
        }
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public static LoginFragment newInstance(Map<String, String> bulletinPost) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
//        args.putSerializable(ARG_PARAM1, bulletinPost);
        fragment.setArguments(args);
        return fragment;
    }

}
