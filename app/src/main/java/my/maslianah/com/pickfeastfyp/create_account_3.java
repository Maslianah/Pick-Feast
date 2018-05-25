/*
package my.maslianah.com.pickfeastfyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import my.maslianah.com.pickfeastfyp.model.Customer;

public class create_account_3 extends AppCompatActivity {

    EditText custPass,custName;
    DatabaseReference mDatabase ;
    Customer cust;

    DatabaseReference databaseReference;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_3);

        signUp();
        custPass = (EditText) findViewById(R.id.text_customerpassword);
        custName = (EditText) findViewById(R.id.text_name);

*/
/*

        cust = new Customer();

        cust.setCustID(custID);
        cust.setCustPassword(custPass.getText().toString());
*//*


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child("CUST001").child("custID").setValue(custName.getText().toString());
        mDatabase.child("Customers").child("CUST001").child("custPass").setValue(custPass.getText().toString());
    }
        private void signUp() {
            Log.d("MainActivity", "signUp");
            if (!validateForm()) {
                return;
            }
            showProgressDialog();
            String name = custName.getText().toString();
            String pass = custPass.getText().toString();
        // to sign up the user at firebase
        mAuth.createUserWithEmailAndPassword(name,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("Customers").child("CUST001").child("custName").setValue(custName.getText().toString());
                        mDatabase.child("Customers").child("CUST001").child("custPass").setValue(custPass.getText().toString());
                        Log.d("MainActivity", "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            Toast.makeText(create_account_3.this, "Successful! Please Sign In",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(create_account_3.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    // to validate the form is user has filled the required fields
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(custName.getText().toString())) {
            custName.setError("Required");
            result = false;
        } else {
            custName.setError(null);
        }
        if (TextUtils.isEmpty(custPass.getText().toString())) {
            custPass.setError("Required");
            result = false;
        } else {
            custPass.setError(null);
        }
        return result;
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    public void done(View view) {
        // Getting the ID from firebase database.
        //String StudentRecordIDFromServer = databaseReference.push().getKey();

        Intent i = new Intent(create_account_3.this,MainActivity.class);

        startActivity(i);
        Toast.makeText(create_account_3.this,"Name must not be empty",Toast.LENGTH_SHORT).show();
    }
}
*/
