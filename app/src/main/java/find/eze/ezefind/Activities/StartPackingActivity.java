package find.eze.ezefind.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import at.markushi.ui.CircleButton;
import find.eze.ezefind.R;

public class StartPackingActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar topToolBar;
    private RelativeLayout relativeStartPacking;
    private EditText edTitle,edAddress,edStartDate,edStartTime;
    private CircleButton btnStart;
    private TextView tvStartPacking;
    private LinearLayout mlayoutLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start_packing);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findIds();

        mlayoutLogout.setVisibility(View.GONE);

        clickListeners();
        setFonts();

    }

    private void findIds() {

        relativeStartPacking = findViewById(R.id.relativeStartPacking);

        edTitle = findViewById(R.id.edTitle);
        edAddress = findViewById(R.id.edAddress);
        edStartDate = findViewById(R.id.edStartDate);
        edStartTime = findViewById(R.id.edStartTime);

        tvStartPacking = findViewById(R.id.tvStartPacking);

        btnStart = findViewById(R.id.btnStart);


        mlayoutLogout = findViewById(R.id.layoutLogout);

    }




    private void clickListeners() {

        btnStart.setOnClickListener(this);
    }



    private void setFonts() {

        Typeface interstate_light = Typeface.createFromAsset(getAssets(), "fonts/interstate-light-compressed.ttf");
        tvStartPacking.setTypeface(interstate_light);


    }


    @Override
    public void onClick(View v) {

        if(v==btnStart){

            Intent intent = new Intent(StartPackingActivity.this,PackGoLogExistingActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);


        }
    }
}
