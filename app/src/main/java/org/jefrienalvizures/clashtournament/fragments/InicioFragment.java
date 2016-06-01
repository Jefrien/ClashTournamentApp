package org.jefrienalvizures.clashtournament.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jefrienalvizures.clashtournament.R;

/**
 * Created by Familia on 31/05/2016.
 */
public class InicioFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio,container,false);
    }
}
