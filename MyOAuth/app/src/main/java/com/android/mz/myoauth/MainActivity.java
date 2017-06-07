package com.android.mz.myoauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * OAuth authentication with Google and Facebook API.
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private GoogleAuth googleAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.my_text);
        final View view = this.findViewById(android.R.id.content);

        googleAuth = new GoogleAuth(this);
        googleAuth.setButtonListener(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAG", "on result");

        if (requestCode == GoogleAuth.SIGN_IN_SUCCESS) {
            googleAuth.onResult(requestCode, resultCode, data);
            String s = googleAuth.getUserName() + "\n" + googleAuth.getUserEmail();
            mTextView.setText(s);
            Log.d("TAG", "sign in google successful");
            Log.d("TAG", s);
        }
    }
}
