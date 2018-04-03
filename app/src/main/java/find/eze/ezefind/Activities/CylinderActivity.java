package find.eze.ezefind.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import find.eze.ezefind.R;

public class CylinderActivity extends AppCompatActivity {

    private LinearLayout mlayoutLogout;
    private Toolbar topToolBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cylinder);


        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mlayoutLogout = findViewById(R.id.layoutLogout);
        mlayoutLogout.setVisibility(View.GONE);




    }
}
