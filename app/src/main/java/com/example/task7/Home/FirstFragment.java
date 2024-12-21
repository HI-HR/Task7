package com.example.task7.Home;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText mEditPage ;
    private Button mBtnJump;
    private TextView mPage;


    class MyHandler extends  Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ContentJson contentJson = ContentJson.decodeJson((String) msg.obj);
            mPage.setText(String.valueOf(contentJson.curPage));
            mRv.setAdapter(new FragmentFirstAdapter(contentJson.datas));
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
        initEvent();

        return view;
    }

    private void initEvent() {
        /**
         * 实现跳转逻辑
         */
        mBtnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditPage.getText().length()==0){
                    Toast.makeText(getContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    int page = Integer.parseInt(mEditPage.getText().toString());
                    if (page>0&&page<796){
                        mEditPage.setText("");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                NetUtil.doGet("https://www.wanandroid.com/article/list/"+(page-1)+"/json", handler);
                            }
                        }).start();

                    }else{
                        Toast.makeText(getContext(),"输入不合法",Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
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
        mBtnJump=view.findViewById(R.id.btn_fragment_first_jump);
        mEditPage=view.findViewById(R.id.et_fragment_first_page);
        mPage =view.findViewById(R.id.tv_page);
    }


}
