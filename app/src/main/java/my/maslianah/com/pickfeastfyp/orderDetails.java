package my.maslianah.com.pickfeastfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import my.maslianah.com.pickfeastfyp.model.Order;
import my.maslianah.com.pickfeastfyp.model.Packages;

public class orderDetails extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    Button btnCancel;
    private FirebaseUser user;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnCancel = (Button)findViewById(R.id.cancelOrder) ;
        btnCancel.setOnClickListener(this);

        Intent i = getIntent();
        TextView toView = findViewById(R.id.text_orderdesc);
        // String res = i.getStringExtra(CatererListings.EXTRA_RETURN_RESULT);
        String res = i.getStringExtra(CustomerOrder.CUST_ORDER_DETAILS);
        toView.setText(res);
        toView.setMovementMethod(new ScrollingMovementMethod());


    }

    @Override
    public void onClick(View v) {
        final Calendar currentDate = Calendar.getInstance();
        String myFormat = "ddMMyy";  //In which you need put here
        final SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, new Locale("ms","MY"));
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        int id = v.getId();
        if(id == R.id.cancelOrder) {
            //write code in button.
            mAuth = FirebaseAuth.getInstance();

            Intent i = getIntent();
            final String res = i.getStringExtra(CustomerOrder.CUST_ORDER_DETAILS);
            user = mAuth.getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");

            //list orders in list view
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Order orders = postSnapshot.getValue(Order.class);
                        String mGroupId = postSnapshot.getKey();
                        if (res.contains(mGroupId)) {

                            assert orders != null;
                            String currentString = orders.getEventDate();
                            String[] separated = currentString.split("/");
                            String eventDate = "";
                            for (int i = 0; i < separated.length; i++) {
                                eventDate += (separated[i]);
                            }

                            String currentString1 = orders.getEventTime();
                            String[] separated1 = currentString1.split(":");
                            String eventTime = "";
                            for (int i = 0; i < separated1.length; i++) {
                                eventTime += separated1[i];
                            }

                            if (Integer.parseInt(eventDate) < Integer.parseInt(dateFormat.format(currentDate.getTime()))) {
                                Toast.makeText(orderDetails.this, "You can't cancel past order!", Toast.LENGTH_SHORT).show();
                            } else if (Integer.parseInt(eventDate) == Integer.parseInt(dateFormat.format(currentDate.getTime()))) {
                                Toast.makeText(orderDetails.this, "You can't cancel order on the event day!", Toast.LENGTH_SHORT).show();
                            } else {
                                mDatabase.child(mGroupId).removeValue();
                                Toast.makeText(orderDetails.this, "Order is cancelled!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // [START_EXCLUDE]
                    System.out.println("The read failed: " + databaseError.getMessage());
                }
            });



            Intent intent = new Intent(orderDetails.this, CustomerLogged.class);
            startActivity(intent);
        }
    }
}
