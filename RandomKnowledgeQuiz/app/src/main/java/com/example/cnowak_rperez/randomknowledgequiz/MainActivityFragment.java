package com.example.cnowak_rperez.randomknowledgequiz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

//    private TextView mTextDetails;
    private CallbackManager mCallbackManager;

    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            System.out.println("FACEBOOK LOGIN SUCCESSFUL THO");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
//                mTextDetails.setText("Welcome " + profile.getName());
                Toast.makeText(getActivity(), "Logged into Facebook as: " + profile.getName(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel() {
            System.out.println("FACEBOOK LOGIN CANCELLED THO");
            Toast.makeText(getActivity(), "Facebook login cancelled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException e) {
            System.out.println("FACEBOOK LOGIN ERROR THO: " + e.getMessage());
            Toast.makeText(getActivity(), "Facebook login failed", Toast.LENGTH_SHORT).show();
        }
    };
    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken old, AccessToken newToken) {
                System.out.println("FACEBOOK ACCESS TOKEN CHANGED THO");
            }
        };
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
//                mTextDetails.setText("Welcome " + newProfile.getName());
                System.out.println("FACEBOOK PROFILE CHANGED THO");
            }
        };
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton)view.findViewById(R.id.login_button);
//        this.mTextDetails = (TextView)view.findViewById(R.id.textDetails);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);
    }

    @Override
    public void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("FACEBOOK REQUEST CODE IS: " + Integer.toString(requestCode));
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
