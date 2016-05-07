package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.hiiyl.mmuhubreborn.Models.Announcement;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MMLSFragment extends Fragment {
    private Firebase weekFirebaseRef;
    private String mSubjectRef = "";
    private String mTitle = "";
    PrettyTime p = new PrettyTime();
    private static final String ARG_PARAM1 = "bulletin_uid";
    private static final String ARG_PARAM2 = "bulletin_uid2";
    private FirebaseRecyclerAdapter<Announcement, WeekViewHolder> mAdapter;
    private Query subjectQuery;


    public MMLSFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getArguments() != null) {
            mSubjectRef = getArguments().getString(ARG_PARAM1);
            mTitle = getArguments().getString(ARG_PARAM2);
            Log.d("SUP", "SUP DAWG" + mSubjectRef);
            weekFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com").child("subjects3").child(mSubjectRef).child("announcements");
            subjectQuery = weekFirebaseRef.orderByPriority();
//            "https://mmu-hub.firebaseio.com/subjects2/260:1459119520/weeks/0/announcements"
//            weekFirebaseRef = UserSingleton.getInstance().getmFirebaseRef().child("subjects2")
//                    .child(mSubjectRef).child("weeks").limitToLast(1).getRef().child("announcements");
        }else {
            Log.d("SUP", "NOPE DAWG");
            weekFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com/subjects2/260:1459119520/weeks/0/announcements");
        }
        View rootView =  inflater.inflate(R.layout.fragment_mmls, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.mmls_recyclerview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM

        mAdapter = new FirebaseRecyclerAdapter<Announcement, WeekViewHolder>(Announcement.class, R.layout.item_bulletin, WeekViewHolder.class, subjectQuery) {
            @Override
            protected void populateViewHolder(WeekViewHolder weekViewHolder, final Announcement announcement, final int position) {
                weekViewHolder.titleView.setText(announcement.getTitle());
                weekViewHolder.authorView.setText(announcement.getAuthor());
                weekViewHolder.dateView.setText(p.format(new Date(announcement.getPosted_date() * 1000)));
//                weekViewHolder.authorView.setText(bulletinPost.getAuthor());

//                weekViewHolder.dateView.setText(p.format(new Date(bulletinPost.getDatePosted() * 1000)));
                weekViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w("WOW", "You clicked on " + position);
                        // Create a new Fragment to be placed in the activity layout
                        MMLSViewFragment mmlsViewFragment = MMLSViewFragment.newInstance(announcement);

                        // In case this activity was started with special instructions from an
                        // Intent, pass the Intent's extras to the fragment as arguments
//                        firstFragment.setArguments(getIntent().getExtras());

                        // Add the fragment to the 'fragment_container' FrameLayout
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                .add(R.id.fragment_container, mmlsViewFragment)
                                .addToBackStack("tag").commit();
//                        mAdapter.getRef(position).removeValue();
                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);
        return rootView;
    }

    public void onDownloadBtnClicked() {
        Log.d("PRESSED ", "PRESSED MOTHERUFCKER");
        DownloadFragment downloadFragment = DownloadFragment.newInstance(mSubjectRef);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.fragment_container, downloadFragment)
                .addToBackStack("tag").commit();
    }

    public static class WeekViewHolder  extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView authorView;
        TextView dateView;
        public WeekViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.title);
            authorView = (TextView) itemView.findViewById(R.id.author);
            dateView = (TextView) itemView.findViewById(R.id.date);
        }
    }
    public static MMLSFragment newInstance(String mSubjectRef, String mTitle) {
        Log.d("MMLSFRAGMENT", "NEWINSTANCE");
        MMLSFragment fragment = new MMLSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mSubjectRef);
        args.putString(ARG_PARAM2, mTitle);
        fragment.setArguments(args);
        return fragment;
    }

}
