package gukbab1216.com.chalkak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gukbab1216.com.chalkak.MainActivity;
import gukbab1216.com.chalkak.Model.Drama;
import gukbab1216.com.chalkak.R;

public class ListDramaAdapter extends RecyclerView.Adapter<ListDramaAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Drama> mDataset;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;
        ImageView imageView;


        private MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewDescription = itemView.findViewById(R.id.textDramaContext);
            this.imageView = itemView.findViewById(R.id.camera_imageView);
        }

    }

    public ListDramaAdapter(ArrayList<Drama> dataset) {
        mDataset = dataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
        mContext = view.getContext();
        view.setOnClickListener(MainActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewDescription = holder.textViewDescription;
        textViewName.setText(mDataset.get(position).getTitle());
        textViewDescription.setText(mDataset.get(position).getDescription());
        Glide.with(mContext).load(mDataset.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
