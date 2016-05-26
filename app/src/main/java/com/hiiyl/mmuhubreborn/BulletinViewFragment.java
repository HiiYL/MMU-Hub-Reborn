package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hiiyl.mmuhubreborn.Models.BulletinPost;


/**
 * A simple {@link Fragment} subclass.
 */
public class BulletinViewFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "bulletin_uid";

    // TODO: Rename and change types of parameters
    private BulletinPost mBulletinPost;

    WebView webview;


    public BulletinViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBulletinPost = (BulletinPost) getArguments().getSerializable(ARG_PARAM1);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bulletin_view, container, false);
        webview = (WebView) rootView.findViewById(R.id.contents_webview);
        webview.loadData(mBulletinPost.getContents(), "text/html; charset=UTF-8", null);

        return rootView;
    }
    public static BulletinViewFragment newInstance(BulletinPost bulletinPost) {
        BulletinViewFragment fragment = new BulletinViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, bulletinPost);
        fragment.setArguments(args);
        return fragment;
    }

}
