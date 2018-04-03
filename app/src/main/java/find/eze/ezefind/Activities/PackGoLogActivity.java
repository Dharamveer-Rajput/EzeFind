package find.eze.ezefind.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.markushi.ui.CircleButton;
import find.eze.ezefind.ModelClasses.RecyclerData;
import find.eze.ezefind.R;

public class PackGoLogActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar topToolBar;
    private LinearLayout mlayoutLogout;
    private RecyclerView recyclerViewPack;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<RecyclerData> recyclerDataList = new ArrayList<>();
    private CircleButton btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pack_golog);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findIds();

        clickListeners();
        mlayoutLogout.setVisibility(View.GONE);
    }

    private void clickListeners() {

        btnStart.setOnClickListener(this);
    }

    private void findIds() {


        mlayoutLogout = findViewById(R.id.layoutLogout);
        recyclerViewPack = findViewById(R.id.recyclerViewPack);
        btnStart = findViewById(R.id.btnStart);


        recyclerViewAdapter = new RecyclerViewAdapter(recyclerDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPack.setLayoutManager(mLayoutManager);
        recyclerViewPack.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPack.setAdapter(recyclerViewAdapter);

        prepareData();

    }

    private void prepareData() {

        RecyclerData recyclerData = new RecyclerData("Kelly Kleinfelter", "Start Date: 12/18/14", "45");
        recyclerDataList.add(recyclerData);

        recyclerData = new RecyclerData("Kelly Kleinfelter", "Start Date: 12/18/14", "45");
        recyclerDataList.add(recyclerData);

        recyclerData = new RecyclerData("Kelly Kleinfelter", "Start Date: 12/18/14", "45");
        recyclerDataList.add(recyclerData);

        recyclerData = new RecyclerData("Kelly Kleinfelter", "Start Date: 12/18/14", "45");
        recyclerDataList.add(recyclerData);

        recyclerData = new RecyclerData("Kelly Kleinfelter", "Start Date: 12/18/14", "45");
        recyclerDataList.add(recyclerData);



        recyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        if(v==btnStart){
            Intent intent = new Intent(PackGoLogActivity.this,StartPackingActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
        }
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

        List<RecyclerData> horizontalList = Collections.emptyList();
        Context context;
        View view;

        public RecyclerViewAdapter(List<RecyclerData> horizontalList) {
            this.horizontalList = horizontalList;
            this.context = context;
        }




        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pack_go, parent, false);


            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            RecyclerData recyclerData = horizontalList.get(position);
            holder.tvUpperText.setText(recyclerData.getUppertext());
            holder.tvDate.setText(recyclerData.getDate());
            holder.tvNumber.setText(recyclerData.getNumber());
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tvUpperText,tvDate,tvNumber;
            private SquareImageView imageFirst,imageSec,imageThird;

            public MyViewHolder(View itemView) {
                super(itemView);

                tvUpperText = itemView.findViewById(R.id.tvUpperText);
                tvDate = itemView.findViewById(R.id.tvDate);
                tvNumber = itemView.findViewById(R.id.tvNumber);

                imageFirst = itemView.findViewById(R.id.imageFirst);
                imageSec = itemView.findViewById(R.id.imageSec);
                imageThird = itemView.findViewById(R.id.imageThird);


            }
        }
    }
}
