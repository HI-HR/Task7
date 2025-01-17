package com.example.task7.level1;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.task7.R;

public class Vp2Adapter extends RecyclerView.Adapter<Vp2Adapter.ViewHolder>{
    private Level1Json mData;

    public Vp2Adapter(Level1Json mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background)

                .fallback(R.drawable.ic_launcher_background);
        Level1Json.Level1JsonDataBean dataBean = mData.data.get(position);
        holder.mName.setText(dataBean.name);
        holder.mAuthor.setText(dataBean.author);
        holder.mDesc.setText(dataBean.desc);
        //Glide.with(holder.itemView.getContext()).load(dataBean.cover).apply(requestOptions).into(holder.mImg);
        Glide.with(holder.itemView.getContext()).load(dataBean.cover).apply(requestOptions).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mData.data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImg;
        private TextView mName;
        private TextView mAuthor;
        private TextView mDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.siv_item_image);
            mName = itemView.findViewById(R.id.tv_item_name);
            mDesc = itemView.findViewById(R.id.tv_item_desc);
            mAuthor=itemView.findViewById(R.id.tv_item_author);
        }
    }
}
