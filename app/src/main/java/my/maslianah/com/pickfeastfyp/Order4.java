package my.maslianah.com.pickfeastfyp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import my.maslianah.com.pickfeastfyp.model.Order;
import my.maslianah.com.pickfeastfyp.model.Packages;

public class Order4 extends AppCompatActivity {

    Button btnDone;
    EditText txtDetails;
    private DatabaseReference mDatabase,mDatabase2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order4);
        db = new DatabaseHelper(this);
        txtDetails = (EditText) findViewById(R.id.text_details);

        btnDone = (Button) findViewById(R.id.buttonDoneOrder4);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert into db
                Intent i = getIntent();
                final String orderID = i.getStringExtra(Order2.ORDER_2_ORDER_ID);
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                final String uid = user.getUid(); // get current user's unique id.

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       // if (txtDetails.getText().toString().equals(""))
                        mDatabase.child("Orders").child(orderID).child("custID").setValue(uid);
                        mDatabase.child("Orders").child(orderID).child("orderDetails").setValue(txtDetails.getText().toString());
                        Order order = dataSnapshot.getValue(Order.class);
                        db.updateCharityID("");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });


                AlertDialog.Builder builder = new AlertDialog.Builder(Order4.this);
                builder.setTitle("Reminder");
                builder.setCancelable(false);
                builder.setMessage("1) The order made will be updated once you re-enter the application\n" );
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(Order4.this, CustomerLogged.class);
                        startActivity(intent);
                    }
                });

                AlertDialog alert11 = builder.create();
                alert11.show();
                Toast.makeText(Order4.this, "Order Done!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
