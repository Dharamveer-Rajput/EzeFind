package find.eze.ezefind.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import find.eze.ezefind.ModelClasses.ContactList;
import find.eze.ezefind.R;

public class InviteContactsActivity extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CONTACT = 1 ;
    private Toolbar topToolBar;
    private LinearLayout mlayoutLogout;
    private RecyclerView recyclerViewInvite;
    private InviteAdapter inviteAdapter;
    private Button btnInviteSelected;
    private EditText edSearch;
    private int counter = 0;
    private ArrayList<ContactList> contactLists = new ArrayList<ContactList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_invite);

        topToolBar =  findViewById(R.id.toolbar);
        setSupportActionBar(topToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findIds();

        askForContactPermission();

        clickListeners();

        mlayoutLogout.setVisibility(View.GONE);

        inviteAdapter = new InviteAdapter(contactLists,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewInvite.setLayoutManager(mLayoutManager);
        recyclerViewInvite.setHasFixedSize(false);
        recyclerViewInvite.setItemAnimator(new DefaultItemAnimator());
        recyclerViewInvite.setAdapter(inviteAdapter);


        edSearchFilterCode();


    }

    private void edSearchFilterCode() {


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        });
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        //ArrayList<ContactList> filterdNames = new ArrayList<>();
        ArrayList<ContactList> contactListssearch = new ArrayList<>();


        //looping through existing elements
        for (ContactList s : contactLists) {
            //if the existing elements contains the search input
            if (s.getTvName().toString().toLowerCase().contains(text.toString().toLowerCase())) {
                //adding the element to filtered list
                contactListssearch.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        inviteAdapter.filterList(contactListssearch);
    }


    private void findIds() {
        mlayoutLogout = findViewById(R.id.layoutLogout);
        recyclerViewInvite = findViewById(R.id.recyclerViewInvite);

        edSearch = findViewById(R.id.edSearch);
        btnInviteSelected = findViewById(R.id.btnInviteSelected);
    }

    public void askForContactPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }else{
                getContacts(InviteContactsActivity.this.getContentResolver());
            }
        }
        else{
            getContacts(InviteContactsActivity.this.getContentResolver());
        }
    }

    private  void getContacts(ContentResolver contentResolver)

    {
        Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).toString().trim();

            phoneNumber = phoneNumber.replaceAll("[\\-\\(\\)\\#\\+\\*\\s+^:,]", "");

            String pic;
            Uri person = null;
            try {
                person = ContentUris.withAppendedId(
                        ContactsContract.Contacts.CONTENT_URI, getContactIDFromNumber(phoneNumber, getApplicationContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            pic = String.valueOf(person);
            if (person == null)
                pic = "android.resource://dvr.com.voicemail/drawable/accnt";


            ContactList contactList = new ContactList();

            contactList.setTvName(name);
            contactList.setTvContact(phoneNumber);
            contactList.setImageId(pic);
            contactLists.add(contactList);

        }

        phones.close();
    }

    public static int getContactIDFromNumber(String contactNumber, Context context) throws Exception {
        contactNumber = Uri.encode(contactNumber);
        int phoneContactID = new Random().nextInt();
        Cursor contactLookupCursor = context.getContentResolver().query(Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, contactNumber), new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
        while (contactLookupCursor.moveToNext()) {
            phoneContactID = contactLookupCursor.getInt(contactLookupCursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
        }
        contactLookupCursor.close();

        return phoneContactID;
    }


    private void clickListeners() {

        btnInviteSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteContactsActivity.this,AccessCodeActivity.class);
                startActivity(intent);
            }
        });

    }


    private class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.MyViewHolder>{


        private View view;
        List<ContactList> contactLists = Collections.emptyList();
        Context context;

        public void filterList(ArrayList<ContactList> filterdNames) {
            this.contactLists = filterdNames;
            notifyDataSetChanged();
        }


        public InviteAdapter(List<ContactList> contactLists, Context context) {
            this.contactLists = contactLists;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_invite_contact, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {


            Collections.sort(contactLists, new Comparator<ContactList>() {

                public int compare(ContactList obj1, ContactList obj2) {

                    return obj1.getTvName().compareToIgnoreCase(obj2.getTvName());

                }

            });


            ContactList contactList= contactLists.get(position);

            char first_letter = contactList.getTvName().charAt(0);

            List<String> MySortStrings =new ArrayList<String>();

            String aletter = "a";
            String bletter = "b";
            String cletter = "c";
            String dletter = "d";
            String eletter = "e";
            String fletter = "f";
            String gletter = "g";
            String hletter = "h";
            String iletter = "i";
            String jletter = "j";
            String kletter = "k";
            String lletter = "l";
            String mletter = "m";
            String nletter = "n";
            String oletter = "o";
            String pletter = "p";
            String qletter = "q";
            String rletter = "r";
            String sletter = "s";
            String tletter = "t";
            String uletter = "u";
            String vletter = "v";
            String wletter = "w";
            String xletter = "x";
            String yletter = "y";
            String zletter = "z";
            String[] num = { "0", "1","2","3","4","5","6","7","8","9" };



            if (Character.isLetter (first_letter)){

                if(String.valueOf(first_letter).equalsIgnoreCase(aletter)) {
                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(aletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }
                else if(String.valueOf(first_letter).equalsIgnoreCase(bletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(bletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(cletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(cletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(dletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(dletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(eletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(eletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(fletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(fletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(gletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(gletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(hletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(hletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(iletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(iletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(jletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(jletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(kletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(kletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(lletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(lletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(mletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(mletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(nletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(nletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(oletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(oletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                else if(String.valueOf(first_letter).equalsIgnoreCase(pletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(pletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(qletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(qletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(rletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(rletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(sletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(sletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }



                else if(String.valueOf(first_letter).equalsIgnoreCase(tletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(tletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(uletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(uletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(vletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(vletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(wletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(wletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(xletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(xletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(yletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(yletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }


                else if(String.valueOf(first_letter).equalsIgnoreCase(zletter)){


                    for (int i = 0; i < contactLists.size(); i++) {


                        if (contactLists.get(i).getTvName().startsWith(zletter.toUpperCase()))

                        {
                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));
                        }
                    }
                }

                int size = MySortStrings.size();

                holder.tvHeaderCount.setText(String.valueOf(size));


                holder.first.setVisibility(View.VISIBLE);
                holder.tvHeaderAlphabets.setText(String.valueOf(contactList.getTvName().charAt(0)).toUpperCase());

            }

            else {

                for(int i=0;i<contactLists.size();i++){

                    for(int k = 0;k<num.length;k++) {

                        if (contactLists.get(i).getTvName().startsWith(num[k]))

                            MySortStrings.add(String.valueOf(contactLists.get(i).getTvName()));

                    }

                }

                int size = MySortStrings.size();

                holder.tvHeaderCount.setText(String.valueOf(size));
                holder.first.setVisibility(View.VISIBLE);
                holder.tvHeaderAlphabets.setText("#");

            }


         /*   List<String> MySortStringsChar = new ArrayList<String>();

            for (int i = 0; i < contactLists.size(); i++) {


                if (contactLists.get(i).getTvName().startsWith(bletter.toUpperCase()))

                {
                    MySortStringsChar.add(String.valueOf(contactLists.get(i).getTvName()));
                }


                int size = MySortStringsChar.size();

                holder.tvHeaderCount.setText(String.valueOf(size));

            }*/


            if(position>0) {

                int pos = position - 1;
                char c = contactLists.get(pos).getTvName().charAt(0);


                if (holder.tvHeaderAlphabets.getText().toString().equalsIgnoreCase(String.valueOf(c)))
                {

                    holder.first.setVisibility(View.GONE);


                }

                if(holder.tvHeaderAlphabets.getText().toString().equalsIgnoreCase("#"))
                {
                    holder.first.setVisibility(View.GONE);

                }

            }


            holder.tvContact.setText(contactList.getTvContact());
            holder.tvName.setText(contactList.getTvName());

            Picasso.with(context).load(contactList.getImageId()).error(R.drawable.lady).into(holder.imagePerson);



            holder.checkboxContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){

                        btnInviteSelected.setTextColor(Color.GREEN);
                    }
                    else {
                        btnInviteSelected.setTextColor(Color.parseColor("#CECFD0"));

                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return contactLists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {


            private TextView tvHeaderAlphabets,tvHeaderCount,tvName,tvContact;
            private SquareImageView imagePerson;
            private CheckBox checkboxContact;
            private LinearLayout first;

            public MyViewHolder(View itemView) {
                super(itemView);


                first = itemView.findViewById(R.id.first);
                tvHeaderAlphabets = itemView.findViewById(R.id.tvHeaderAlphabets);
                tvHeaderCount = itemView.findViewById(R.id.tvHeaderCount);
                tvName = itemView.findViewById(R.id.tvName);
                tvContact = itemView.findViewById(R.id.tvContact);

                imagePerson = itemView.findViewById(R.id.imagePerson);
                checkboxContact = itemView.findViewById(R.id.checkboxContact);



            }
        }
    }
}
