package com.testapp.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.InstanceState;
import com.testapp.R;
import com.testapp.activities.MainActivity;

@EFragment(R.layout.fragment_nav)
public class NavFragment extends Fragment {

    @InstanceState
    int currentNavSection = -1;

    @Click(R.id.section_1_label)
    public void onSection1Select() {
        currentNavSection = 1;
        getMainActivity().updateNavFragment(
                PlaceholderFragment_.builder()
                    .sectionNumber(1).build());
    }

    @Click(R.id.section_2_label)
    public void onSection2Select() {
        currentNavSection = 2;
        getMainActivity().updateNavFragment(
                PlaceholderFragment_.builder()
                        .sectionNumber(2).build());
    }

    @Click(R.id.section_3_label)
    public void onSection3Select() {
        currentNavSection = 3;
        getMainActivity().updateNavFragment(
                PlaceholderFragment_.builder()
                        .sectionNumber(3).build());
    }

    @AfterInject
    void init() {
        switch(currentNavSection) {
            case 2:
                onSection2Select();
                break;
            case 3:
                onSection3Select();
                break;
            case 1:
            default:
                onSection1Select();
                break;
        }
    }

    private MainActivity getMainActivity() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity) {
            return (MainActivity) activity;
        } else {
            return null;
        }
    }
}
