package com.olimou.android.content;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olimou.android.databinding.FragmentContentBinding;

public class ContentFragment extends Fragment {

    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentContentBinding lBinding = FragmentContentBinding.inflate(inflater, container, false);

        lBinding.setModel(new ContentModel());

        return lBinding.getRoot();
    }
}
