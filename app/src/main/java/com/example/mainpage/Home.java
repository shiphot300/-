package com.example.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainpage.search.SearchActivity;
import com.example.mainpage.write.WriteActivity;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;

public class Home extends Fragment {
    private View view;
    private FirstAdapter firstAdapter;
    private CategoryAdapter categoryAdapter;
    private SpeedDialView mSpeedDialView;

    private ListView listView;
    private RecyclerView rvContentView;
    private RecyclerView rvCategoryView;
    private ConstraintLayout viewSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hom, container, false);
        listView = view.findViewById(R.id.list);
        mSpeedDialView = view.findViewById(R.id.speedDial);
        rvContentView = view.findViewById(R.id.rv_content);
        rvCategoryView = view.findViewById(R.id.rv_category);
        viewSearch = view.findViewById(R.id.view_search);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<MainList> data = new ArrayList<>();
        data.add(new MainList("상품1", "상품제목" , "상품설명"));
        data.add(new MainList("상품2", "상품제목" , "상품설명"));
        data.add(new MainList("상품3", "상품제목" , "상품설명"));
        data.add(new MainList("상품4", "상품제목" , "상품설명"));
        data.add(new MainList("상품5", "상품제목" , "상품설명"));
        data.add(new MainList("상품6", "상품제목" , "상품설명"));
        data.add(new MainList("상품7", "상품제목" , "상품설명"));
        data.add(new MainList("상품8", "상품제목" , "상품설명"));




        firstAdapter = new FirstAdapter(data);
        listView.setAdapter(firstAdapter);


        initDialVieW();
        initRecyclerView(data);
        initCategoryRecyclerView();
        //클릭이벤트
        listView.setOnItemClickListener((parent, view1, position, id) -> Toast.makeText(getActivity(), position + " 번째 아이템 선택", Toast.LENGTH_SHORT ).show());


        viewSearch.setOnClickListener(view12 -> startActivity(new Intent(getActivity(), SearchActivity.class)));
    }

    private void initCategoryRecyclerView(){


        ArrayList<Category> categoryData = new ArrayList<>();
        categoryData.add(new Category(0,"카테고리"));
        categoryData.add(new Category(0,"카테고리"));
        categoryData.add(new Category(0,"카테고리"));
        categoryData.add(new Category(0,"카테고리"));


        categoryAdapter = new CategoryAdapter(categoryData) {
            @Override
            void itemCallback(Category item) {

            }
        };

        //리사이클러뷰의 타입을 선택할수있다 gird,,, linear(세로 가로).. 디폴트는 세로 스크롤
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        rvCategoryView.setLayoutManager(gridLayoutManager);
        rvCategoryView.setAdapter(categoryAdapter);

    }

    private void initRecyclerView(ArrayList<MainList> data){
        HomeAdapter homeAdapter = new HomeAdapter(data) {
            @Override
            void itemCallback(MainList item) {

            }
        };

        //리사이클러뷰의 타입을 선택할수있다 gird,,, linear(세로 가로).. 디폴트는 세로 스크롤
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvContentView.setLayoutManager(linearLayoutManager);
        rvContentView.setAdapter(homeAdapter);

    }

    private void initDialVieW(){
        //fab 버튼에 추가할 item...   id 값을 다르게 해주세요. 그옆 drawable을 이미지.
        mSpeedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_no_label, R.drawable.ic_link_white_24dp).create());
        mSpeedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_no_label2, R.drawable.ic_link_white_24dp).create());

        mSpeedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem actionItem) {
                switch (actionItem.getId()){
                    case R.id.fab_no_label:
                        startActivity(new Intent(getActivity(), WriteActivity.class));
                        return true;
                    case R.id.fab_no_label2:
                        return true;
                }
                return false;
            }
        });

    }



}
