package com.example.task7.FragmnetFirst;

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

import com.example.task7.FragmentSecond.FragmentSecondAdapter;
import com.example.task7.Json.TutorialJson;
import com.example.task7.R;
import com.example.task7.Util.NetUtil;

import java.util.List;

public class SecondFragment extends Fragment {
    private RecyclerView mRv;
    private Handler mHandler = new MyHandler();
    private List<TutorialJson.TutorialJsonDataBean> mList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        initView(view);
        initData();

        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NetUtil.doGet("https://www.wanandroid.com/chapter/547/sublist/json",mHandler);
            }
        }).start();
    }

    private void initView(View view){
        mRv=view.findViewById(R.id.rv2);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            TutorialJson tutorialJson = TutorialJson.decodeJson((String) msg.obj);
            mList=tutorialJson.data;
            mRv.setAdapter(new FragmentSecondAdapter(mList));
            mRv .setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        }
    }
}
