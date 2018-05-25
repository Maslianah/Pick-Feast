package my.maslianah.com.pickfeastfyp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import my.maslianah.com.pickfeastfyp.model.Charity;
import my.maslianah.com.pickfeastfyp.model.Customer;

public class Order1 extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    Button btnNext;
    RadioButton btnSame,btnDifferent;
    private DatabaseReference mDatabase,mDB,mDB2;
    EditText txtAddress;
    public final static String ORDER_1_ORDER_ID = "my.maslianah.com.pickfeastfyp.ORDER_1_ORDER_ID";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order1);
        db = new DatabaseHelper(this);

        btnNext = (Button) findViewById(R.id.bookNowButton);
        btnSame = (RadioButton) findViewById(R.id.rb_sameaddress);
        btnDifferent = (RadioButton) findViewById(R.id.rb_diffaddress);
        txtAddress = (EditText) findViewById(R.id.text_addressDesc);

        btnDifferent.setOnCheckedChangeListener(this);
        btnSame.setOnCheckedChangeListener(this);
        btnNext.setOnClickListener(this);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
       // mDatabase = FirebaseDatabase.getInstance().getReference().child("Packages");


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode== KeyEvent.KEYCODE_BACK){
            Toast.makeText(getApplicationContext(), "Please proceed with order OR exit the application",
                    Toast.LENGTH_LONG).show();
        }

        return false;
        // Disable back button..............
    }

    private boolean validateAddress() {
        boolean result = true;

        if (TextUtils.isEmpty(txtAddress.getText().toString())) {
            txtAddress.setError("Required");
            result = false;
        } else {
            txtAddress.setError(null);
        }

        return result;
    }
    @Override
    public void onClick(View v) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid(); // get current user's unique id.

        Intent i = getIntent();
        final int intValue = i.getIntExtra(Login.LOGIN_BUTTON_ID, 0);
        final int intValue1 = i.getIntExtra(Signup.SIGNUP_BUTTON_ID, 0);
        final String orderID;
        if (intValue == R.id.logIn) {
            orderID = i.getStringExtra(Login.LOGIN_ORDER_ID);
        }else if (intValue1 == R.id.signUp){
            orderID = i.getStringExtra(Signup.SIGN_UP_ORDER_ID);
        }else {
            orderID = i.getStringExtra(CatererDisplay.CATERER_DISPLAY_ORDER_ID);
        }

        Cursor res = db.getAllData();
        final StringBuffer bf = new StringBuffer();
        while (res.moveToNext())
        {
            bf.append(res.getString(1));
        }

        if (btnSame.isChecked()) {
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDB = FirebaseDatabase.getInstance().getReference().child("Customer");
            mDB.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Customer cust = dataSnapshot1.getValue(Customer.class);
                        String mGroupId = dataSnapshot1.getKey();
                        if (uid.equals(mGroupId)) {

                            if (bf.toString().equals("")) {
                                assert cust != null;
                                mDatabase.child("Orders").child(orderID).child("orderID").setValue(orderID);
                                mDatabase.child("Orders").child(orderID).child("eventAddress").setValue(cust.getCustAddress());
                            }else {

                                mDB2 = FirebaseDatabase.getInstance().getReference().child("Charity");
                                mDB2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                            Charity charity = dataSnapshot1.getValue(Charity.class);

                                            assert charity != null;

                                               // Log.e("bf", bf.toString());
                                                if (bf.toString().equals(charity.getCharityID())) {

                                                    mDatabase.child("Orders").child(orderID).child("orderID").setValue(orderID);
                                                    mDatabase.child("Orders").child(orderID).child("charityID").setValue(charity.getCharityID());
                                                    mDatabase.child("Orders").child(orderID).child("eventAddress").setValue(charity.getCharityAddress());
                                                }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        // [START_EXCLUDE]
                                        System.out.println("The read failed: " + databaseError.getMessage());
                                    }
                                });
                            }
                            //checkCharityID(bf.toString(),orderID,cust.getCustAddress());
//                            assert cust != null;
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });
        }else if (btnDifferent.isChecked()){

            if (!validateAddress()) {
                return;
            }
            mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (bf.toString().equals("")) {

                        mDatabase.child("Orders").child(orderID).child("orderID").setValue(orderID);
                        mDatabase.child("Orders").child(orderID).child("eventAddress").setValue(txtAddress.getText().toString());
                    }else {

                        mDatabase.child("Orders").child(orderID).child("orderID").setValue(orderID);
                        mDatabase.child("Orders").child(orderID).child("charityID").child(bf.toString());
                        mDatabase.child("Orders").child(orderID).child("eventAddress").setValue(txtAddress.getText().toString());
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });

        }

        db.updateCharityID("");
        Intent intent = new Intent(Order1.this, Order2.class);
        intent.putExtra(ORDER_1_ORDER_ID,orderID);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}