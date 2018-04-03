package find.eze.ezefind.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import find.eze.ezefind.R;

public class AccessCodeActivity extends AppCompatActivity {

    private LinearLayout mlayoutLogout;
    private Toolbar topToolBar;

    private TextView tvPleaseEnter,tvInviteAccessCode,tvfromEmail;
    private EditText edAccessCode;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_access_code);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mlayoutLogout = findViewById(R.id.layoutLogout);
        tvPleaseEnter = findViewById(R.id.tvPleaseEnter);
        tvInviteAccessCode = findViewById(R.id.tvInviteAccessCode);
        tvfromEmail = findViewById(R.id.tvfromEmail);

        edAccessCode = findViewById(R.id.edAccessCode);
        btnSubmit = findViewById(R.id.btnSubmit);


        mlayoutLogout.setVisibility(View.INVISIBLE);





        Typeface corbelBold = Typeface.createFromAsset(getAssets(), "fonts/Corbel-Bold.ttf");
        btnSubmit.setTypeface(corbelBold);
        tvPleaseEnter.setTypeface(corbelBold);
        tvInviteAccessCode.setTypeface(corbelBold);
        tvfromEmail.setTypeface(corbelBold);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = edAccessCode.getText().toString().trim();


            }
        });
    }
}
