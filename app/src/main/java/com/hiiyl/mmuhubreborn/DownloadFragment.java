package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.hiiyl.mmuhubreborn.Models.SubjectFile;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.File;
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
    private Query subjectFilesQuery;
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
            subjectFilesQuery = subjectFilesFirebaseRef.orderByChild("priority");
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
        mAdapter = new FirebaseRecyclerAdapter<SubjectFile, SubjectFilesViewHolder>(SubjectFile.class, R.layout.item_file_download, SubjectFilesViewHolder.class, subjectFilesQuery) {
            @Override
            protected void populateViewHolder(final SubjectFilesViewHolder subjectFilesViewHolder, final SubjectFile subjectFile, final int position) {
                subjectFilesViewHolder.titleView.setText(subjectFile.getTitle());
                subjectFilesViewHolder.authorView.setText(subjectFile.getAuthor());
                subjectFilesViewHolder.dateView.setText(p.format(new Date(subjectFile.getPosted_date() * 1000)));
                subjectFilesViewHolder.descriptionView.setText(Html.fromHtml(subjectFile.getDescription()));

                Picasso.with(getActivity()).load(subjectFile.getFileIcon()).into(subjectFilesViewHolder.iconImageView);
//                weekViewHolder.authorView.setText(bulletinPost.getAuthor());

//                weekViewHolder.dateView.setText(p.format(new Date(bulletinPost.getDatePosted() * 1000)));
                subjectFilesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w("WOW", "You clicked on " + position);
                        Ion.with(getActivity())
                                .load("https://mmls.mmu.edu.my/form-download-content")

                                .setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                .setHeader("Origin", "https://mmls.mmu.edu.my")
                                .setHeader("Upgrade-Insecure-Requests", "bar")
                                .setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36")
                                .setHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryfomf9w6BTATWJ6eO")
                                .setHeader("Referer", "https://mmls.mmu.edu.my/67:1459119551")
                                .setHeader("Accept-Encoding", "gzip, deflate")
                                .setHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6")
                                .setHeader("Cookie", "laravel_session=eyJpdiI6IlFKWitOVXJ4am9uMzVjTVl4clwvbkNnPT0i" +
                                        "LCJ2YWx1ZSI6IlR4aUdaR2dcL1paQ3VkRVdrTFZjdk90UWdROXpNY0l6TEVwa200N0oxYzlBcXdZK2h1aldmMnJ6cVM0dzNPeDJMb2ltNHlsTEFGd2VjME1nRHZEXC8wZGc9PSIsIm1hYyI6IjhlNDBhNzE3MjA0ODU2Mjk3MmZkYjg5ZWJmZGY0ZjU0YTE4OGMyOWM3OGZiN2Q4Yzg2ZDc4MDU0NGVhN2E2ZWQifQ%3D%3D")
                                .setBodyParameter("_token", "kWjAig2mWpw4R18MNWHFNBfe9njumNMApGUASxJr")
                                .setBodyParameter("btnsubmit", "76175_MOCK MEETING SCHEDULE_TRI3_1516.xls")
                                .setBodyParameter("content_id", "76175")
                                .setBodyParameter("file_name", "76175_MOCK MEETING SCHEDULE_TRI3_1516.xls")
                                .setBodyParameter("file_path", "CYBER/PWC1010/announcement")
                                .progressBar(subjectFilesViewHolder.progressBar)
                                // can also use a custom callback
                                .progress(new ProgressCallback() {
                                    @Override
                                    public void onProgress(long downloaded, long total) {

                                    }
                                })
                                .write(new File("/sdcard/really-big-file.zip"))
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {
                                        // download done...
                                        // do stuff with the File or error
                                    }
                                });
                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);
        return root;
    }
    public static class SubjectFilesViewHolder  extends RecyclerView.ViewHolder{
        TextView filenameView;
        TextView descriptionView;
        TextView authorView;
        TextView dateView;
        TextView titleView;
        ImageView iconImageView;
        ProgressBar progressBar;
        public SubjectFilesViewHolder(View itemView) {
            super(itemView);
//            filenameView = (TextView)itemView.findViewById(R.id.title);
            descriptionView = (TextView) itemView.findViewById(R.id.description);
            titleView = (TextView)itemView.findViewById(R.id.title);
            authorView = (TextView) itemView.findViewById(R.id.author);
            dateView = (TextView) itemView.findViewById(R.id.date);
            iconImageView = (ImageView) itemView.findViewById(R.id.icon_view);
            progressBar = (ProgressBar) itemView.findViewById(R.id.download_progressbar);
        }
    }

}
