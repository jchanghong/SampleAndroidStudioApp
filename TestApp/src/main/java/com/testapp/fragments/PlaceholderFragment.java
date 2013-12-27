package com.testapp.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.FragmentArg;
import com.googlecode.androidannotations.annotations.ViewById;
import com.testapp.R;
import com.testapp.activities.MainActivity;

/**
 * Created by kkirch on 12/26/13.
 */
@EFragment(R.layout.fragment_main)
public class PlaceholderFragment extends Fragment {

    @ViewById(R.id.section_label)
    TextView sectionLabel;

    @FragmentArg
    int sectionNumber = -1;

    boolean justAttached = false;

    MainActivity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        justAttached = true;
        if (activity instanceof MainActivity) {
            this.activity = (MainActivity) activity;
        }
    }

    @AfterViews
    public void afterViewInjection() {
        sectionLabel.setText("This is Section " + sectionNumber);
        if (justAttached && activity != null) {
            activity.setActionBarTitle("Fragment - " + sectionNumber);
            justAttached = false;
        }
    }
}
