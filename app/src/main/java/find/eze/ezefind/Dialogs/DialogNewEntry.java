package find.eze.ezefind.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import find.eze.ezefind.Activities.PackGoLogActivity;
import find.eze.ezefind.R;

public class DialogNewEntry extends Dialog implements View.OnClickListener {


    private LinearLayout linearPack,linearInventory;

    public DialogNewEntry(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_new_entry);


        linearPack = findViewById(R.id.linearPack);
        linearInventory = findViewById(R.id.linearInventory);

        linearPack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==linearPack){

            Intent intent = new Intent(getContext(),PackGoLogActivity.class);
            getContext().startActivity(intent);


        }
    }
}
