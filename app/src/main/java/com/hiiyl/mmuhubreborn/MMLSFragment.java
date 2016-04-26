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
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.hiiyl.mmuhubreborn.Models.Announcement;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class MMLSFragment extends Fragment {
    private final Firebase weekFirebaseRef;
    PrettyTime p = new PrettyTime();
    private FirebaseRecyclerAdapter<Announcement, WeekViewHolder> mAdapter;


    public MMLSFragment() {
        weekFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com/subjects/BFN1014/weeks/0/announcements");
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_mmls, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.mmls_recyclerview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        mAdapter = new FirebaseRecyclerAdapter<Announcement, WeekViewHolder>(Announcement.class, R.layout.item_bulletin, WeekViewHolder.class, weekFirebaseRef) {
            @Override
            protected void populateViewHolder(WeekViewHolder weekViewHolder, final Announcement week, final int position) {
                weekViewHolder.titleView.setText(week.getTitle());
                weekViewHolder.authorView.setText(week.getAuthor());
                weekViewHolder.dateView.setText(p.format(new Date(week.getPosted_date() * 1000)));
//                weekViewHolder.authorView.setText(bulletinPost.getAuthor());

//                weekViewHolder.dateView.setText(p.format(new Date(bulletinPost.getDatePosted() * 1000)));
                weekViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w("WOW", "You clicked on " + position);
//                        // Create a new Fragment to be placed in the activity layout
//                        BulletinViewFragment bulletinViewFragment = BulletinViewFragment.newInstance();
//
//                        // In case this activity was started with special instructions from an
//                        // Intent, pass the Intent's extras to the fragment as arguments
////                        firstFragment.setArguments(getIntent().getExtras());
//
//                        // Add the fragment to the 'fragment_container' FrameLayout
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                                .add(R.id.fragment_container, bulletinViewFragment)
//                                .addToBackStack("tag").commit();
//                        mRecycleViewAdapter.getRef(position).removeValue();
                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);
        return rootView;
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

}
