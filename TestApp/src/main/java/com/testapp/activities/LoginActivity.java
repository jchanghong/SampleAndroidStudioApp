package com.testapp.activities;

import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.testapp.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @ViewById(R.id.email)
    EditText emailTextView;

    @ViewById(R.id.password)
    EditText passwordTextView;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello",
            "bar@example.com:world"
    };

    @Click(R.id.sign_in_button)
    public void tryLogin() {
        emailTextView.setError(null);
        passwordTextView.setError(null);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        boolean validated = true;
        boolean authenticated = false;

        //validate login
        if (email == null || email.isEmpty()) {
            validated = false;
            emailTextView.setError("Cannot be empty");
        }

        if (password == null || password.isEmpty()) {
            validated = false;
            passwordTextView.setError("Cannot be empty");
        }

        if (!validated) {
            return;
        }


        for (String cred : DUMMY_CREDENTIALS) {
            String[] parts = cred.split(":");
            if (email.equals(parts[0])
                    && password.equals(parts[1])) {
                authenticated = true;
                break;
            }
        }

        if (authenticated) {
            Toast.makeText(this, "Successfully Logged In!!", 5000).show();
        } else {
            Toast.makeText(this, "Login Failed, Please Try Again", 5000).show();
        }
    }

}
