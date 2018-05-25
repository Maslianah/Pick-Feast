package my.maslianah.com.pickfeastfyp;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import my.maslianah.com.pickfeastfyp.model.Packages;

public class CatererDisplay extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    Button btnBook;
    public final static String CATERER_DISPLAY_BUTTON_ID = "my.maslianah.com.pickfeastfyp.CATERER_DISPLAY_BUTTON_ID";
    public final static String CATERER_DISPLAY_ORDER_ID = "my.maslianah.com.pickfeastfyp.CATERER_DISPLAY_ORDER_ID";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_display);
        db = new DatabaseHelper(this);
        btnBook = (Button)findViewById(R.id.bookNowButton) ;
        btnBook.setOnClickListener(this);

        Log.e("out","sU");
        Intent i = getIntent();
        TextView toView = findViewById(R.id.text_catererdesc);
        //String res2 = i.getStringExtra(CatererListings.CATERER_LISTINGS_PACKAGE_DETAILS);
        String res = i.getStringExtra(CustomAdapter.CUSTOM_ADAPTER_PACKAGE_DETAILS);
        toView.setText(res);// + res2);
        toView.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void onClick(final View v) {
        //write code in button.
        mAuth = FirebaseAuth.getInstance();

        Intent is = getIntent();
        final String res = is.getStringExtra(CustomAdapter.CUSTOM_ADAPTER_PACKAGE_DETAILS);
        //final String res2 = is.getStringExtra(CatererListings.CATERER_LISTINGS_PACKAGE_DETAILS);

        final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        final String orderID = mDatabase.push().getKey();


        DatabaseReference mPack = FirebaseDatabase.getInstance().getReference().child("Packages");
        mPack.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Packages pack = postSnapshot.getValue(Packages.class);
                    String mGroupId = postSnapshot.getKey();
                    if (res.contains(pack.getPackName())) {
                       // mDatabase.child("Orders").child(orderID).child("custID").setValue(uid);
                        mDatabase.child("Orders").child(orderID).child("packID").setValue(pack.getPackID());
                        db.updatePackID(pack.getPackID());

                                /* Cursor res = db.getAllData();
                            StringBuffer bf = new StringBuffer();
                            while (res.moveToNext())
                            {
                                bf.append(res.getString(0));
                            }

                            Log.e("msg", bf.toString());*/
                    }

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CONFIRMATION");
        builder.setMessage("1) Make sure the Package selected is the desired package\n" +
                           "2) Order Pax: 10-60 (On Spot) and >60 (Big Event)\n" +
                           "3) Order Date: Today (On Spot) and 4 days after today (Big Event)\n\n" +
                           "4) Click OK to make order confirmation");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mAuth.getCurrentUser()!=null) {
                    final FirebaseUser user = mAuth.getCurrentUser();
                    final String uid = user.getUid();

                    Intent intent=new Intent(CatererDisplay.this,Order1.class);
                    intent.putExtra(CATERER_DISPLAY_ORDER_ID,orderID);
                    startActivity(intent);
                    finish();
                    // Toast.makeText(Login.this, "Feature not implemented", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(CatererDisplay.this,Login.class);
                    intent.putExtra(CATERER_DISPLAY_BUTTON_ID, v.getId());
                    intent.putExtra(CATERER_DISPLAY_ORDER_ID,orderID);
                    startActivity(intent);
                    finish();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("cancel","prompt");
                dialog.cancel();
            }
        });

        AlertDialog alert11 = builder.create();
        alert11.show();


    }
}
