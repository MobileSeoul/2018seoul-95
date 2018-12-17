package gukbab1216.com.chalkak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import gukbab1216.com.chalkak.CameraActivity;
import gukbab1216.com.chalkak.Model.Picture;
import gukbab1216.com.chalkak.R;

public class HorizontalSceneAdapter extends RecyclerView.Adapter<HorizontalSceneAdapter.ViewHolder> {
    private List<Picture> mItems;
    private Context mContext;
    private GoogleMap mGmap;
    boolean[] flag = new boolean[3];
    float latitude = 0;
    float longitude = 0;

    public HorizontalSceneAdapter(Context context, List<Picture> items, GoogleMap googleMap) {
        mItems = items;
        mContext = context;
        mGmap = googleMap;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene_map, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Picture picture = mItems.get(position);

        //가져온 사진 리스트에 맞게 view에 맵핑되는 부분.
        String posTitle = picture.getPosTitle();
        Glide.with(mContext).load(picture.getImgUrl()).into(holder.mThumbnail);
        holder.mTitle.setText(posTitle);


        holder.mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filterImg = "";
                String filterLine = "";

                filterImg = mItems.get(position).getFilterImg();
                filterLine = mItems.get(position).getFilterLine();
                latitude = mItems.get(position).getLatitude();
                longitude = mItems.get(position).getLongitude();

                if (!flag[position]) {
                    int pos = position;
                    latitude = mItems.get(pos).getLatitude();
                    longitude = mItems.get(pos).getLongitude();

                    LatLng position = new LatLng(latitude, longitude);
                    CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);

                    mGmap.addMarker(new MarkerOptions().position(position).title(mItems.get(pos).getPosTitle())).showInfoWindow();
                    mGmap.moveCamera(CameraUpdateFactory.newLatLng(position));
                    mGmap.moveCamera(zoom);

                    flag[pos] = true;

                } else if (flag[position]) {

                    Intent it = new Intent(mContext, CameraActivity.class);
                    it.putExtra("filterImg", filterImg);
                    it.putExtra("filterLine", filterLine);
                    mContext.startActivity(it);
                    flag[position] = false;
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mThumbnail;
        TextView mTitle;

        ViewHolder(View view) {
            super(view);
            mThumbnail = view.findViewById(R.id.thumbnail_scene);
            mTitle = view.findViewById(R.id.title_scene);
        }

    }
}

