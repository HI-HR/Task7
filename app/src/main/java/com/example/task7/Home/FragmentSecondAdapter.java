package com.example.task7.Home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.task7.Json.TutorialJson;
import com.example.task7.R;
import com.example.task7.WebActivity;

import org.w3c.dom.Text;

import java.util.List;

public class FragmentSecondAdapter extends RecyclerView.Adapter<FragmentSecondAdapter.ViewHolder> {
    public List<TutorialJson.TutorialJsonDataBean> mList;

    public FragmentSecondAdapter(List<TutorialJson.TutorialJsonDataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_second, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TutorialJson.TutorialJsonDataBean tutorialJsonDataBean = mList.get(holder.getAdapterPosition());
                Toast.makeText(parent.getContext(), "你点击了"+tutorialJsonDataBean.name,Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(parent.getContext(), WebActivity.class);
//                intent.putExtra("URL", "https://www.wanandroid.com/book/intro/" + tutorialJsonDataBean.id);
//                parent.getContext().startActivity(intent);
                //这个页面需要登录，cookie持久化登录什么的，暂时不会，以后填坑
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TutorialJson.TutorialJsonDataBean tutorialJsonDataBean = mList.get(position);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background)

                .fallback(R.drawable.ic_launcher_background);
        Glide.with(holder.itemView.getContext()).load(tutorialJsonDataBean.cover).apply(requestOptions).into(holder.mImg);
        holder.mAuthor.setText(tutorialJsonDataBean.author);
        holder.mTitle.setText(tutorialJsonDataBean.name);
        holder.mDesc.setText(tutorialJsonDataBean.desc);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mDesc;
        private ImageView mImg;
        private View itemView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.iv_img);
            mTitle = itemView.findViewById(R.id.tv_title);
            mDesc = itemView.findViewById(R.id.tv_desc);
            mAuthor=itemView.findViewById(R.id.author);
            this.itemView=itemView;
        }
    }
}
