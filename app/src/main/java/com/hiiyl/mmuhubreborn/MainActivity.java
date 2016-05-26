package com.hiiyl.mmuhubreborn;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hiiyl.mmuhubreborn.Models.User;
import com.hiiyl.mmuhubreborn.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BulletinFragment.OnFragmentInteractionListener {


    private static final int RC_SIGN_IN = 97;
    public CoordinatorLayout myView;
    private DrawerLayout drawer;
    private String studentID;
    private String mmlsPassword;

    private TextView studentNametxtView;
    private TextView facultyTxtView;
    private ImageView profileImageView;
    private FirebaseAuth mAuth;
    private DatabaseReference myFirebaseRef;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String TAG = "TAG YOU're IT";
    private User mUser;
    public ActionBar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        myFirebaseRef = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
        myView = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mToolbar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        getSupportActionBar().setTitle("Bulletin");

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            BulletinFragment firstFragment = new BulletinFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof MMLSPagerFragment) {
                    ((MMLSPagerFragment) currentFragment).onDownloadBtnClicked();

                }

            }
        });


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        profileImageView = (ImageView) headerView.findViewById(R.id.profileImage);
        studentNametxtView = (TextView) headerView.findViewById(R.id.student_name);
        facultyTxtView = (TextView) headerView.findViewById(R.id.student_faculty);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    Uri photoUrl = user.getPhotoUrl();
                    Log.e("WOW", String.valueOf(photoUrl));
                    studentNametxtView.setText(name);
                    facultyTxtView.setText(email);
                    Picasso.with(MainActivity.this).load(photoUrl)
                            .transform(new CircleTransform()).into(profileImageView);
                    myFirebaseRef.child("users").child(user.getUid()).addListenerForSingleValueEvent(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if(dataSnapshot)
                                    mUser = dataSnapshot.getValue(User.class);
                                    UserSingleton.getInstance().setUser(mUser);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                                }
                            });
//                    myFirebaseRef.child("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            mUser = dataSnapshot.getValue(User.class);
//                            if (mUser == null || mUser.getId() == null) {
//                                LoginFragment loginFragment = LoginFragment.newInstance(user.getUid());
//                                getSupportFragmentManager().beginTransaction()
//                                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                                        .replace(R.id.fragment_container, loginFragment).commit();
//                            } else {
//                                studentNametxtView = (TextView) drawer.findViewById(R.id.student_name);
//                                studentNametxtView.setText(mUser.getDisplayName());
//                                facultyTxtView = (TextView) drawer.findViewById(R.id.student_faculty);
//                                facultyTxtView.setText(mUser.getFaculty());
//                                profileImageView = (ImageView) drawer.findViewById(R.id.profileImage);
//                                UserSingleton.getInstance().setUser(mUser);
//                            }
//
//                            // User is signed in
//                            Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
                } else {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setProviders(
                                            AuthUI.GOOGLE_PROVIDER)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
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
            // Create a new Fragment to be placed in the activity layout
            BulletinFragment firstFragment = new BulletinFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, firstFragment).commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            if (mUser == null) {
                myFirebaseRef.child("users").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mUser = dataSnapshot.getValue(User.class);
                        if (mUser == null) {
                            Log.e("UID IS", mAuth.getCurrentUser().getUid());
                            LoginFragment loginFragment = LoginFragment.newInstance(mAuth.getCurrentUser().getUid());
                            getSupportFragmentManager().beginTransaction()
                                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                                    .replace(R.id.fragment_container, loginFragment).commit();
                        } else {
                            studentNametxtView = (TextView) drawer.findViewById(R.id.student_name);
                            studentNametxtView.setText(mUser.getDisplayName());
                            facultyTxtView = (TextView) drawer.findViewById(R.id.student_faculty);
                            facultyTxtView.setText(mUser.getFaculty());
                            profileImageView = (ImageView) drawer.findViewById(R.id.profileImage);
                            Picasso.with(MainActivity.this).load(mAuth.getCurrentUser().getPhotoUrl())
                                    .resize(profileImageView.getWidth(), 0)
                                    .transform(new CircleTransform()).into(profileImageView);


                            UserSingleton.getInstance().setUser(mUser);
                        }
    //                Log.d("WOW", mUser.getSubjects()[0].getName());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }else {
                // Create a new Fragment to be placed in the activity layout
                MMLSPagerFragment mmlsFragment = new MMLSPagerFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, mmlsFragment, "MMLSPagerFragment").commit();
            }

        } else if (id == R.id.nav_slideshow) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    AuthUI.GOOGLE_PROVIDER)
                            .build(),
                    RC_SIGN_IN);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
