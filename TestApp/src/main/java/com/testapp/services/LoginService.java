package com.testapp.services;

import android.os.Handler.Callback;
import android.os.Message;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EBean;
import com.testapp.util.TACallback;

/**
 * Created by kkirch on 12/26/13.
 */
@EBean
public class LoginService {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello",
            "bar@example.com:world"
    };

    @Background
    public void attemptLogin(String username, String password, TACallback<Boolean> callback) {
        boolean authenticated = false;
        for (String cred : DUMMY_CREDENTIALS) {
            String[] parts = cred.split(":");
            if (username.equals(parts[0])
                    && password.equals(parts[1])) {
                authenticated = true;
                break;
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        authenticated = true;
        callback.onCallback(authenticated);
    }

}
