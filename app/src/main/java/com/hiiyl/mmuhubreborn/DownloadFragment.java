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
import com.hiiyl.mmuhubreborn.Models.SubjectFile;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DownloadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DownloadFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static Firebase subjectFilesFirebaseRef;

    PrettyTime p = new PrettyTime();

    // TODO: Rename and change types of parameters
    private String mSubjectRef;
    private String mParam2;
    private FirebaseRecyclerAdapter<SubjectFile, SubjectFilesViewHolder> mAdapter;
//    private FirebaseRecyclerAdapter<Announcement, WeekViewHolder> mAdapter;


    public DownloadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DownloadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DownloadFragment newInstance(String param1) {
        DownloadFragment fragment = new DownloadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSubjectRef = getArguments().getString(ARG_PARAM1);
            subjectFilesFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com").child("subjects3").child(mSubjectRef).child("subject_files");
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_download, container, false);
        RecyclerView recycler = (RecyclerView) root.findViewById(R.id.mmls_recyclerview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new FirebaseRecyclerAdapter<SubjectFile, SubjectFilesViewHolder>(SubjectFile.class, R.layout.item_bulletin, SubjectFilesViewHolder.class, subjectFilesFirebaseRef) {
            @Override
            protected void populateViewHolder(SubjectFilesViewHolder subjectFilesViewHolder, final SubjectFile subjectFile, final int position) {
                subjectFilesViewHolder.titleView.setText(subjectFile.getTitle());
                subjectFilesViewHolder.authorView.setText(subjectFile.getAuthor());
                subjectFilesViewHolder.dateView.setText(p.format(new Date(subjectFile.getPosted_date() * 1000)));
//                weekViewHolder.authorView.setText(bulletinPost.getAuthor());

//                weekViewHolder.dateView.setText(p.format(new Date(bulletinPost.getDatePosted() * 1000)));
                subjectFilesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w("WOW", "You clicked on " + position);
                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);
        return root;
    }
    public static class SubjectFilesViewHolder  extends RecyclerView.ViewHolder{
        TextView filenameView;
        TextView authorView;
        TextView dateView;
        TextView titleView;
        public SubjectFilesViewHolder(View itemView) {
            super(itemView);
//            filenameView = (TextView)itemView.findViewById(R.id.title);
            titleView = (TextView)itemView.findViewById(R.id.title);
            authorView = (TextView) itemView.findViewById(R.id.author);
            dateView = (TextView) itemView.findViewById(R.id.date);
        }
    }

}
