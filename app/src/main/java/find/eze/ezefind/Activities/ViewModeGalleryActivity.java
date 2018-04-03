package find.eze.ezefind.Activities;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import find.eze.ezefind.Dialogs.FilterDialog;
import find.eze.ezefind.R;

public class ViewModeGalleryActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbarwithtime;
    private LinearLayout linearBackbtn,linearFilter,linearViewPack;
    private TextView tvNameofPlace,tvTimeToolbar,tvBack,tvViewPackGo;
    private GridView gridviewCustom;

    public static String[] numbersList = {
            "45",
            "23",
            "23",
            "12",
            "23",
            "31",

    };


    public static String[] itemNames = {
            "Box 1",
            "Bag 1",
            "Bag 2",
            "Bin 1",
            "Single Item 1",
            "Box 2",

    };

    public static int[] GridViewImages = {
            R.drawable.meter,
            R.drawable.bluebag,
            R.drawable.redbag,
            R.drawable.dustbin,
            R.drawable.jacket,
            R.drawable.box,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_mode_gallery);

        findIds();

        setData();

        clickListeners();

    }


    private void findIds() {

        toolbarwithtime =  findViewById(R.id.toolbarwithtime);

        //Linear Layouts
        linearBackbtn = findViewById(R.id.linearBackbtn);
        linearFilter = findViewById(R.id.linearFilter);
        linearViewPack = findViewById(R.id.linearViewPack);

        //Text Views
        tvNameofPlace = findViewById(R.id.tvNameofPlace);
        tvTimeToolbar = findViewById(R.id.tvTimeToolbar);
        tvBack = findViewById(R.id.tvBack);
        tvViewPackGo = findViewById(R.id.tvViewPackGo);


        //Gr
        gridviewCustom = findViewById(R.id.gridviewCustom);

    }


    private void setData() {
        setSupportActionBar(toolbarwithtime);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        gridviewCustom.setAdapter(new CustomAdapter(this, GridViewImages,numbersList,itemNames));


        Typeface corbelLight = Typeface.createFromAsset(getAssets(), "fonts/CORBEL.TTF");
        tvNameofPlace.setTypeface(corbelLight);
        tvTimeToolbar.setTypeface(corbelLight);
        tvBack.setTypeface(corbelLight);
        tvViewPackGo.setTypeface(corbelLight);

    }

    private void clickListeners() {

        linearBackbtn.setOnClickListener(this);
        linearFilter.setOnClickListener(this);
        linearViewPack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==linearBackbtn){

            finish();
        }

        if(v==linearFilter){

            FilterDialog filterDialog = new FilterDialog(this);
            filterDialog.show();
        }

        if(v==linearViewPack){

        }
    }

    private class CustomAdapter extends BaseAdapter {

        Context context;
        int [] imageId;
        String[] numbersList;
        String[] itemNames;

        private  LayoutInflater inflater=null;

        public CustomAdapter(ViewModeGalleryActivity viewModeGalleryActivity, int[] gridViewImages,String[] numbersList2,String[] itemNames2) {

            context=viewModeGalleryActivity;
            imageId=gridViewImages;
            numbersList=numbersList2;
            itemNames=itemNames2;
            inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public int getCount() {
            return imageId.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder{
            ImageView gridImages;
            TextView tvNumber,tvBox;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            Holder holder=new Holder();
            View rowView;

            rowView = inflater.inflate(R.layout.gridlayout, null);

            holder.gridImages = rowView.findViewById(R.id.gridImages);
            holder.tvNumber = rowView.findViewById(R.id.tvNumber);
            holder.tvBox = rowView.findViewById(R.id.tvBox);

            holder.tvNumber.setText(numbersList[position]);
            holder.tvBox.setText(itemNames[position]);
            holder.gridImages.setImageResource(imageId[position]);

            return rowView;
        }

    }
}
