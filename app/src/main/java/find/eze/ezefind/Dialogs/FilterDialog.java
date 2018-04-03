package find.eze.ezefind.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import find.eze.ezefind.R;

public class FilterDialog extends Dialog {

    private TextView tvFilter;
    private CheckBox checkboxBag,checkboxBin,checkboxBox,checkboxSingleitem;

    public FilterDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_filter);


        tvFilter = findViewById(R.id.tvFilter);

        checkboxBag = findViewById(R.id.checkboxBag);
        checkboxBin = findViewById(R.id.checkboxBin);
        checkboxBox = findViewById(R.id.checkboxBox);
        checkboxSingleitem = findViewById(R.id.checkboxSingleitem);




    }
}
