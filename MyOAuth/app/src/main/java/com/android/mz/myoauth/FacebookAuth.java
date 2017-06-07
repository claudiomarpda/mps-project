package com.android.mz.myoauth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created on 06/06/17.
 * <p>
 * Authentication with Facebook API.
 */
public final class FacebookAuth implements SocialAuth {

    private CallbackManager callbackManager;
    private String email;
    private String name;

    public FacebookAuth(Context context) {
        FacebookSdk.sdkInitialize(context);
        callbackManager = CallbackManager.Factory.create();
    }


    @Override
    public void setButtonListener(View view) {
        final LoginButton signInButton = (LoginButton) view.findViewById(R.id.facebook_sign_in_button);
        signInButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        //TODO: Use the Profile class to get information about the current user.


                        signInButton.setReadPermissions(Arrays.asList(
                                "public_profile", "email", "user_birthday", "user_friends"));

                        callbackManager = CallbackManager.Factory.create();

                        signInButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                            @Override
                            public void onSuccess(LoginResult loginResult) {

                                String accessToken = loginResult.getAccessToken().getToken();
                                Log.i("accessToken", accessToken);

                                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.i("LoginActivity", response.toString());
                                        // Get facebook data from login
                                        Bundle bFacebookData = getFacebookData(object);
                                        email = bFacebookData.getString("email", "mail fail");
                                        name = bFacebookData.getString("first_name", "name fail");
                                        Log.d("TAG", email + "\n" + name);
                                    }
                                });
                                Bundle parameters = new Bundle();
                                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                                request.setParameters(parameters);
                                request.executeAsync();
                            }

                            @Override
                            public void onCancel() {
                                Log.d("TAG", "onCancel");
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                Log.d("TAG", "onError");
                                Log.v("LoginActivity", exception.getCause().toString());
                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG", "onCancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("TAG", "onError");
                    }
                });


    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = null;
        try {
            bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

        } catch (JSONException e) {
            Log.d("TAG", "Error parsing JSON");
        }
        return bundle;
    }

    @Override
    public void onResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public String getUserEmail() {
//        return "mock@facebook.com";
        return email;
    }
}