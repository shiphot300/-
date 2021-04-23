package com.example.mainpage.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mainpage.R;

public class PopularSearchFragment extends Fragment {
    public static PopularSearchFragment newInstance() {
        PopularSearchFragment fragment = new PopularSearchFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_popular_list, container, false);
    }
}
