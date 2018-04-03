package find.eze.ezefind.Activities;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import find.eze.ezefind.R;

public class ResetPasswordActivity extends AppCompatActivity {


    private EditText medEmailAddress;
    private Button mbtnSubmit;
    private Toolbar topToolBar;
    private TextView tvResetPsw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_reset_password);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findIds();

        setClickListeners();

    }

    private void findIds() {

        medEmailAddress = findViewById(R.id.edEmailAddress);
        tvResetPsw = findViewById(R.id.tvResetPsw);
        mbtnSubmit = findViewById(R.id.btnSubmit);

        Typeface interstate_light = Typeface.createFromAsset(getAssets(), "fonts/interstate-light-compressed.ttf");
        tvResetPsw.setTypeface(interstate_light);

        Typeface corbelBold = Typeface.createFromAsset(getAssets(), "fonts/Corbel-Bold.ttf");
        mbtnSubmit.setTypeface(corbelBold);
    }

    private void setClickListeners() {

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAddress = medEmailAddress.getText().toString().trim();





            }
        });
    }
}
