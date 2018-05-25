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
import android.view.View;
import android.widget.Button;

public class login_signup_home extends AppCompatActivity implements View.OnClickListener{

    Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup_home);

        btnSignUp = (Button) findViewById(R.id.signUp);
        btnLogIn = (Button) findViewById(R.id.logIn);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (!isOnline()){
            Context context = null;
            displayMobileDataSettingsDialog(login_signup_home.this,context);
        }


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.signUp){
            Intent intent = new Intent(login_signup_home.this, Signup.class);
            startActivity(intent);

        }else if (id == R.id.logIn){
            Intent intent = new Intent(login_signup_home.this, Login.class);
            startActivity(intent);
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

}
