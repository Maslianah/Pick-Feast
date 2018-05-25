package my.maslianah.com.pickfeastfyp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import my.maslianah.com.pickfeastfyp.model.Customer;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private EditText txtMail,txtPassword,txtName,txtPhone,txtAddress, txtConfirm;
    Button btnSignUp, btnCancel;
    public final static String SIGN_UP_ORDER_ID = "my.maslianah.com.pickfeastfyp.SIGN_UP_ORDER_ID";
    public final static String SIGNUP_BUTTON_ID = "my.maslianah.com.pickfeastfyp.SIGNUP_BUTTON_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // Views
        txtName = (EditText)findViewById(R.id.name) ;
        txtPhone = (EditText)findViewById(R.id.phone);
        txtAddress = (EditText)findViewById(R.id.address);
        txtMail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        txtConfirm = (EditText)findViewById(R.id.repassword);

        btnSignUp = (Button) findViewById(R.id.signUp);
        btnCancel = (Button) findViewById(R.id.cancel_button);
        btnSignUp.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        if (!isOnline()){
            Context context = null;
            displayMobileDataSettingsDialog(Signup.this,context);
        }
    }
    // to validate the form is user has filled the required fields
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(txtName.getText().toString()) || txtName.getText().toString().length() < 3) {
            txtName.setError("At least 3 alphabets");
            result = false;
        } else {
            txtName.setError(null);
        }
        if (TextUtils.isEmpty(txtPhone.getText().toString())) {
            txtPhone.setError("Required");
            result = false;
        } else {
            txtPhone.setError(null);
        }
        if (TextUtils.isEmpty(txtAddress.getText().toString())) {
            txtAddress.setError("Required");
            result = false;
        } else {
            txtAddress.setError(null);
        }
        if (TextUtils.isEmpty(txtMail.getText().toString()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(txtMail.getText().toString()).matches()) {
            txtMail.setError("Enter a valid email address");
            result = false;
        } else {
            txtMail.setError(null);
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString()) || txtPassword.getText().toString().length() < 6 || txtPassword.getText().toString().length() > 10) {
            txtPassword.setError("Between 6 and 10 alphanumeric characters");
            result = false;
        } else {
            txtPassword.setError(null);
        }
        if (TextUtils.isEmpty(txtConfirm.getText().toString())) {
            txtConfirm.setError("Required");
            result = false;
        }else if (! txtConfirm.getText().toString().equals(txtPassword.getText().toString())) {
            txtConfirm.setError("Password Not Same");
            result = false;
        } else {
            txtConfirm.setError(null);
        }

        return result;
    }
    @Override
    public void onClick(final View v) {
        int id = v.getId();
        if(id == R.id.signUp){

            Log.d("MainActivity", "signIn");
            if (!validateForm()) {
                return;
            }
            showProgressDialog();

            final String name = txtName.getText().toString();
            final String phone = txtPhone.getText().toString();
            final String address = txtAddress.getText().toString();
            final String email = txtMail.getText().toString();
            final String password = txtPassword.getText().toString();
            String Repassword = txtConfirm.getText().toString();

            // to sign up the user at firebase
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("MainActivity", "createUser:onComplete:" + task.isSuccessful());
                            hideProgressDialog();
                            if (task.isSuccessful()) {
                                int count =1;

                                Intent mIntent = getIntent();
                                final int intValue = mIntent.getIntExtra(Login.LOGIN_CATERER_DIPLAY_BUTTTONID, 0);
                                final String orderID =  mIntent.getStringExtra(Login.LOGIN_ORDER_ID);

                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                Customer cust = new Customer(count++,password,name,phone,address,email);
                                mDatabase.child("Customer").child(uid).setValue(cust);
                                Toast.makeText(Signup.this, "Successful! Account created...",
                                        Toast.LENGTH_SHORT).show();

                                //if sign up from caterer display
                                if(intValue == R.id.bookNowButton) {
                                    Intent intent = new Intent(Signup.this, Order1.class);
                                    intent.putExtra(SIGN_UP_ORDER_ID,orderID);
                                    intent.putExtra(SIGNUP_BUTTON_ID, v.getId()); // logIn
                                    startActivity(intent);
                                    finish();
                                }
                                //if sign up from main activity/caterer listings
                                else{
                                    Intent intent = new Intent(Signup.this, CustomerLogged.class);
                                    //intent.putExtra(SIGN_UP_ORDER_ID,orderID);
                                    startActivity(intent);
                                    finish();
                                }

                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Toast.makeText(Signup.this, "Sign Up Failed" + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Log.e("SignUp","Failed Reg",e);
                                return;
                            }
                        }
                    });
        }
        else if (id == R.id.cancel_button) {

            Intent i = new Intent(Signup.this,MainActivity.class);
            startActivity(i);
            finish();

            Toast.makeText(Signup.this,"Account not created", Toast.LENGTH_LONG).show();
        }
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Creating Account...");
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public AlertDialog displayMobileDataSettingsDialog(final Activity activity, final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Internet");
        builder.setMessage("Please connect to your internet");
        builder.setCancelable(false);
        builder.setPositiveButton("Wifi", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Mobile Data", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings","com.android.settings.Settings$DataUsageSummaryActivity"));
                dialog.cancel();
                startActivity(intent);
                activity.finish();

            }
        });
        AlertDialog alert11 = builder.create();
        alert11.show();
        return alert11;
    }
}
/*@Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // checkbox status is changed from uncheck to checked.
        if (!isChecked) {
            // show password
            txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            // hide password
            txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }

    }*/