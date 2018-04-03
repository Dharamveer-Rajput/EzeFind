package find.eze.ezefind.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import find.eze.ezefind.Dialogs.ShareEmailDialog;
import find.eze.ezefind.ModelClasses.RecyclerDataExisting;
import find.eze.ezefind.R;

public class PackGoLogExistingActivity extends AppCompatActivity implements  View.OnClickListener {


    private Toolbar topToolBar2;
    private LinearLayout mlayoutLogout;
    private RecyclerView recyclerViewPack;
    private RecyclerViewAdapterExisting recyclerViewAdapterExisting;
    private List<RecyclerDataExisting> recyclerDataExistings = new ArrayList<>();
    private ImageView imageGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pack_go_log_existing);

        topToolBar2 =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findIds();

        clickListeners();

        mlayoutLogout.setVisibility(View.GONE);

    }


    private void findIds() {

        mlayoutLogout = findViewById(R.id.layoutLogout);
        recyclerViewPack = findViewById(R.id.recyclerViewPack);

        imageGoHome = findViewById(R.id.imageGoHome);

        recyclerViewAdapterExisting = new PackGoLogExistingActivity.RecyclerViewAdapterExisting(recyclerDataExistings);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPack.setLayoutManager(mLayoutManager);
        recyclerViewPack.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPack.setAdapter(recyclerViewAdapterExisting);

        prepareData();

    }
    private void clickListeners() {

        imageGoHome.setOnClickListener(this);

    }

    private void prepareData() {

        RecyclerDataExisting recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerData = new RecyclerDataExisting("Moving Down Town...", "Start Date: 12/18/14", "45");
        recyclerDataExistings.add(recyclerData);

        recyclerViewAdapterExisting.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        if(v==imageGoHome){

            Intent intent = new Intent(this,PieChartHome.class);
            startActivity(intent);
        }

    }



    private static class RecyclerViewAdapterExisting extends RecyclerView.Adapter<PackGoLogExistingActivity.RecyclerViewAdapterExisting.MyViewHolder>   {

        private List<RecyclerDataExisting> horizontalList = Collections.emptyList();
        private Context context;
        private  OnItemClickListener onClickListener;
        private View view;

        public interface OnItemClickListener {
            void OpenClick( int position);
        }



        public RecyclerViewAdapterExisting(List<RecyclerDataExisting> horizontalList) {
            this.horizontalList = horizontalList;
            this.onClickListener = onClickListener;

        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            context = parent.getContext();

            if(viewType==1){
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pack_existing, parent, false);



            }
            else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pack_go_exist, parent, false);
            }
            return new PackGoLogExistingActivity.RecyclerViewAdapterExisting.MyViewHolder(view);


        }




        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            final int itemType = getItemViewType(position);

            RecyclerDataExisting recyclerData = horizontalList.get(position);
            holder.tvUpperText.setText(recyclerData.getUppertext());
            holder.tvDate.setText(recyclerData.getDate());
            holder.tvNumber.setText(recyclerData.getNumber());

            if(itemType==1){
                holder.btnOpen.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          Intent intent = new Intent(context,ViewModeGalleryActivity.class);
                                                          context.startActivity(intent);
                                                      }
                                                  }


                );

                holder.btnShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        ShareEmailDialog shareEmailDialog = new ShareEmailDialog(context);


                        Window window = shareEmailDialog.getWindow();
                        WindowManager.LayoutParams wlp = window.getAttributes();

                        wlp.gravity = Gravity.TOP|Gravity.RIGHT;
                        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                        wlp.y = 500;
                        window.setAttributes(wlp);
                        shareEmailDialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                        shareEmailDialog.show();

                    }
                });

                holder.btnInvite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,InviteContactsActivity.class);
                        context.startActivity(intent);

                    }
                });
            }

        }


        @Override
        public int getItemViewType(int position) {


            if(position==0)
                return 1;
            else
                return 2;
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }



        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tvUpperText,tvDate,tvNumber;
            private SquareImageView imageFirst,imageSec,imageThird;
            private Button btnOpen,btnShare,btnInvite;



            public MyViewHolder(View itemView) {
                super(itemView);



                tvUpperText = itemView.findViewById(R.id.tvUpperText);
                tvDate = itemView.findViewById(R.id.tvDate);
                tvNumber = itemView.findViewById(R.id.tvNumber);

                imageFirst = itemView.findViewById(R.id.imageFirst);
                imageSec = itemView.findViewById(R.id.imageSec);
                imageThird = itemView.findViewById(R.id.imageThird);

                btnOpen = itemView.findViewById(R.id.btnOpen);
                btnShare = itemView.findViewById(R.id.btnShare);
                btnInvite = itemView.findViewById(R.id.btnInvite);



            }

        }
    }
}
