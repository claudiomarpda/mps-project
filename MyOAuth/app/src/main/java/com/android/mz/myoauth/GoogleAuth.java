package com.android.mz.myoauth;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created on 06/06/17.
 * <p>
 * Authentication with Google APi.
 */

public final class GoogleAuth implements SocialAuth {

    public static final int SIGN_IN_SUCCESS = 99;
    private GoogleApiClient mGoogleApiClient;
    private Activity activity;
    private GoogleSignInResult result;
    private String name;
    private String email;

    public GoogleAuth(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void setButtonListener(View view) {
        SignInButton mGoogleSignInButton = (SignInButton) view.findViewById(R.id.google_sign_in_button);
        mGoogleSignInButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle(activity);
            }
        });
    }

    private void signInWithGoogle(Activity activity) {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        final Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, SIGN_IN_SUCCESS);
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            final GoogleApiClient client = mGoogleApiClient;

            if (result.getSignInAccount() != null) {
                if (result.getSignInAccount().getDisplayName() != null) {
                    name = result.getSignInAccount().getDisplayName();
                }
                if (result.getSignInAccount().getEmail() != null) {
                    email = result.getSignInAccount().getEmail();
                }
            }

            Log.d("TAG", "result: " + name);
        } else {
            Log.d("TAG", "result fail");
        }
    }

    @Override
    public String getUserName() {
        if(name != null) {
            return name;
        }
        else {
            return "FailName";
        }
    }

    @Override
    public String getUserEmail() {
        if (email != null) {
            return email;
        }
        return "FailMail";
    }
}
