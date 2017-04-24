package com.beacons.railwayaid.raid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customlbs.library.IndoorsFactory;
import com.customlbs.surface.library.IndoorsSurfaceFactory;
import com.customlbs.surface.library.IndoorsSurfaceFragment;

public class Navigation extends Fragment{



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_layout, container, false);

        IndoorsFactory.Builder indoorsBuilder = new IndoorsFactory.Builder();
        IndoorsSurfaceFactory.Builder surfaceBuilder = new IndoorsSurfaceFactory.Builder();

        indoorsBuilder.setContext(getContext());

        indoorsBuilder.setApiKey("69af5e85-007a-4df5-8715-d1c2c22e206f");



        indoorsBuilder.setBuildingId((long) 979231679);

        surfaceBuilder.setIndoorsBuilder(indoorsBuilder);

        IndoorsSurfaceFragment indoorsFragment = surfaceBuilder.build();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(android.R.id.tabcontent, indoorsFragment, "indoors");
        transaction.commit();


        return v;
    }
}
