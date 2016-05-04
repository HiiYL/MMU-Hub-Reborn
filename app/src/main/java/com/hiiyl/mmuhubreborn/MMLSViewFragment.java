package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hiiyl.mmuhubreborn.Models.Announcement;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MMLSViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MMLSViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Announcement mAnnouncement;
    private WebView webview;


    public MMLSViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MMLSViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MMLSViewFragment newInstance(Announcement param1) {
        MMLSViewFragment fragment = new MMLSViewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAnnouncement = (Announcement) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_mmlsview, container, false);
        webview = (WebView) root.findViewById(R.id.contents_webview);
        webview.loadData(mAnnouncement.getContents(), "text/html; charset=UTF-8", null);
        return root;
    }

}
