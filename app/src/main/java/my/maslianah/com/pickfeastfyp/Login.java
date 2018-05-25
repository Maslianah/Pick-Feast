package my.maslianah.com.pickfeastfyp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Button signIn, signUp;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private EditText txtMail,txtPassword;
    private DatabaseReference mDatabase;
    CheckBox cbShowPassword;

    public final static String LOGIN_ORDER_ID = "my.maslianah.com.pickfeastfyp.LOGIN_ORDER_ID";
    public final static String LOGIN_CATERER_DIPLAY_BUTTTONID = "my.maslianah.com.pickfeastfyp.LOGIN_CATERER_DIPLAY_BUTTTONID";
    public final static String LOGIN_BUTTON_ID = "my.maslianah.com.pickfeastfyp.LOGIN_BUTTON_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signIn = (Button) findViewById(R.id.logIn);
        signUp = (Button) findViewById(R.id.signUp);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        // Views
        txtMail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText) findViewById(R.id.password);
        cbShowPassword = (CheckBox) findViewById(R.id.viewPassword);
        cbShowPassword.setOnCheckedChangeListener(this);

        if (!isOnline()){
            Context context = null;
            displayMobileDataSettingsDialog(Login.this,context);
        }
    }

 /*   @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    // to validate the form is user has filled the required fields
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(txtMail.getText().toString())) {
            txtMail.setError("Required");
            result = false;
        } else {
            txtMail.setError(null);
        }
        if (TextUtils.isEmpty(txtPassword.getText().toString())) {

            txtPassword.setError("Required");
            result = false;
        } else {
            txtPassword.setError(null);
        }
        return result;
    }

    public void signIn (final View view) {

        Log.d("MainActivity", "signIn");
        if (!validateForm()) {
            return;
        }
        showProgressDialog();
        String email = txtMail.getText().toString();
        String password = txtPassword.getText().toString();

        // to sign in the user at firebase
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("MainActivity", "signIn:onComplete:" + task.isSuccessful());
                hideProgressDialog();
                if (task.isSuccessful()) {

                    Intent mIntent = getIntent();
                    final int intValue = mIntent.getIntExtra(CatererDisplay.CATERER_DISPLAY_BUTTON_ID, 0);
                    final String orderID =  mIntent.getStringExtra(CatererDisplay.CATERER_DISPLAY_ORDER_ID);

                    //if log in from caterer display
                    if(intValue == R.id.bookNowButton) {
                        Intent intent = new Intent(Login.this, Order1.class);
                        intent.putExtra(LOGIN_ORDER_ID,orderID);
                        intent.putExtra(LOGIN_BUTTON_ID, view.getId()); // logIn
                        startActivity(intent);
                        finish();
                    }
                    //if log in from main activity/caterer listings
                    else{
                        Intent intent = new Intent(Login.this, CustomerLogged.class);
                        //intent.putExtra(LOGIN_ORDER_ID,orderID);
                        //intent.putExtra(LOGIN_BUTTON_ID, view.getId());
                        startActivity(intent);
                        finish();
                    }

                }
                else {
                    Toast.makeText(Login.this, "Sign In Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signUp (View view) {
        Intent mIntent = getIntent();
        final String orderID =  mIntent.getStringExtra(CatererDisplay.CATERER_DISPLAY_ORDER_ID);
        final int intValue = mIntent.getIntExtra(CatererDisplay.CATERER_DISPLAY_BUTTON_ID, 0);

        Intent intent=new Intent(Login.this,Signup.class);
        intent.putExtra(LOGIN_CATERER_DIPLAY_BUTTTONID,intValue);
        intent.putExtra(LOGIN_ORDER_ID,orderID);
        startActivity(intent);
        finish();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Authenticating...");
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        // checkbox status is changed from uncheck to checked.
        if (!isChecked) {
            // show password
            txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            // hide password
            txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }

    }
}
