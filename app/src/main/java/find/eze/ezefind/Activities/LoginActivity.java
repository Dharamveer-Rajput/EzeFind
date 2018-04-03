package find.eze.ezefind.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import find.eze.ezefind.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText medUsername,medPassword;
    private CheckBox mcheckboxRememberme;
    private TextView mtvForgetpsw,mtvRegsiternew,mtvLogout;
    private Button mbtnFindme;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private String username,password;
    private LinearLayout mlayoutLogout;
    private Toolbar topToolBar;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        findIds();

        setFonts();

        checkBoxForLogin();

        clickListeners();
    }



    private void findIds() {

        medUsername = findViewById(R.id.edUsername);
        medPassword = findViewById(R.id.edPassword);

        mcheckboxRememberme = findViewById(R.id.checkboxRememberme);

        mtvForgetpsw = findViewById(R.id.tvForgetpsw);
        mtvRegsiternew = findViewById(R.id.tvRegsiternew);

        mbtnFindme = findViewById(R.id.btnFindme);

        mlayoutLogout = findViewById(R.id.layoutLogout);

        mtvLogout = findViewById(R.id.tvLogout);

    }

    private void setFonts() {

        Typeface corbelLight = Typeface.createFromAsset(getAssets(), "fonts/CORBEL.TTF");
        mcheckboxRememberme.setTypeface(corbelLight);
        mtvForgetpsw.setTypeface(corbelLight);
        mtvRegsiternew.setTypeface(corbelLight);


        Typeface corbelBold = Typeface.createFromAsset(getAssets(), "fonts/Corbel-Bold.ttf");
        mbtnFindme.setTypeface(corbelBold);
        mtvLogout.setTypeface(corbelBold);
    }


    private void checkBoxForLogin() {

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        loginPreferences = getSharedPreferences("userrecord", 0);
        Boolean islogin = loginPreferences.getBoolean("userlogin", false);
        if(islogin){
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            medUsername.setText(loginPreferences.getString("username", ""));
            medPassword.setText(loginPreferences.getString("password", ""));
            mcheckboxRememberme.setChecked(true);
        }


    }

    private void clickListeners() {

        mbtnFindme.setOnClickListener(this);
        mlayoutLogout.setOnClickListener(this);
        mtvRegsiternew.setOnClickListener(this);
        mtvForgetpsw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==mtvForgetpsw){

            Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


        }
        if(v==mlayoutLogout){

        }

        if(v==mtvRegsiternew){


            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

        }


        if(v==mbtnFindme){

            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(medUsername.getWindowToken(), 0);

            username = medUsername.getText().toString();
            password = medPassword.getText().toString();


            if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")){
                SharedPreferences.Editor edit = loginPreferences.edit();
                edit.putBoolean("userlogin", true);
                edit.commit();

                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);


                Intent intent = new Intent(LoginActivity.this, PieChartHome.class);
                startActivity(intent);
                finish();
            }else{
               // Toast.makeText(getApplicationContext(), "Username/Password Invalid", Toast.LENGTH_LONG).show();
            }

            if (mcheckboxRememberme.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            doSomethingElse();


        }

    }

    private void doSomethingElse() {


        startActivity(new Intent(LoginActivity.this, PieChartHome.class));
        LoginActivity.this.finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
