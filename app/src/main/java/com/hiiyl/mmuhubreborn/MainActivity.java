package com.hiiyl.mmuhubreborn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.hiiyl.mmuhubreborn.Models.BulletinPost;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseRecyclerAdapter<BulletinPost, BulletinPostViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

        Firebase myFirebaseRef = new Firebase("https://mmu-hub.firebaseio.com/");
//        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//        Date d;
//        try {
//            d = sdf.parse("21/12/2012");
//            BulletinPost bulletinPost = new BulletinPost("Bulletin", "Bruh...", "Dude", d);
//            myFirebaseRef.child("bulletin_posts").push().setValue(bulletinPost);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        ListView messagesView = (ListView) findViewById(R.id.bulletin_list);
//
//        mAdapter = new FirebaseListAdapter<BulletinPost>(this, BulletinPost.class, android.R.layout.two_line_list_item, myFirebaseRef) {
//            @Override
//            protected void populateView(View view, BulletinPost bulletinPost, int position) {
//                ((TextView)view.findViewById(android.R.id.text1)).setText(bulletinPost.getTitle());
//                ((TextView)view.findViewById(android.R.id.text2)).setText(bulletinPost.getContents());
//            }
//        };
//        messagesView.setAdapter(mAdapter);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.bulletin_post_recyclerview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        mAdapter = new FirebaseRecyclerAdapter<BulletinPost, BulletinPostViewHolder>(BulletinPost.class, R.layout.item_bulletin, BulletinPostViewHolder.class, myFirebaseRef.child("bulletin_posts")) {
            @Override
            protected void populateViewHolder(BulletinPostViewHolder bulletinPostViewHolder, BulletinPost bulletinPost, int i) {
                bulletinPostViewHolder.titleView.setText(bulletinPost.getTitle());
                bulletinPostViewHolder.authorView.setText(bulletinPost.getAuthor());

                bulletinPostViewHolder.dateView.setText(sdf.format(new Date(bulletinPost.getDatePosted() * 1000)));
            }
        };
        recycler.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
    public static class BulletinPostViewHolder  extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView dateView;
        TextView contentView;
        public BulletinPostViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView)itemView.findViewById(R.id.title);
            authorView = (TextView) itemView.findViewById(R.id.author);
            dateView = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
