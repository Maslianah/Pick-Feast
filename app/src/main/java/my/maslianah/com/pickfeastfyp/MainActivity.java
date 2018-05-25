package my.maslianah.com.pickfeastfyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public final static String MAIN_ACTIVITY_BUTTON_ID = "my.maslianah.com.pickfeastfyp.MAIN_ACTIVITY_BUTTON_ID";
    Button btnOnSpot, btnBigEvent, btnCharity;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        db = new DatabaseHelper(this);

        // check if user is signed in then start dashboardactivity
        if(mAuth.getCurrentUser()!=null) {
            Intent intent=new Intent(MainActivity.this,CustomerLogged.class);
            startActivity(intent);
            finish();
        }

        btnOnSpot = (Button) findViewById(R.id.buttonOnSpot);
        btnOnSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CatererListings.class);
                intent.putExtra(MAIN_ACTIVITY_BUTTON_ID, view.getId()); //where v is button that is cliked, you will find it as a parameter to onClick method
                startActivity(intent);
            }
        });
        btnBigEvent = (Button) findViewById(R.id.buttonBigEvent);
        btnBigEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CatererListings.class);
                intent.putExtra(MAIN_ACTIVITY_BUTTON_ID, view.getId()); //where v is button that is cliked, you will find it as a parameter to onClick method
                startActivity(intent);
            }
        });
        btnCharity = (Button) findViewById(R.id.buttonCharity);
        btnCharity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateCharityID("");
                Intent intent = new Intent(MainActivity.this, CharityListings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (mAuth.getCurrentUser()==null)
            getMenuInflater().inflate(R.menu.menu_main, menu);
        else
            getMenuInflater().inflate(R.menu.menu_customer_logged, menu);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        db.updateTheme(0);
        db.updateCharityID("");
        Log.e("onStart","occured");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_login) {
            Intent i = new Intent(this, login_signup_home.class);
            startActivity(i);
        }
        else if (item.getItemId() == R.id.action_logout)
        {
            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to log out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mAuth.signOut();
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(MainActivity.this,"Signed Out",Toast.LENGTH_SHORT).show();
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
        return true;
    }
}


/*
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
 */
