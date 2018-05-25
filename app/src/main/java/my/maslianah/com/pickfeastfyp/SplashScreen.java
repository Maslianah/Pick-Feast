package my.maslianah.com.pickfeastfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class SplashScreen extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_splash_screen);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pick&Feast");

        final TextView loading = (TextView)findViewById(R.id.txtLoad);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(50);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            count ++;
                            if (count>100) {
                                loading.setText( "100%");
                            }else {
                                loading.setText(String.valueOf(count) + "%");
                            }
                            if (count == 100) {
                                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }
                            }
                        });
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        myThread.start();
    }
}
