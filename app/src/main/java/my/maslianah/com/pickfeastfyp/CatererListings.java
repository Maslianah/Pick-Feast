package my.maslianah.com.pickfeastfyp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

import my.maslianah.com.pickfeastfyp.model.Caterer;
import my.maslianah.com.pickfeastfyp.model.Packages;
import my.maslianah.com.pickfeastfyp.model.rowPack;

public class CatererListings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    public final static String CATERER_LISTINGS_PACKAGE_DETAILS = "my.maslianah.com.pickfeastfyp.CATERER_LISTINGS_PACKAGE_DETAILS";

    private DatabaseReference mDatabase,mAddOnDatabase,mHalal,mLocation;
    Context context = CatererListings.this;
    private ListView listView;
    private ArrayList<String> arrayListPack = new ArrayList<String>();
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> detailList = new ArrayList<>();
    private ArrayList<String> arrayList1 = new ArrayList<String>();
    private ArrayList<String> arrayList2 = new ArrayList<String>();
    private ArrayList<String> arrayList3 = new ArrayList<String>();
    private ArrayList<String> arrayListHalal = new ArrayList<String>();
    private ArrayList<String> detailList2 = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter,arrayAdapter2, arrayCopy, arrayHold, arrayAdapterV2, arrayPack,arrayHalal,arrayOccasion;

    String list = "",list2 = "", basic = "";
    CheckBox cbMalay,cbChinese,cbIndian,cbOthers,cbWestern,cbHalal,cbBuffet,cbBanquet,cbLive,cbPack,cbDome,cbParty, cbMeeting, cbWedding, cbOpenHouse, cbPrivateEvent;
    Query query;
    int count = 1;
    private int checkFunction;
    Boolean here, noCb;
    private FirebaseAuth mAuth;
    List<rowPack> rows,rows1,rows2,rowHalal,rows3;
    CustomAdapter adapter,adapter1,adapter2,adapterHalal,adapter3;
    DatabaseHelper db;
    public static final Integer[] images = { R.drawable.alanna, R.drawable.bestchef, R.drawable.bigonion, R.drawable.bigrajah,
            R.drawable.classiccaterers, R.drawable.felda, R.drawable.kenny, R.drawable.oncatering, R.drawable.tuktuk, R.drawable.wyk };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_listings);
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

        if (!isOnline()){
            Toast.makeText(getApplicationContext(), "No Internet Connection!\nCouldn't refresh list", Toast.LENGTH_SHORT).show();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Packages");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,detailList);
        arrayAdapterV2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,detailList2);
        arrayCopy = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList1);
        arrayHold = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList2);
        arrayPack = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListPack);
        arrayHalal = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListHalal);
        arrayOccasion = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList3);

        rows = new ArrayList<rowPack>();
        rows1 = new ArrayList<rowPack>();
        rows2 = new ArrayList<rowPack>();
        rows3 = new ArrayList<rowPack>();
        rowHalal = new ArrayList<rowPack>();

        listView = (ListView)findViewById(R.id.lv);
        listView.setOnItemClickListener(this);
        adapter = new CustomAdapter(getApplicationContext(), R.layout.activity_row_listing, rows);
        adapter1 = new CustomAdapter(getApplicationContext(), R.layout.activity_row_listing, rows1);
        adapter2 = new CustomAdapter(getApplicationContext(), R.layout.activity_row_listing, rows2);
        adapter3 = new CustomAdapter(getApplicationContext(), R.layout.activity_row_listing, rows3);
        adapterHalal = new CustomAdapter(getApplicationContext(), R.layout.activity_row_listing, rowHalal);

        Intent mIntent = getIntent();
        final int intValue = mIntent.getIntExtra(MainActivity.MAIN_ACTIVITY_BUTTON_ID, 0);
        arrayListPack.clear();

        displayPackages(intValue);
    }
    private void displayPackages(final int intValue) {

        //list packages in list view
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Packages pack = postSnapshot.getValue(Packages.class);
                    if(intValue == 0) {
                        // error handling (Will come in this if when button id is invalid)
                    }
                    else
                    {
                        if(intValue == R.id.buttonOnSpot) {
                            db.updateTheme(1);
                            if (pack.getPackOnSpot() == true){
                                basic = pack.getPackID();
                                arrayPack.add(basic);
                                list = "Package " + (count++) +"\n"+ pack.toStringFirst();
                                list2 = "Package " + pack.toStringSecond();
                                arrayAdapter.add(list);
                                //arrayAdapter2.add(list2);

                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                rows.add(desc);
                            }
                        }
                        if(intValue == R.id.buttonBigEvent) {
                            db.updateTheme(2);
                            if (pack.getPackBigEvent() == true){
                                basic = pack.getPackID();
                                arrayPack.add(basic);
                                list = "Package " + (count++) +"\n"+ pack.toStringFirst();
                                list2 = "\nPackage " + pack.toStringSecond();
                                arrayAdapter.add(list);
                                //arrayAdapter2.add(list2);

                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                rows.add(desc);
                            }
                        }
                    }

                    here = false;
                }

                listView.setAdapter(adapter);

                //set button id to be used in Order2
                Cursor res = db.getAllData();
                StringBuffer bf = new StringBuffer();
                while (res.moveToNext())
                {
                    bf.append(res.getString(2));
                }

               // Log.e("msg", bf.toString());

                // listView.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }
    private Integer checkImages(String catererID) {
        if (catererID.equals("CAT006"))
            return images[0];
        else if (catererID.equals("CAT007"))
            return images[1];
        else if (catererID.equals("CAT003"))
            return images[2];
        else if (catererID.equals("CAT004"))
            return images[3];
        else if (catererID.equals("CAT009"))
            return images[4];
        else if (catererID.equals("CAT008"))
            return images[5];
        else if (catererID.equals("CAT010"))
            return images[6];
        else if (catererID.equals("CAT005"))
            return images[7];
        else if (catererID.equals("CAT001"))
            return images[8];
        else if (catererID.equals("CAT002"))
            return images[9];
        else
            return 0;
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.caterer_listings, menu);
        mAuth = FirebaseAuth.getInstance();

        // Inflate the menu; this adds items to the action bar if it is present.
        if (mAuth.getCurrentUser()==null)
            // if (status2=="Logout")
            getMenuInflater().inflate(R.menu.caterer_listings, menu);
        else
            getMenuInflater().inflate(R.menu.caterer_listings_logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.account) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            // Toast.makeText(CatererListings.this, "Feature not implemented", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.popularity){
            rows.clear();
            arrayList.clear();

            if (!arrayList3.isEmpty()){
                Log.e("msg", "3");

                query = mDatabase.orderByChild("count");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);

                            for (int i = 0; i < arrayList3.size(); i++) {
                                assert pack != null;
                                if (arrayList3.get(i).equals(pack.toStringFirst())) {
                                    Log.e("msg", "arrayList1");
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                    rows.add(desc);

                                }
                            }
                            Collections.reverse(rows);
                            //arrayAdapter2.add(list2);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }

                });}

            else if (!arrayList2.isEmpty()){
                Log.e("msg", "2");
                query = mDatabase.orderByChild("count");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);

                            for (int i = 0; i < arrayList2.size(); i++) {
                                assert pack != null;
                                if (arrayList2.get(i).equals(pack.toStringFirst())) {
                                    Log.e("msg", "arrayList1");
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                    rows.add(desc);

                                }
                            }
                            Collections.reverse(rows);
                            //arrayAdapter2.add(list2);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }

                });}

            else if (!arrayList1.isEmpty()){

                Log.e("msg", "1");
                query = mDatabase.orderByChild("count");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);

                            for (int i = 0; i < arrayList1.size(); i++) {
                                assert pack != null;
                                if (arrayList1.get(i).equals(pack.toStringFirst())) {
                                    Log.e("msg", "arrayList1");
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                    rows.add(desc);

                                }
                            }
                            Collections.reverse(rows);
                            //arrayAdapter2.add(list2);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }

                });}
            else if (!arrayListHalal.isEmpty()){
                Log.e("msg", "h");
                query = mDatabase.orderByChild("count");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);

                            for (int i = 0; i < arrayListHalal.size(); i++) {
                                assert pack != null;
                                if (arrayListHalal.get(i).equals(pack.toStringFirst())) {
                                    Log.e("msg", "arrayList1");
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                    rows.add(desc);

                                }
                            }
                            Collections.reverse(rows);
                            //arrayAdapter2.add(list2);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }

                });}


            else{
                Log.e("msg", "n");
                query = mDatabase.orderByChild("count");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i = 0; i < arrayListPack.size(); i++) {
                                assert pack != null;
                                if (arrayListPack.get(i).equals(pack.getPackID())) {

                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                    rows.add(desc);
                                }
                            }
                            Collections.reverse(rows);
                            //arrayAdapter2.add(list2);
                        }
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }

                });}

        }


        else if (id == R.id.asc_price){
            arrayList.clear();
            rows.clear();
            detailList.clear();

            if (!arrayList3.isEmpty())
            {
                Log.e("msg", "aarayList2 asc price");
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList3.size(); i++) {
                                assert pack != null;
                                if (arrayList3.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayList2.isEmpty())
            {
                Log.e("msg", "aarayList2 asc price");
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList2.size(); i++) {
                                assert pack != null;
                                if (arrayList2.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayList1.isEmpty())
            {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList1.size(); i++) {
                                assert pack != null;
                                if (arrayList1.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayListHalal.isEmpty())
            {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayListHalal.size(); i++) {
                                assert pack != null;
                                if (arrayListHalal.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        listView.setAdapter(adapter);
                        Log.e("price","asc else");

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
            else {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayListPack.size(); i++) {
                                assert pack != null;
                                if (arrayListPack.get(i).equals(pack.getPackID())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                   // arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                    Log.e("price","asc else");
                                }
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
        }

        else if (id == R.id.desc_price){
            arrayList.clear();
            detailList.clear();
            rows.clear();

            if (!arrayList3.isEmpty())
            {
                Log.e("msg", "aarayList2 asc price");
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList3.size(); i++) {
                                assert pack != null;
                                if (arrayList3.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                  //  arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        Collections.reverse(rows);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }

            else if (!arrayList2.isEmpty())
            {
                Log.e("msg", "aarayList2 asc price");
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList2.size(); i++) {
                                assert pack != null;
                                if (arrayList2.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //  arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        Collections.reverse(rows);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
            else if (!arrayList1.isEmpty())
            {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList1.size(); i++) {
                                assert pack != null;
                                if (arrayList1.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        Collections.reverse(rows);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
            else if (!arrayListHalal.isEmpty())
            {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayListHalal.size(); i++) {
                                assert pack != null;
                                if (arrayListHalal.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        Collections.reverse(rows);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
            else {
                query = mDatabase.orderByChild("packPrice");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayListPack.size(); i++) {
                                assert pack != null;
                                if (arrayListPack.get(i).equals(pack.getPackID())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                        }
                        Collections.reverse(rows);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });
            }
        }
        //DONE
        else if (id == R.id.asc_location){
            arrayList.clear();
            rows.clear();
            detailList.clear();

            if (!arrayList3.isEmpty())
            {
                query = mDatabase.orderByChild("packLocation");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList3.size(); i++) {
                                assert pack != null;
                                if (arrayList3.get(i).equals(pack.toStringFirst()) ) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    //arrayAdapter.add(list);
                                    arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayList2.isEmpty())
            {
                query = mDatabase.orderByChild("packLocation");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList2.size(); i++) {
                                assert pack != null;
                                if (arrayList2.get(i).equals(pack.toStringFirst()) ) {
                                    list = "Package " + "\n" + pack.toStringFirst() ;
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    //arrayAdapter.add(list);
                                    arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayList1.isEmpty())
            {
                query = mDatabase.orderByChild("packLocation");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayList1.size(); i++) {
                                assert pack != null;
                                if (arrayList1.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else if (!arrayListHalal.isEmpty())
            {
                query = mDatabase.orderByChild("packLocation");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i=0; i<arrayListHalal.size(); i++) {
                                assert pack != null;
                                if (arrayListHalal.get(i).equals(pack.toStringFirst())) {
                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);
                                    //arrayAdapter2.add(list2);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
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
            else {
                query = mDatabase.orderByChild("packLocation");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            for (int i = 0; i<arrayListPack.size(); i++) {
                                assert pack != null;
                                if (arrayListPack.get(i).equals(pack.getPackID())) {

                                    list = "Package " + "\n" + pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                }
                            }
                            //arrayAdapter2.add(list2);


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
        }

        else if (id == R.id.logout){
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mAuth.signOut();
                            Intent intent = new Intent(CatererListings.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(CatererListings.this,"Signed Out",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            android.support.v7.app.AlertDialog alert = builder.create();
            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void checkFilter(View view) {

        arrayListHalal.clear();
        rowHalal.clear();
        arrayList.clear();
        rows.clear();
        noCb = false;
        here = false;
        arrayList2.clear();
        rows1.clear();
        arrayList3.clear();
        rows2.clear();
        rows3.clear();
        arrayList1.clear();
        detailList.clear();
        detailList2.clear();
        checkFunction = 0;

        cbHalal= (CheckBox) findViewById(R.id.halal);
        if (cbHalal.isChecked()) {
            checkHalal();
        }


        cbBanquet = (CheckBox) findViewById(R.id.banquet);
        cbBuffet = (CheckBox) findViewById(R.id.buffet);
        cbLive = (CheckBox) findViewById(R.id.livestation);
        cbPack = (CheckBox) findViewById(R.id.packed);
        cbDome = (CheckBox) findViewById(R.id.dome);
        checkStyle();
        cbMalay = (CheckBox) findViewById(R.id.malay);
        cbChinese = (CheckBox) findViewById(R.id.chinese);
        cbIndian = (CheckBox) findViewById(R.id.indian);
        cbWestern = (CheckBox) findViewById(R.id.western);
        cbOthers = (CheckBox) findViewById(R.id.others);
        checkCuisine();
        cbMeeting = (CheckBox) findViewById(R.id.meeting);
        cbParty = (CheckBox) findViewById(R.id.party);
        cbWedding = (CheckBox) findViewById(R.id.wedding);
        cbOpenHouse = (CheckBox) findViewById(R.id.openhouse);
        cbPrivateEvent = (CheckBox) findViewById(R.id.privatevent);
        checkOccasions();


        if (!arrayList3.isEmpty())
        {
            listView.setAdapter(arrayOccasion);
            Log.e("msg", "here arrayOccasion");
        }
        else if (!noCb) {
            //halal checkbox here
            if (cbHalal.isChecked()) {
                //listView.setAdapter(adapter);
                listView.setAdapter(adapterHalal);
                Log.e("msg", "here arrayHalal");
            } else {
                count = 1;
                mLocation = FirebaseDatabase.getInstance().getReference().child("Packages");
                mLocation.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Packages pack = dataSnapshot1.getValue(Packages.class);
                            assert pack != null;

                            for (int i = 0; i < arrayListPack.size(); i++) {
                                if (arrayListPack.get(i).equals(pack.getPackID())) {
                                    //arrayAdapter.add(list);
                                    list = "\nPackage " + (count++) +"\n"+ pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter2.add(list2);
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                    //Log.e("d",rows.toString());

                                }
                            }
                        }
                        listView.setAdapter(adapter);
                    }

                    public void onCancelled(DatabaseError databaseError) {
                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });

                Log.e("msg", "here arrayAdapter");
                //listView.setAdapter(arrayAdapter);

            }
        } else {
            if (here) {
                //listView.setAdapter(adapter2);
                listView.setAdapter(arrayHold);
                Log.e("msg", "here arrayHold");
            } else {
                listView.setAdapter(arrayCopy);
                //listView.setAdapter(adapter1);
                Log.e("msg", "here arrayCopy");
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private CustomAdapter checkHalal() {

        arrayList.clear();
        rows.clear();

        mHalal = FirebaseDatabase.getInstance().getReference().child("Caterers");
        mHalal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Caterer caterer = dataSnapshot1.getValue(Caterer.class);
                    assert caterer != null;
                    if (caterer.getHalalReg().contains("Non-Halal")) {
                        list = caterer.getCatID();
                        arrayAdapter.add(list);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        mLocation = FirebaseDatabase.getInstance().getReference().child("Packages");
        mLocation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Packages pc = dataSnapshot1.getValue(Packages.class);
                    assert pc != null;
                    list = pc.toStringFirst();
                    for (int a = 0; a < arrayListPack.size(); a++) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).equals(pc.getCatID()) && arrayListPack.get(a).equals(pc.getPackID())) {
                                //arrayAdapter.add(list);
                                list2 = "\nPackage " + pc.toStringSecond();
                                arrayAdapter2.add(list2);
                                arrayHalal.add(list);

                                rowPack desc = new rowPack(checkImages(pc.getCatID()),list,list2);
                                rowHalal.add(desc);
                                //Log.e("d",rowHalal.toString());

                            }
                        }
                    }
                }
                listView.setAdapter(adapterHalal);
            }

            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        //return arrayHalal;
        return adapterHalal;

    }

    //JUST DONE
    private boolean checkStyle() {

        if (cbBanquet.isChecked()) {
            checkStylePackage("Banquet");
            noCb = true;
        }
        if (cbBuffet.isChecked()) {
            //listView.setAdapter(checkStylePackage("Buffet"));
            checkStylePackage("Buffet");
            noCb = true;
        }
        if (cbLive.isChecked()) {
            checkStylePackage("Live Station");
            noCb = true;
            //listView.setAdapter(checkStylePackage("Live Station"));
        }

        if (cbPack.isChecked()) {
            checkStylePackage("Pack");
            noCb = true;
            ///listView.setAdapter(checkStylePackage("Pack"));
        }

        if (cbDome.isChecked()) {
            checkStylePackage("Dome");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }

        if (cbBanquet.isChecked() || cbBuffet.isChecked() || cbDome.isChecked() || cbPack.isChecked() || cbLive.isChecked()) {
            checkFunction = 1;
            return true;
        } else {
            return false;
        }
    }

    //JUST DONE
    private boolean checkOccasions() {

        if (cbMeeting.isChecked()) {
            checkOccasionPackage("Meeting");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if (cbParty.isChecked()) {
            checkOccasionPackage("Party");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }

        if (cbWedding.isChecked()) {
            checkOccasionPackage("Wedding");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if (cbOpenHouse.isChecked()) {
            checkOccasionPackage("Open House");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if (cbPrivateEvent.isChecked()) {
            checkOccasionPackage("Private");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if (noCb == true) {
            return true;
        } else
            return false;
    }

    private boolean checkCuisine() {
        if (cbMalay.isChecked()) {
            here = checkCuisinePackage("Malay");
            noCb = true;
        }
        if (cbChinese.isChecked()) {
            here = checkCuisinePackage("Chinese");
            noCb = true;
        }
        if (cbIndian.isChecked()) {
            //   checkStylePackage("Party");
            here = checkCuisinePackage("Indian");
            noCb = true;

        }
        if (cbWestern.isChecked()) {
            here = checkCuisinePackage("Western");
            noCb = true;

        }
        if (cbOthers.isChecked()) {
            here = checkCuisinePackage("Others");
            noCb = true;

        }

        if (noCb == true) {
            checkFunction=1;
            return true;
        } else
            return false;
        //return here;
    }

    //Done, please do not touch
    private CustomAdapter checkStylePackage(final String style) {
        arrayList.clear();
        rows.clear();


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for no check cbhalal
                if (!cbHalal.isChecked()) {

                    if (!arrayList1.isEmpty()) { //to avoid buffet and banquet has the same package
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                if ((pack.getPackDescription()).contains(style)) {
                                    list = pack.toStringFirst();
                                    //union, if intercept skip (means this is more the same style)
                                    for (int i = 0; i < arrayList1.size(); i++) {
                                        if (arrayList1.get(i).equals(list) && arrayListPack.get(a).equals(pack.getPackID()))
                                            break;
                                        else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                            if (i != arrayList1.size() - 1)
                                                continue;
                                            else {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapter2.add(list2);
                                                arrayCopy.add(list);
                                                arrayAdapter.add(list);
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows.add(desc);
                                                rows1.add(desc);
                                                break;
                                            }
                                        }

                                    }
                                }
                            }
                             listView.setAdapter(adapter1);
                        }
                        //if empty, add as usual. no intercept
                    } else {
                        for (int b = 0; b < arrayListPack.size(); b++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                //if it's in the selected style, and in the pack list. and halal right?
                                if ((pack.getPackDescription()).contains(style) && arrayListPack.get(b).equals(pack.getPackID())) {

                                    list = pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter2.add(list2);
                                    arrayCopy.add(list);
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                    rows1.add(desc);
                                }
                            }
                        }
                         listView.setAdapter(adapter1);
                    }
                }
                // check intercept with halal
                else {
                    //if empty
                    if (arrayList1.isEmpty()) {
                        for (int a = 0; a < arrayListHalal.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                if (arrayListHalal.get(a).equals(list) && pack.getPackDescription().contains(style)) {
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapterV2.add(list2);
                                    arrayCopy.add(list);
                                    arrayAdapter.add(list);

                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows.add(desc);
                                    rows1.add(desc);
                                    break;
                                }
                            }
                        }
                         listView.setAdapter(adapter1);
                    }
                    // if dome has been selected, dome needs to be removed in intercerpt
                    else {
                        for (int a = 0; a < arrayListHalal.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                for (int b = 0; b < arrayList1.size(); b++) {

                                    if (arrayListHalal.get(a).equals(list) && arrayList1.get(b).equals(list)) {
                                        break;
                                    } else if (arrayListHalal.get(a).equals(list)) {
                                        if (b != arrayList1.size() - 1)
                                            continue;
                                        else {
                                            list2 = "\nPackage " + pack.toStringSecond();
                                            arrayAdapterV2.add(list2);
                                            arrayCopy.add(list);
                                            arrayAdapter.add(list);

                                            rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                            rows.add(desc);
                                            rows1.add(desc);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                         listView.setAdapter(adapter1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        //return arrayAdapter;
        return adapter;
    }

    private Boolean checkCuisinePackage(final String type) {
        arrayList.clear();
        rows.clear();
        detailList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //   arrayCopy not empty
                //check without no halal
                if (!cbHalal.isChecked()) {
                    //union of style // can buang?
                    if (!arrayList1.isEmpty()) {
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                assert pack != null;
                                list = pack.toStringFirst();

                                if ((pack.getPackCuisine()).contains(type) && arrayListPack.get(a).equals(pack.getPackID())) {

                                    //INTERCEPTS
                                    //if (checkFunction == 1) {
                                        Log.e("msg", "1");
                                        for (int i = 0; i < arrayList1.size(); i++) {
                                            if (arrayList1.get(i).equals(list)) {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                arrayHold.add(list);
                                                arrayAdapter.add(list);

                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows.add(desc);
                                                rows2.add(desc);
                                                break;
                                            }
                                        }
                                        //listView.setAdapter(adapter2);

                                    //}
                                    //UNION
                                    /*else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                        Log.e("msg", "2");
                                        for (int i = 0; i < arrayList1.size(); i++) {

                                            if (arrayList1.get(i).equals(list))
                                                break;
                                            else {
                                                if (i != arrayList1.size() - 1)
                                                    continue;
                                                else {
                                                    list2 = "\nPackage " + pack.toStringSecond();
                                                    arrayAdapterV2.add(list2);
                                                    arrayHold.add(list);
                                                    arrayAdapter.add(list);

                                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                    rows.add(desc);
                                                    rows2.add(desc);
                                                    break;
                                                }
                                            }

                                        }
                                       // listView.setAdapter(adapter2);
                                    }*/
                                    //arrayCopy IS empty
                                    listView.setAdapter(adapter2);
                                }

                            }
                        }
                    }
                    //no halal
                    //empty list (no style)
                  //  check here
                   else {
                        Log.e("msg", "3");
                        //from style
                        if (cbBanquet.isChecked() || cbBuffet.isChecked() || cbDome.isChecked() || cbLive.isChecked() || cbPack.isChecked()) {
                            Log.e("msg2", "4");
                            /*for (int b = 0; b < arrayListPack.size(); b++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    //add it like usual man, no style, no intercept or union
                                    assert pack != null;
                                    if ((pack.getPackCuisine()).contains(type) && arrayListPack.get(b).equals(pack.getPackID())) {

                                        list = pack.toStringFirst();
                                        list2 = "\nPackage " + pack.toStringSecond();
                                        arrayAdapterV2.add(list2);
                                        arrayHold.add(list);
                                        arrayAdapter.add(list);

                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                        rows.add(desc);
                                        rows2.add(desc);
                                       // Log.e("msg",rows2.toString());

                                    }

                                }
                            }
                            */listView.setAdapter(adapter2);
                        } //check this one to display cuisine alone
                        else{
                            Log.e("msg2", "5");
                            for (int b = 0; b < arrayListPack.size(); b++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    Packages pack = dataSnapshot1.getValue(Packages.class);

                                    //add it like usual man, no style, no intercept or union
                                    assert pack != null;
                                    if ((pack.getPackCuisine()).contains(type) && arrayListPack.get(b).equals(pack.getPackID())) {
                                        Log.e("msg3", "6");
//                                    }
//                                    else{
                                        list = pack.toStringFirst();
                                        list2 = "\nPackage " + pack.toStringSecond();
                                        arrayAdapterV2.add(list2);
                                        arrayHold.add(list);
                                        arrayAdapter.add(list);
                                        Log.e("msg4", "if not intercept with style");
                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                        rows.add(desc);
                                        rows2.add(desc);
                                       // Log.e("msg",rows2.toString());
                                    }

                                }
                            }
                            listView.setAdapter(adapter2);
                        }
                        //listView.setAdapter(adapter2);
                    }
                }
                //is check arrayListhalal
                else {
                    //this is done, dont touch
                    //intercept between halal no style
                    if (!arrayList1.isEmpty())
                    //
                    //intercept between halal and style //dah ada halal and style

                    {
                        Log.e("msg", "halal check");
                        //if (checkFunction == 1) {
                            Log.e("msg", "7");
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                assert pack != null;
                                list = pack.toStringFirst();
                                for (int b = 0; b < arrayList1.size(); b++) {

                                    if (pack.getPackCuisine().contains(type) && arrayList1.get(b).equals(list)) {
                                        list2 = "\nPackage " + pack.toStringSecond();
                                        arrayAdapterV2.add(list2);
                                        arrayHold.add(list);
                                        arrayAdapter.add(list);

                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                        rows.add(desc);
                                        rows2.add(desc);
                                        break;
                                    }
                                }
                            }
                            listView.setAdapter(adapter2);
                      //  }
                        //if not function 1, means function 0, intercept between halal and cuisine
                        //done dont touch
                        /*else {
                            Log.e("msg", "8");
                            for (int a = 0; a < arrayListHalal.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    assert pack != null;
                                    list = pack.toStringFirst();
                                    for (int b = 0; b < arrayList1.size(); b++) {

                                        if (arrayListHalal.get(a).equals(list) && arrayList1.get(b).equals(list)) {
                                            break;
                                        } else if (arrayListHalal.get(a).equals(list)) {
                                            if (b != arrayList1.size() - 1)
                                                continue;
                                            else {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                arrayHold.add(list);
                                                arrayAdapter.add(list);

                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows.add(desc);
                                                rows2.add(desc);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            listView.setAdapter(adapter2);
                        }*/
                    }


                    else{


                       /* if (!arrayList1.isEmpty())
                            Log.e("msg","hello");//*/
                        //intercept between halal and style //dah ada halal and style

                        {

                            if (cbBanquet.isChecked() || cbBuffet.isChecked() || cbDome.isChecked() || cbLive.isChecked() || cbPack.isChecked())
                            {
                                Log.e("msg", "9");
                                listView.setAdapter(adapter2);
                            }
                            else {
                                Log.e("msg", "10");

                                for (int a = 0; a < arrayListHalal.size(); a++) {
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        Packages pack = dataSnapshot1.getValue(Packages.class);
                                        assert pack != null;
                                        list = pack.toStringFirst();
                                        if (arrayListHalal.get(a).equals(list) && pack.getPackCuisine().contains(type)) {
                                            list2 = "\nPackage " + pack.toStringSecond();
                                            arrayAdapterV2.add(list2);
                                            arrayHold.add(list);

                                            rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                            rows.add(desc);
                                            rows2.add(desc);
                                            break;
                                        }
                                    }
                                }
                                listView.setAdapter(adapter2);
                            }
                        }
                    }

                }


                // onSpotList.size
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        return true;
    }

    private CustomAdapter checkOccasionPackage(final String occasion) {
        arrayList.clear();
        rows.clear();
        detailList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //   arrayCopy not empty
                //check without no halal
                if (!cbHalal.isChecked()) {
                    //no halal, yes result from cuisine
                    if (!arrayList2.isEmpty()) {
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                assert pack != null;
                                list = pack.toStringFirst();
                                if ((pack.getPackDescription()).contains(occasion) && arrayListPack.get(a).equals(pack.getPackID())) {
                                    //INTERCEPTS
                                    //  if (checkFunction == 1) {//
                                    for (int i = 0; i < arrayList2.size(); i++) {

                                        if (arrayList2.get(i).equals(list)) {
                                            list2 = "\nPackage " + pack.toStringSecond();
                                            arrayAdapterV2.add(list2);
                                            arrayOccasion.add(list);
                                            arrayAdapter.add(list);
                                            Log.e("msg1", "occasion");
                                            rowPack desc = new rowPack(checkImages(pack.getCatID()), list, list2);
                                            rows.add(desc);
                                            rows3.add(desc);
                                            break;
                                        }
                                    }
                                    listView.setAdapter(adapter3);
                                }
                                    /*//UNION
                                    else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                        for (int i = 0; i < arrayList2.size(); i++) {
                                            if (arrayList2.get(i).equals(list))
                                                break;
                                            else {
                                                if (i != arrayList2.size() - 1)
                                                    continue;
                                                else {
                                                    list2 = "\nPackage " + pack.toStringSecond();
                                                    arrayAdapterV2.add(list2);
                                                    arrayOccasion.add(list);
                                                    arrayAdapter.add(list);
                                                    Log.e("msg2","occasion");
                                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                    rows.add(desc);
                                                    rows3.add(desc);
                                                    break;
                                                }
                                            }
                                        }
                                     //   listView.setAdapter(adapter3);
                                    }*/
                                //arrayCopy IS empty
                            }
                        }
                        listView.setAdapter(adapter3);
                    }
                    else if (!arrayList1.isEmpty()) {
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                assert pack != null;
                                list = pack.toStringFirst();
                                if ((pack.getPackDescription()).contains(occasion) && arrayListPack.get(a).equals(pack.getPackID())) {

                                    //INTERCEPTS
                                  //  if (checkFunction == 1) {
                                        for (int i = 0; i < arrayList1.size(); i++) {

                                            if (arrayList1.get(i).equals(list)) {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                arrayOccasion.add(list);
                                                arrayAdapter.add(list);
                                                Log.e("msg3","occasion");
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows.add(desc);
                                                rows3.add(desc);
                                                break;
                                            }
                                        }
                                 //   }
                                    //UNION
                                    /*else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                        for (int i = 0; i < arrayList1.size(); i++) {

                                            if (arrayList1.get(i).equals(list))
                                                break;
                                            else {
                                                if (i != arrayList1.size() - 1)
                                                    continue;
                                                else {
                                                    list2 = "\nPackage " + pack.toStringSecond();
                                                    arrayAdapterV2.add(list2);
                                                    arrayOccasion.add(list);
                                                    arrayAdapter.add(list);
                                                    Log.e("msg4","occasion");
                                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                    rows.add(desc);
                                                    rows3.add(desc);
                                                    break;
                                                }
                                            }

                                        }
                                    //    listView.setAdapter(adapter3);
                                    }*/
                                    //arrayCopy IS empty
                                }
                                listView.setAdapter(adapter3);
                            }
                        }
                    }
                    //empty list (no style)
                    else {
                        if (cbBanquet.isChecked() || cbBuffet.isChecked() || cbDome.isChecked() || cbLive.isChecked() || cbPack.isChecked() ||
                                cbMalay.isChecked() || cbIndian.isChecked() || cbChinese.isChecked() || cbWestern.isChecked()|| cbOthers.isChecked()) {
                                listView.setAdapter(adapter3);
                            }

                          else if (!arrayList3.isEmpty()) { //to avoid buffet and banquet has the same package
                            Log.e("msg","occasion not empt");
                            for (int a = 0; a < arrayListPack.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    if ((pack.getPackDescription()).contains(occasion)) {
                                        list = pack.toStringFirst();
                                        //union, if intercept skip (means this is more the same style)
                                        int k = arrayList3.size();
                                        for (int i = 0; i < k; i++) {
                                            if (arrayList3.get(i).equals(list) && arrayListPack.get(a).equals(pack.getPackID()))
                                                break;
                                            else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                                if (i != k - 1)
                                                    continue;
                                                else {
                                                    list2 = "\nPackage " + pack.toStringSecond();
                                                    arrayAdapter2.add(list2);
                                                    arrayOccasion.add(list);
                                                    arrayAdapter.add(list);
                                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                    rows.add(desc);
                                                    rows3.add(desc);
                                                    break;
                                                }
                                            }

                                        }
                                    }
                                }
                                listView.setAdapter(adapter3);
                            }
                            //if empty, add as usual. no intercept
                        } else {
                            Log.e("msg","occasion empt");
                            for (int b = 0; b < arrayListPack.size(); b++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    //if it's in the selected style, and in the pack list. and halal right?
                                    if ((pack.getPackDescription()).contains(occasion) && arrayListPack.get(b).equals(pack.getPackID())) {

                                        list = pack.toStringFirst();
                                        list2 = "\nPackage " + pack.toStringSecond();
                                        arrayAdapter2.add(list2);
                                        arrayOccasion.add(list);
                                        arrayAdapter.add(list);

                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                        rows.add(desc);
                                        rows3.add(desc);
                                    }
                                }
                            }
                            listView.setAdapter(adapter3);
                        }

                      }//here last
                }
                //is check arrayListhalal
                else
                {
                    //intercept between halal and style

                        if (!arrayList2.isEmpty()) {
                            for (int a = 0; a < arrayListPack.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    assert pack != null;
                                    list = pack.toStringFirst();
                                    if ((pack.getPackDescription()).contains(occasion) && arrayListPack.get(a).equals(pack.getPackID())) {

                                        //INTERCEPTS
                                            for (int i = 0; i < arrayList2.size(); i++) {
                                                for (int b=0; b<arrayListHalal.size();b++){
                                                    Log.e("halal and chinese", "haih");

                                                    if (arrayList2.get(i).equals(list) && arrayListHalal.get(b).equals(list)) {
                                                        list2 = "\nPackage " + pack.toStringSecond();
                                                        arrayAdapterV2.add(list2);
                                                        arrayOccasion.add(list);
                                                        arrayAdapter.add(list);
                                                        Log.e("msg7","occasion");
                                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                        rows.add(desc);
                                                        rows3.add(desc);
                                                        break;
                                                    }
                                                }
                                            }
                                            listView.setAdapter(adapter3);/*
                                        //UNION
                                        else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                            for (int i = 0; i < arrayList2.size(); i++) {
                                                for (int b = 0; b<arrayListHalal.size();b++) {
                                                    if (arrayList2.get(i).equals(list) && arrayListHalal.get(b).equals(list))
                                                        break;
                                                    else if (arrayListPack.get(b).equals(list)){
                                                        if (i != arrayList2.size() - 1)
                                                            continue;
                                                        else {
                                                            list2 = "\nPackage " + pack.toStringSecond();
                                                            arrayAdapterV2.add(list2);
                                                            arrayOccasion.add(list);
                                                            arrayAdapter.add(list);
                                                            Log.e("msg8","occasion");
                                                            rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                            rows.add(desc);
                                                            rows3.add(desc);
                                                            break;
                                                        }
                                                    }Log.e("halal and chinese", "hhhh");
                                                }

                                            }
                                            checkFunction = 0;
                                            listView.setAdapter(adapter3);
                                        }*/
                                        //arrayCopy IS empty
                                    }
                                }
                            }
                        }
                        else if (!arrayList1.isEmpty()) {
                            for (int a = 0; a < arrayListPack.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    list = pack.toStringFirst();
                                    if ((pack.getPackDescription()).contains(occasion) && arrayListPack.get(a).equals(pack.getPackID())) {

                                        //INTERCEPTS
                                        //if (checkFunction == 1) {
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                for (int b=0; b<arrayListHalal.size();b++){
                                                    Log.e("halal and chinese", "haih");

                                                    if (arrayList1.get(i).equals(list) && arrayListHalal.get(b).equals(list)) {
                                                        list2 = "\nPackage " + pack.toStringSecond();
                                                        arrayAdapterV2.add(list2);
                                                        arrayOccasion.add(list);
                                                        arrayAdapter.add(list);
                                                        Log.e("msg9","occasion");
                                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                        rows.add(desc);
                                                        rows3.add(desc);
                                                        break;
                                                    }
                                                }
                                            }

                                        //UNION
                                        /*else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                            for (int i = 0; i < arrayList1.size(); i++) {
                                                for (int b = 0; b<arrayListHalal.size();b++) {
                                                    if (arrayList1.get(i).equals(list) && arrayListHalal.get(b).equals(list))
                                                        break;
                                                    else if (arrayListPack.get(b).equals(list)){
                                                        if (i != arrayList1.size() - 1)
                                                            continue;
                                                        else {
                                                            list2 = "\nPackage " + pack.toStringSecond();
                                                            arrayAdapterV2.add(list2);
                                                            arrayOccasion.add(list);
                                                            arrayAdapter.add(list);
                                                            Log.e("msg10","occasion");
                                                            rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                            rows.add(desc);
                                                            rows3.add(desc);
                                                            break;
                                                        }
                                                    }Log.e("halal and chinese", "hhhh");
                                                }

                                            }
                                            checkFunction = 0;
                                        }*/
                                        //arrayCopy IS empty
                                    }
                                }
                            }
                            listView.setAdapter(adapter3);
                        }
                        else{
                            for (int a = 0; a < arrayListHalal.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    list = pack.toStringFirst();
                                    for (int b=0; b<arrayList1.size();b++){

                                        if (arrayListHalal.get(a).equals(list) && arrayList1.get(b).equals(list)) {
                                            break;
                                        }
                                        else if(arrayListHalal.get(a).equals(list))
                                        {
                                            if (b!=arrayList1.size()-1)
                                                continue;
                                            else {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                arrayOccasion.add(list);
                                                arrayAdapter.add(list);
                                                Log.e("msg11","occasion");
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows.add(desc);
                                                rows3.add(desc);
                                                break;
                                            }
                                        }Log.e("halal and chinese", "eeee");
                                    }
                                }
                            }listView.setAdapter(adapter3);
                        }


                }
                // onSpotList.size
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


        //return arrayOccasion;
        return adapter3;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
       // Log.e("masg", String.valueOf(arrayAdapter2.getItem(position)));
        //Log.e("masg", String.valueOf(parent.getAdapter().getItem(position)));
       // Log.e("masg", detailList.get(position));

        StringBuilder builder = new StringBuilder();
        if (here)
        {
            builder.append(detailList2.get(position));
        }
        else
        {
            builder.append(detailList.get(position));
        }

        Intent intent = new Intent(CatererListings.this,CatererDisplay.class);
        intent.putExtra(CATERER_LISTINGS_PACKAGE_DETAILS,builder.toString());


        startActivity(intent);

    }

}
