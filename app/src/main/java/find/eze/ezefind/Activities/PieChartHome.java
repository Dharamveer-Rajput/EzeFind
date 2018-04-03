package find.eze.ezefind.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.renderer.PieChartRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import find.eze.ezefind.Dialogs.DialogNewEntry;
import find.eze.ezefind.R;

public class PieChartHome extends AppCompatActivity implements View.OnClickListener {


    private PieChart pieChart;
    private LinearLayout mlayoutLogout;
    private static final int[] PIE_COLORS = {Color.rgb(255, 102, -0), Color.rgb(169, 169, 169)
            , Color.rgb(0, 0, 102)};

    private ImageView imageViewReport,imageViewSettings,imageViewInventory,imageInnerCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart_home);


        mlayoutLogout = findViewById(R.id.layoutLogout);
        mlayoutLogout.setVisibility(View.GONE);


        imageViewReport = findViewById(R.id.imageViewReport);
        imageViewSettings = findViewById(R.id.imageViewSettings);
        imageViewInventory = findViewById(R.id.imageViewInventory);
        imageInnerCircle = findViewById(R.id.imageInnerCircle);





        imageViewReport.setOnClickListener(this);
        imageViewSettings.setOnClickListener(this);
        imageViewInventory.setOnClickListener(this);
        imageInnerCircle.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {


        if(v==imageViewReport){
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
            imageViewReport.startAnimation(animFadein);



        }
        if(v==imageViewSettings){
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
            imageViewSettings.startAnimation(animFadein);
        }

        if(v==imageViewInventory){
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_out);
            imageViewInventory.startAnimation(animFadein);
        }

        if(v==imageInnerCircle){
            Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            imageInnerCircle.startAnimation(animFadein);


            DialogNewEntry dialogNewEntry = new DialogNewEntry(this);

            Window window = dialogNewEntry.getWindow();

            window.setGravity(Gravity.CENTER_HORIZONTAL);

            WindowManager.LayoutParams wmlp = dialogNewEntry.getWindow().getAttributes();

            wmlp.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
           // wmlp.x = 350;   //x position
            wmlp.y = 350;   //y position


            dialogNewEntry.show();



        }
    }

}

/*


        pieChart =  findViewById(R.id.piechart);



        pieChart.setUsePercentValues(false);

        pieChart.setDescription("");

        pieChart.setRotationEnabled(false);

        pieChart.getLegend().setEnabled(false);


        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(15f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(15f, 2));

        PieDataSet dataSet = new PieDataSet(yvalues, "");


        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Report");
        xVals.add("View Inventory");
        xVals.add("Setting");


        PieData data = new PieData(xVals, dataSet);

        data.setValueFormatter(new PercentFormatter());


        ArrayList<Integer> colors = new ArrayList<Integer>();

        for(int c: PIE_COLORS)
            colors.add(c);
        dataSet.setColors(colors); //

        pieChart.setData(data);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(45);
        pieChart.setTransparentCircleColor(R.color.greenbtn);
        pieChart.setHoleRadius(40f);
        pieChart.setHoleColor(R.color.app_bg_color);


        pieChart.setDrawSliceText(true);

        dataSet.setDrawValues(false);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);


        pieChart.setOnChartValueSelectedListener(this);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {

    }*/




