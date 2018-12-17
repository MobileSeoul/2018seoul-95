package gukbab1216.com.chalkak;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Adapter.HorizontalSceneAdapter;
import gukbab1216.com.chalkak.Model.Picture;
import gukbab1216.com.chalkak.databinding.ActivitySceneMapBinding;

public class SceneMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private ActivitySceneMapBinding mBinding;

    private GoogleMap mMap;
    static ArrayList<Picture> pictureArrayList;
    String dramaTitle;
    HorizontalSceneAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_scene_map);

        //이전 Activity 에서 가져온 Intent
        Intent intent = getIntent();
        dramaTitle = (String) intent.getExtras().get("dramaName");
        pictureArrayList = (ArrayList<Picture>) intent.getSerializableExtra("pictureList");
        String dramaDescription = (String) intent.getExtras().get("dramaContext");
        String subDramaImage = (String) intent.getExtras().get("subImageUrl");
        mBinding.titleDrama.setText(dramaTitle);
        mBinding.summaryDrama.setText(dramaDescription);

        ImageView imageView = findViewById(R.id.thumbnail_drama);
        Glide.with(this).load(subDramaImage).into(imageView);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initHorizontalScene();

        //기존의 운현궁 위도와 경도를 받아서 시작지점을 만들었음
        //이후 밑에 섬네일 이미지 클릭시 위도와 경도가 비동기적으로 바뀌도록 해야함.
        LatLng position = new LatLng(37.576034, 126.98705199999995);
        mMap.addMarker(new MarkerOptions().position(position).title(dramaTitle + "촬영지")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

        //구글지도 줌 확대하는 부분 1~23까지의 레벨이 있음.
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
        mMap.moveCamera(zoom);
    }

    private void initHorizontalScene() {

        //이부분이 Firebase DB에서 가져온 pictureArrayList를 Adapter에 넘기는 부분인데
        //ArrayList 안에 있는 사진들의 Position을 사용하는 방법을 모르겠음.
        mBinding.scenePanel.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        mBinding.scenePanel.setHasFixedSize(true);
        mAdapter = new HorizontalSceneAdapter(this, pictureArrayList, mMap);
        mBinding.scenePanel.setAdapter(mAdapter);

    }
}
