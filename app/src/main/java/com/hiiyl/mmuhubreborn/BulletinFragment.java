package com.hiiyl.mmuhubreborn;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hiiyl.mmuhubreborn.Models.BulletinPost;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

public class BulletinFragment extends Fragment {
    PrettyTime p = new PrettyTime();

    private FirebaseRecyclerAdapter<BulletinPost, BulletinPostViewHolder> mAdapter;

    private OnFragmentInteractionListener mListener;
    private DatabaseReference mRef;

    public BulletinFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bulletin, container, false);
        RecyclerView recycler = (RecyclerView) rootView.findViewById(R.id.bulletin_post_recyclerview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        mRef = FirebaseDatabase.getInstance().getReference().child("bulletin_posts");

        mAdapter = new FirebaseRecyclerAdapter<BulletinPost, BulletinPostViewHolder>(BulletinPost.class, R.layout.item_bulletin, BulletinPostViewHolder.class, mRef) {

            @Override
            protected void populateViewHolder(BulletinPostViewHolder bulletinPostViewHolder, final BulletinPost bulletinPost, final int position) {
                bulletinPostViewHolder.titleView.setText(bulletinPost.getTitle());
                bulletinPostViewHolder.authorView.setText(bulletinPost.getAuthor());

                bulletinPostViewHolder.dateView.setText(p.format(new Date(bulletinPost.getDatePosted() * 1000)));
                bulletinPostViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.w("WOW", "You clicked on " + position);
                        // Create a new Fragment to be placed in the activity layout
                        BulletinViewFragment bulletinViewFragment = BulletinViewFragment.newInstance(bulletinPost);

                        // In case this activity was started with special instructions from an
                        // Intent, pass the Intent's extras to the fragment as arguments
//                        firstFragment.setArguments(getIntent().getExtras());
                        showEditDialog(bulletinPost);

                        // Add the fragment to the 'fragment_container' FrameLayout
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                                .add(R.id.fragment_container, bulletinViewFragment)
//                                .addToBackStack("tag").commit();
                    }
                });
            }
        };
        recycler.setAdapter(mAdapter);

        return rootView;
    }
    private void showEditDialog(BulletinPost bulletinPost) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        BulletinViewFragment bulletinViewFragment = BulletinViewFragment.newInstance(bulletinPost);
        bulletinViewFragment.show(fm, "fragment_edit_name");
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public static class BulletinPostViewHolder  extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView authorView;
        TextView dateView;
        public BulletinPostViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.title);
            authorView = (TextView) itemView.findViewById(R.id.author);
            dateView = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
