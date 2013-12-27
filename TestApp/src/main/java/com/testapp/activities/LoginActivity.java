package com.testapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.testapp.R;
import com.testapp.services.LoginService;
import com.testapp.util.TACallback;

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

    @ViewById(R.id.login_status)
    View loginStatusView;

    @ViewById(R.id.login_form)
    View loginFormView;

    @Bean
    LoginService loginService;


    @Click(R.id.sign_in_button)
    public void tryLogin() {
        emailTextView.setError(null);
        passwordTextView.setError(null);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        boolean validated = true;

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

        loginService.attemptLogin(email, password, new TACallback<Boolean>() {

            @Override
            public void onCallback(Boolean authenticated) {
                onLoginReturn(authenticated);
            }

        });

        loginStatusView.setVisibility(View.VISIBLE);
        loginFormView.setVisibility(View.GONE);
    }

    @UiThread
    void onLoginReturn(Boolean authenticated) {
        if (authenticated) {
            Toast.makeText(LoginActivity.this, "Successfully Logged In!!", 5000).show();
            MainActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
        } else {
            Toast.makeText(LoginActivity.this, "Login Failed, Please Try Again", 5000).show();
            loginFormView.setVisibility(View.VISIBLE);
            loginStatusView.setVisibility(View.GONE);
        }
    }

}
