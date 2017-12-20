package com.example.pooja.myappdemonew.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Zafar.Hussain on 20/12/2017.
 */

public abstract class BackHandledFragment extends Fragment {
    protected BackHandlerInterface backHandlerInterface;
    public abstract String getTagText();
    public abstract boolean onBackPressed();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!(getActivity()  instanceof BackHandlerInterface)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // Mark this fragment as the selected Fragment.
        backHandlerInterface.setSelectedFragment(this);
    }

    public interface BackHandlerInterface {
        public void setSelectedFragment(BackHandledFragment backHandledFragment);
    }
}
