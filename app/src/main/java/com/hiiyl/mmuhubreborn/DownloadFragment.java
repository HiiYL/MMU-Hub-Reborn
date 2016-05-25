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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hiiyl.mmuhubreborn.Helpers.FileOpen;
import com.hiiyl.mmuhubreborn.Models.Subject;
import com.hiiyl.mmuhubreborn.Models.SubjectFile;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.File;
import java.io.IOException;
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

    PrettyTime p = new PrettyTime();

    // TODO: Rename and change types of parameters
    private String mSubjectRef;
    private String mSubjectName;
    private String mParam2;
    private FirebaseRecyclerAdapter<SubjectFile, SubjectFilesViewHolder> mAdapter;
    private DatabaseReference subjectFilesFirebaseRef;
    private com.google.firebase.database.Query subjectFilesQuery;
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
            subjectFilesFirebaseRef = FirebaseDatabase.getInstance().getReference().child(mSubjectRef).child("subject_files");
            subjectFilesQuery = subjectFilesFirebaseRef.orderByChild("priority");

            boolean found = false;
            for (Subject subject : UserSingleton.getInstance().getUser().getSubjects()) {
                if (subject.getUri() == mSubjectRef ) {
                    mSubjectName = subject.getName();
                    found = true;
                    break;
                }
            }
            if(!found) {
                Log.e("DOWNLOADFRAG", "SUBJECT NAME NOT FOUND");
            }

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
        mAdapter = new FirebaseRecyclerAdapter<SubjectFile, SubjectFilesViewHolder>(SubjectFile.class, 5, SubjectFilesViewHolder.class, subjectFilesFirebaseRef) {
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
                        String file_name = "76175_MOCK MEETING SCHEDULE_TRI3_1516.xls";
                        String file_directory = getActivity().getExternalFilesDir(null) + "/" + mSubjectName + "/";
                        String file_path = file_directory + file_name;
                        Log.d("FILE NAME", file_name);
                        Log.d("FILE PATH", file_path);
                        File file = new File(file_path);
                        if(file.exists()) {
                            try {
                                FileOpen.openFile(getActivity(), file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            File temp = new File(file_directory);
                            Log.e("MKDIRS", String.valueOf(temp.mkdirs()));

                            Log.d("ION", "STARTING");
                            Ion.with(getContext())
                                    .load("https://mmls.mmu.edu.my/form-download-content")
                                    .addHeader("Cookie","laravel_session=eyJpdiI6InZLeWI3RERKQlpIWFVWUDZhXC9lZ09RPT0iLCJ2YWx1ZSI6IlhBYTVNbDlzQnJmMGxiM0drdDlzd1Y1NmZ5ZlJKa1wvR0N3Qk9ZVjdkcWlkSEhRZmZEUFR1NXNsQk5wTlhvSkIzcTZYa1ZiZkdudHlSMnBCVFhhd3N4UT09IiwibWFjIjoiNDUzMWRhZDBiYmZlZGQ5YzBiNjc1OTY5MzZlMWVhMWI5NmExN2RlZDUyYzllYTdmODY1MTcwY2QwMTZiOWMzMyJ9")
                                    .setBodyParameter("content_id", "76175")
                                    .setBodyParameter("file_name", "76175_MOCK MEETING SCHEDULE_TRI3_1516.xls")
                                    .setBodyParameter("file_path", "CYBER/PWC1010/announcement")
                                    .write(file)
                                    .setCallback(new FutureCallback<File>() {
                                        @Override
                                        public void onCompleted(Exception e, File result) {
                                            if ( e == null) {
                                                Toast.makeText(getActivity(), "DONE M8", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
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
