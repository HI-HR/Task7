package com.example.task7.Home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task7.Json.ContentJson;
import com.example.task7.R;
import com.example.task7.WebActivity;
import com.example.task7.level1.Level1Json;

import java.util.List;

public class FragmentFirstAdapter extends RecyclerView.Adapter<FragmentFirstAdapter.ViewHolder>{
    private List<ContentJson.ContentDataBean> mList;

    public FragmentFirstAdapter(List<ContentJson.ContentDataBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_first, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentJson.ContentDataBean contentDataBean = mList.get(holder.getAdapterPosition());
                Intent intent = new Intent(parent.getContext(), WebActivity.class);
                intent.putExtra("URL", contentDataBean.link);
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContentJson.ContentDataBean contentDataBean = mList.get(position);
        holder.mDate.setText(contentDataBean.niceDate);
        holder.mAuthor.setText(contentDataBean.author);
        holder.mTitle.setText(contentDataBean.title);
        holder.mSuperChapter.setText(contentDataBean.superChapterName);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        private TextView mSuperChapter;
        private TextView mAuthor;
        private TextView mDate;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tv_content_title);
            mAuthor=itemView.findViewById(R.id.tv_content_author);
            mDate=itemView.findViewById(R.id.tv_content_date);
            mSuperChapter= itemView.findViewById(R.id.tv_content_superChapter);
            this.itemView=itemView;
        }
    }
}
