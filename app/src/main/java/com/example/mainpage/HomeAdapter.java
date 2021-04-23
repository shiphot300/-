package com.example.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

abstract class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<MainList> mData;

    abstract void itemCallback(MainList item);

    public HomeAdapter(List<MainList> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출함.
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainlist_1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출한다. 이 메서드는 적절한 데이터를 가져와서 그 데이터를 사용하여 뷰 홀더의 레이아웃을 채워줌.
        MainList MainList = mData.get(position);
        holder.TextName.setText(MainList.getName());
        holder.mainText.setText(MainList.getText());


        //아이템 클릭시 호출하는 뷰.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCallback(MainList);
            }
        });
    }

    @Override
    public int getItemCount() {
        //리사이클러뷰의 아이탬 사이즈.
        return mData == null ? 0 : mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView listImage;
        private TextView TextName;
        private TextView mainText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listImage = itemView.findViewById(R.id.list_image);
            TextName = itemView.findViewById(R.id.list_text);
            mainText = itemView.findViewById(R.id.main_text);

        }
    }

}
