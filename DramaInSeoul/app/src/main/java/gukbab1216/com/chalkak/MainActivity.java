package gukbab1216.com.chalkak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gukbab1216.com.chalkak.Adapter.ListDramaAdapter;
import gukbab1216.com.chalkak.Model.Drama;
import gukbab1216.com.chalkak.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    public static View.OnClickListener myOnClickListener;

    //firebase
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    //List of Drama
    private ArrayList<Drama> mDataset;
    private ListDramaAdapter mListDramaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initialize();
        initDramaList();
    }

    private void initialize() {
        myOnClickListener = new MyOnClickListener(this, mBinding);

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = mBinding.etSearch.getText().toString().trim();
                ArrayList<Drama> searchedArray = new ArrayList<>();
                for (Drama dm : mDataset) {
                    if (dm.getTitle().contains(searchText)) {
                        searchedArray.add(dm);
                    }
                }
                if (searchText.isEmpty()) {
                    mBinding.recyclerView.setAdapter(new ListDramaAdapter(mDataset));
                } else {
                    mBinding.recyclerView.setAdapter(new ListDramaAdapter(searchedArray));
                }
            }
        });

    }

    private void initDramaList() {
        mBinding.recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListDramaAdapter = new ListDramaAdapter(mDataset);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("DramaData");
        mDataset = new ArrayList<>();

        //get Drama Data
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Drama drama = dataSnapshot1.getValue(Drama.class);
                    mDataset.add(drama);
                }
                mListDramaAdapter = new ListDramaAdapter(mDataset);
                mBinding.recyclerView.setAdapter(mListDramaAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public static class MyOnClickListener extends Activity implements View.OnClickListener {

        private final Context context;
        private ActivityMainBinding mBinding;

        private MyOnClickListener(Context context, ActivityMainBinding binding) {
            this.context = context;
            mBinding = binding;
        }

        @Override
        public void onClick(View view) {
            int selectedItemPosition = mBinding.recyclerView.getChildPosition(view);
            RecyclerView.ViewHolder viewHolder = mBinding.recyclerView.findViewHolderForAdapterPosition(selectedItemPosition);

            TextView textView = viewHolder.itemView.findViewById(R.id.textViewName);
            TextView textView1 = viewHolder.itemView.findViewById(R.id.textDramaContext);
            String dramaName = (String) textView.getText();
            String dramaContext = (String) textView1.getText();

            //우리가 사용하는 드라마가 도깨비,쌈마이웨이 두가지 인데
            //Firebase 자동 정렬로 인해 데이터를 가져와서 새로 정렬하는데 시간이 오래걸려서 하드코딩 했음.
            switch (dramaName) {
                case "도깨비":
                    dramaName = "A Goblin";
                    break;
                case "쌈마이웨이":
                    dramaName = "BFight for My Way";
                    break;
                case "미녀의 탄생":
                    dramaName = "Cirth of a Beauty";
                    break;
                case "태양의 후예":
                    dramaName = "Descendants of The Sun";
                    break;
                case "닥터 이방인":
                    dramaName = "Doctor of Medicine";
                    break;
                case "별에서 온 그대":
                    dramaName = "My Love From the Star";
                    break;
                case "상속자":
                    dramaName = "The Inheritors";
                    break;
                case "함부로 애틋하게":
                    dramaName = "Uncontrollably Fond";
                    break;
                case "드라마 준비중":
                    dramaName = "zPreparing Image";
                    break;
            }


            //다음 Activity로 넘길 드라마 제목과 드라마 설명
            Intent intent = new Intent(context, MomentActivity.class);
            intent.putExtra("dramaName", dramaName);
            intent.putExtra("dramaContext", dramaContext);

            context.startActivity(intent);
        }
    }
}
