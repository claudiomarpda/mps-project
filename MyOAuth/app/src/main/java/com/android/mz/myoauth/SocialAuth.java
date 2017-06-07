package com.android.mz.myoauth;

import android.content.Intent;
import android.view.View;

/**
 * Created on 06/06/17.
 * <p>
 * Provides steps for authentication and data to be retrieved from social APIs.
 */

public interface SocialAuth {

    void setButtonListener(View view);

    void onResult(int requestCode, int resultCode, Intent data);

    String getUserName();

    String getUserEmail();
}
