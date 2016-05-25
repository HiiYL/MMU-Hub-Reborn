package com.hiiyl.mmuhubreborn;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.hiiyl.mmuhubreborn.Models.User;
import com.hiiyl.mmuhubreborn.Utils.CircleTransform;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BulletinFragment.OnFragmentInteractionListener {


    private static final int RC_SIGN_IN = 97;
    public Firebase myFirebaseRef;
    public CoordinatorLayout myView;
    private DrawerLayout drawer;
    private String studentID;
    private String mmlsPassword;

    public User mUser;
    private TextView studentNametxtView;
    private TextView facultyTxtView;
    private ImageView profileImageView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        myFirebaseRef = new Firebase("https://mmu-hub-14826.firebaseio.com/");
//        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
        setContentView(R.layout.activity_main);
        myView = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                profileImageView = (ImageView) drawer.findViewById(R.id.profileImage);
                if(profileImageView != null ) {
                    Picasso.with(MainActivity.this).load(auth.getCurrentUser().getPhotoUrl())
                            .transform(new CircleTransform()).into(profileImageView);
                }
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if ( currentFragment instanceof MMLSPagerFragment ) {
                    ((MMLSPagerFragment)currentFragment).onDownloadBtnClicked();
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

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
//            studentNametxtView = (TextView) drawer.findViewById(R.id.student_name);
//            studentNametxtView.setText(mUser.getDisplayName());
//            facultyTxtView = (TextView) drawer.findViewById(R.id.student_faculty);
//            facultyTxtView.setText(mUser.getFaculty());

            Snackbar.make(myView, "Logged In As " + auth.getCurrentUser().getDisplayName(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setProviders(
                                    AuthUI.GOOGLE_PROVIDER)
                            .build(),
                    RC_SIGN_IN);
            // not signed in
        }




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
            if (mUser == null || mUser.getId() == null) {

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
                                    AuthUI.EMAIL_PROVIDER,
                                    AuthUI.GOOGLE_PROVIDER,
                                    AuthUI.FACEBOOK_PROVIDER)
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


//    @Override
//    protected Firebase getFirebaseRef() {
//        return myFirebaseRef;
//    }
//
//    @Override
//    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
//
//    }
//
//    @Override
//    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
//
//    }
//    @Override
//    public void onFirebaseLoggedIn(final AuthData authData) {
//        Log.d("WOW", "WOW LLOGGEDD IN");
//        Snackbar.make(myView, "Logged In As " + authData.getProviderData().get("displayName").toString(), Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();
//        final Map<String, Object> map = new HashMap<String, Object>();
//        map.put("provider", authData.getProvider());
//        if(authData.getProviderData().containsKey("displayName")) {
//            map.put("displayName", authData.getProviderData().get("displayName").toString());
//        }
//        myFirebaseRef.child("users").child(authData.getUid()).updateChildren(map);
//
//        myFirebaseRef.child("users").child(authData.getUid()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                mUser = dataSnapshot.getValue(User.class);
//                if (mUser == null || mUser.getId() == null) {
//                    LoginFragment loginFragment = LoginFragment.newInstance(authData.getUid());
//                    getSupportFragmentManager().beginTransaction()
//                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
//                            .replace(R.id.fragment_container, loginFragment).commit();
//                }else {
//                    studentNametxtView = (TextView) drawer.findViewById(R.id.student_name);
//                    studentNametxtView.setText(mUser.getDisplayName());
//                    facultyTxtView = (TextView) drawer.findViewById(R.id.student_faculty);
//                    facultyTxtView.setText(mUser.getFaculty());
//                    profileImageView = (ImageView) drawer.findViewById(R.id.profileImage);
//                    Picasso.with(MainActivity.this).load((String)authData.getProviderData().get("profileImageURL"))
//                            .resize(profileImageView.getWidth(), 0)
//                            .transform(new CircleTransform()).into(profileImageView);
//
//
//                    UserSingleton.getInstance().setUser(mUser);
//                }
////                Log.d("WOW", mUser.getSubjects()[0].getName());
//            }
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
//
//
//    }
    @Override
    protected void onStart() {
        super.onStart();
        // All providers are optional! Remove any you don't want.
//        setEnabledAuthProvider(AuthProviderType.GOOGLE);
//        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }
}
