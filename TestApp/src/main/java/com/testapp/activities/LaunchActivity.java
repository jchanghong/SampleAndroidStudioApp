package com.testapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent loginIntent = new Intent(this, LoginActivity_.class);
        startActivity(loginIntent);
    }

}
