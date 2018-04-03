package find.eze.ezefind.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import find.eze.ezefind.R;

public class RegisterActivity extends AppCompatActivity {


    public EditText medFirstname,medEmail,medPass,medRetypepsw;
    public Button btnVerfiyme;
    private LinearLayout mlayoutLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        setContentView(R.layout.activity_register);



        findIds();


        mlayoutLogout.setVisibility(View.GONE);
        clickListeners();
    }



    private void findIds() {


        medFirstname = findViewById(R.id.edFirstname);
        medEmail = findViewById(R.id.edEmail);
        medPass = findViewById(R.id.edPass);
        medRetypepsw = findViewById(R.id.edRetypepsw);

        btnVerfiyme = findViewById(R.id.btnVerfiyme);

        mlayoutLogout = findViewById(R.id.layoutLogout);

    }

    private void clickListeners() {

        btnVerfiyme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
