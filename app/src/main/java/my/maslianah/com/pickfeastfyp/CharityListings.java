package my.maslianah.com.pickfeastfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import my.maslianah.com.pickfeastfyp.model.Charity;
import my.maslianah.com.pickfeastfyp.model.Packages;
import my.maslianah.com.pickfeastfyp.model.rowPack;

public class CharityListings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private DatabaseReference mDatabase;
    private ListView listView;
    String list = "",list2="";
    Query query;
    int count = 1;
    private FirebaseAuth mAuth;
    List<rowPack> rows;
    ArrayList<String>details;
    ArrayAdapter arrayAdapter;
    charity_listing adapter;
    DatabaseHelper db;
    public static final Integer[] images = { R.drawable.hklcharity, R.drawable.amancharity, R.drawable.gelandangancharity, R.drawable.tmnmegahcharity,R.drawable.wardahcharity };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_listings);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = new DatabaseHelper(this);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Charity");
        rows = new ArrayList<rowPack>();
        details = new ArrayList<String>();
        adapter = new charity_listing(getApplicationContext(), R.layout.activity_row_listing, rows);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,details);
        listView = (ListView)findViewById(R.id.lv2);
        listView.setOnItemClickListener(this);

        if (!isOnline()){
            Toast.makeText(getApplicationContext(), "No Internet Connection!\nCouldn't refresh list", Toast.LENGTH_SHORT).show();
        }
        //list charity in list view
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Charity charity = postSnapshot.getValue(Charity.class);
                    list = "\nCharity " + (count++) +"\n"+ charity.toString();
                    list2 = charity.getCharityID() ;

                    rowPack desc = new rowPack(checkImages(charity.getCharityID()),list,null);
                    rows.add(desc);
                    details.add(list2);
                }
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

    private Integer checkImages(String charityID) {
       if (charityID.equals("CHARITY001"))
           return images[0];
       else if (charityID.equals("CHARITY002"))
           return images[1];
       else if (charityID.equals("CHARITY003"))
           return images[2];
       else if (charityID.equals("CHARITY004"))
           return images[3];
       else if (charityID.equals("CHARITY005"))
           return images[4];
       else
           return 0;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.charity_listings, menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        if (mAuth.getCurrentUser()==null)
            // if (status2=="Logout")
            getMenuInflater().inflate(R.menu.charity_listings, menu);
        else
            getMenuInflater().inflate(R.menu.menu_customer_logged, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent i = new Intent(this, login_signup_home.class);
            startActivity(i);
        }
        else if (id == R.id.action_logout)
        { AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mAuth.signOut();
                            Toast.makeText(CharityListings.this,"Signed Out",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CharityListings.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();


        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.popularity){
            rows.clear();

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Charity charity = dataSnapshot1.getValue(Charity.class);
                        assert charity != null;

                        if (charity.getCharityID().equals("CHARITY004")) {

                            list =  "\n" + charity.toString();
                            rowPack desc = new rowPack(checkImages(charity.getCharityID()), list, null);
                            rows.add(desc);
                            list2 = charity.getCharityID();
                            details.add(list2);
                        }

                    }
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }

            });
        }
        else if (id == R.id.alpha) {
            final int[] Count = {1};
            rows.clear();
            details.clear();
            query = mDatabase.orderByChild("charityName");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Charity charity = dataSnapshot1.getValue(Charity.class);
                        assert charity != null;
                        list = "\nCharity " + (Count[0]++) +"\n"+ charity.toString();
                        rowPack desc = new rowPack(checkImages(charity.getCharityID()),list,null);
                        rows.add(desc);
                        list2 = charity.getCharityID() ;
                        details.add(list2);
                    }
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }
        else if (id == R.id.asc_location) {
            final int[] Count = {1};
            rows.clear();
            details.clear();
            query = mDatabase.orderByChild("charityLocation");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        Charity charity = dataSnapshot1.getValue(Charity.class);
                        assert charity != null;
                        list = "\nCharity " + (Count[0]++) +"\n"+ charity.toString();
                        rowPack desc = new rowPack(checkImages(charity.getCharityID()),list,null);
                        rows.add(desc);
                        list2 = charity.getCharityID() ;
                        details.add(list2);
                    }
                    listView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CharityListings.this);
        // set activity_prompt.xml to alert dialog builder
        alertDialogBuilder.setMessage(" \n1)Log In or Sign Up \n" +
                "2)Select On Spot / Big Event.\n" +
                "3)Fill in the Event Details & donate to the home." +
                "\n\nClick \"OK\"  to proceed.\n" +
                "        ~Happy Donating!~");
        alertDialogBuilder.setTitle("Instructions");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {


                // check if user is signed in then start dashboardactivity
                if(mAuth.getCurrentUser()!=null) {

                    db.updateCharityID(details.get(position));

//                    Log.e("masg", builder.toString());
                   Log.e("masg", details.get(position));

                    Intent intent=new Intent(CharityListings.this,CustomerLogged.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(CharityListings.this,"Select a package", Toast.LENGTH_SHORT).show();
                }else {
                    db.updateCharityID(details.get(position));
                    Intent i = new Intent(CharityListings.this, Login.class);
                    startActivity(i);
                    finish();
                }
                Log.e("ok","prompt");
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Log.e("cancel","prompt");
                dialog.cancel();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
}
