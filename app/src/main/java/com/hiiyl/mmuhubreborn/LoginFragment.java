package com.hiiyl.mmuhubreborn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.hiiyl.mmuhubreborn.Models.User;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String ARG_PARAM1 = "bulletin_uid";
    String authUid;
    private Button loginBtn;
    private EditText studentIdEditText;
    private EditText MMLSpasswordEditText;


    public LoginFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            authUid =  getArguments().getString(ARG_PARAM1);
            Log.d("GETAUTH", authUid);
        }
        // Inflate the layout for this fragment
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        View root =  inflater.inflate(R.layout.fragment_login, container, false);
        loginBtn = (Button) root.findViewById(R.id.btn_login);
        studentIdEditText = (EditText) root.findViewById(R.id.student_id);
        MMLSpasswordEditText = (EditText) root.findViewById(R.id.mmls_password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtn.setClickable(false);
                loginBtn.setText("Logging In");
                StringRequest sr = new StringRequest(Request.Method.POST,"https://mmu-api.herokuapp.com/login_mmls", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginBtn.setText("DONE!");
                        Toast.makeText(getActivity(),
                                "COMPLETE BOYZ",
                                Toast.LENGTH_LONG).show();

                        // In case this activity was started with special instructions from an
                        // Intent, pass the Intent's extras to the fragment as arguments

                        // Add the fragment to the 'fragment_container' FrameLayout
                        UserSingleton.getInstance().getmFirebaseDatabase().child("users").child(authUid)
                                .addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(DataSnapshot dataSnapshot) {
                                  UserSingleton.getInstance().setUser(dataSnapshot.getValue(User.class));
                                  MMLSPagerFragment mmlsFragment = new MMLSPagerFragment();
                                  getActivity().getSupportFragmentManager().beginTransaction()
                                          .replace(R.id.fragment_container, mmlsFragment).commit();
                              }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                          });

//                        mPostCommentResponse.requestCompleted();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.e("ERROR CODE", String.valueOf(error.networkResponse.statusCode));
                        if (error instanceof TimeoutError){
                            Toast.makeText(getActivity(),
                                    "TIMEOUT BOYZ",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(getActivity(),
                                    "NO CONNECTIOn BOYZ",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getActivity(),
                                    "AUTH FAIL BOYZ",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getActivity(),
                                    "SERVER ERROR BOYZ",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getActivity(),
                                    "NETWORK ERROR BOYZ",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getActivity(),
                                    "PARSE ERROR BOYZ",
                                    Toast.LENGTH_LONG).show();
                        }
                        loginBtn.setClickable(true);
                        loginBtn.setText("FAILED!");
//                        mPostCommentResponse.requestEndedWithError(error);
                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("student_id",studentIdEditText.getText().toString());
                        params.put("mmls_password",MMLSpasswordEditText.getText().toString());
                        params.put("google_auth_uid",authUid);
                        Log.d("SETAUTH", authUid);
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                sr.setRetryPolicy(new DefaultRetryPolicy(0,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(sr);

            }
        });
        return root;
    }

    public static LoginFragment newInstance(String authUid) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        Log.d("AUTH", authUid);
        args.putString(ARG_PARAM1, authUid);
        fragment.setArguments(args);
        return fragment;
    }

}
