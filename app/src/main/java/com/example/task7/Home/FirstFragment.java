package com.example.task7.Home;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task7.Json.ContentJson;
import com.example.task7.R;
import com.example.task7.Util.NetUtil;

import java.util.List;

public class FirstFragment extends Fragment {
    private RecyclerView mRv;
    private Handler handler = new MyHandler();
    private List<ContentJson.ContentDataBean> mList;

    class MyHandler extends  Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ContentJson contentJson = ContentJson.decodeJson((String) msg.obj);
            mList = contentJson.datas;
            mRv.setAdapter(new FragmentFirstAdapter(mList));
            mRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetUtil.doGet("https://www.wanandroid.com/article/list/0/json", handler);
            }
        }).start();
    }

    private void initView(View view) {
        mRv=view.findViewById(R.id.rv1);
    }


}
