package my.maslianah.com.pickfeastfyp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import my.maslianah.com.pickfeastfyp.model.Customer;
import java.util.Calendar;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Order2 extends AppCompatActivity {

    public final static String ORDER_2_ORDER_ID = "my.maslianah.com.pickfeastfyp.ORDER_2_ORDER_ID";
    Button btnNext;
    private DatabaseReference mDatabase;
    EditText txtDate,txtTime,txtPax;
    Calendar myCalendar, myTime;
    Calendar currentTime,currentDate;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);
        db = new DatabaseHelper(this);

        myCalendar = Calendar.getInstance();
        myTime = Calendar.getInstance();

        txtPax= (EditText) findViewById(R.id.text_pax);
        txtDate= (EditText) findViewById(R.id.eventDate);
        txtTime= (EditText) findViewById(R.id.eventTime);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(txtDate,myCalendar);
            }
        };

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Order2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                myTime .set(Calendar.HOUR_OF_DAY,selectedHour);
                myTime.set(Calendar.MINUTE,selectedMinute);
                //updateLabel(null,null,txtDate,myCalendar);
                updateTime(txtTime,myTime);

            }
        };


        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Order2.this, time, myTime
                        .get(Calendar.HOUR_OF_DAY), myTime.get(Calendar.MINUTE),TRUE).show();
            }
        });

        btnNext = (Button) findViewById(R.id.buttonNextOrder2);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateForm()) {
                    return;
                }

                Intent i = getIntent();
                final String orderID = i.getStringExtra(Order1.ORDER_1_ORDER_ID);


                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDatabase.child("Orders").child(orderID).child("orderPax").setValue(txtPax.getText().toString());
                        mDatabase.child("Orders").child(orderID).child("eventDate").setValue(txtDate.getText().toString());
                        mDatabase.child("Orders").child(orderID).child("eventTime").setValue(txtTime.getText().toString());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // [START_EXCLUDE]
                        System.out.println("The read failed: " + databaseError.getMessage());
                    }
                });

                //db.updateCharityID("");
                Intent intent = new Intent(Order2.this, Order4.class);
                intent.putExtra(ORDER_2_ORDER_ID,orderID);
                startActivity(intent);
            }
        });
    }


    private boolean validateForm() {
        boolean result = true;

        Cursor res = db.getAllData();
        StringBuffer bf = new StringBuffer();
        while (res.moveToNext())
        {
            bf.append(res.getString(2));
        }


        if(bf.toString().equals("2") ){
            if ((TextUtils.isEmpty(txtPax.getText().toString()) || Integer.parseInt(txtPax.getText().toString()) < 60)) {
                Log.e("msg", "bige"); txtPax.setError("Number of pax for this package should be more than 60 ");
                result = false;
            }

        }
        else if(bf.toString().equals("1") ){

            if ((Integer.parseInt(txtPax.getText().toString()) < 10) || (Integer.parseInt(txtPax.getText().toString()) > 60)) {
                txtPax.setError("Number of pax for this package should be between 10 to 60 ");
                result = false;
            }

        }else {
            txtPax.setError(null);
        }

        if (TextUtils.isEmpty(txtDate.getText().toString())) {
            txtDate.setError("Required");
            result = false;
        } else {
            txtDate.setError(null);
        }

        if (TextUtils.isEmpty(txtTime.getText().toString())) {
            txtTime.setError("Required");
            result = false;
        } else {
            txtTime.setError(null);
        }

        return result;
    }

    private void updateLabel(EditText txtDate, Calendar myCalendar){
        currentDate = Calendar.getInstance();
        String myFormat = "dd/MM/yy";  //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("ms","MY"));
       /* int mth = currentDate.get(Calendar.MONTH) + 1;
        currentDate.set(Calendar.MONTH,mth);
        int Umth = myCalendar.get(Calendar.MONTH) + 1;
        myCalendar.set(Calendar.MONTH,Umth);*/

        Log.e("CurrentMonth", String.valueOf(currentDate.get(Calendar.MONTH)));
        Log.e("USERMonth", String.valueOf(myCalendar.get(Calendar.MONTH)));
        //int i = currentDate.compareTo(myCalendar);
        int currentYear = currentDate.get(Calendar.YEAR);
        int userYear = myCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int userMonth = myCalendar.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int userDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        Cursor res = db.getAllData();
        StringBuffer bf = new StringBuffer();
        while (res.moveToNext())
        {
            bf.append(res.getString(2));
        }

        if(bf.toString().equals("2") ) {//big event
            if (currentYear < userYear) {
                txtDate.setText(sdf.format(myCalendar.getTime()));
                txtDate.setError(null);
            } else if (currentYear == userYear) {
                if (currentMonth < userMonth) {
                    txtDate.setText(sdf.format(myCalendar.getTime()));
                    txtDate.setError(null);
                } else if (currentMonth == userMonth) {
                    if (currentDay + 4 >= userDay) {
                        txtDate.setText(null);
                        txtDate.setError("Date should be after " + (currentDate.get(Calendar.DAY_OF_MONTH)+ 4) + "/" + (currentDate.get(Calendar.MONTH)+1) + "/" + currentDate.get(Calendar.YEAR));
                    } else {
                        txtDate.setText(sdf.format(myCalendar.getTime()));
                        txtDate.setError(null);
                    }
                } else {
                    txtDate.setText(null);
                    txtDate.setError("Date should be after " + (currentDate.get(Calendar.DAY_OF_MONTH)+ 4) + "/" + (currentDate.get(Calendar.MONTH)+1) + "/" + currentDate.get(Calendar.YEAR));
                    //Log.e("current day", " > user day");
                }
            } else {
                txtDate.setText(null);
                txtDate.setError("Date should be after " + (currentDate.get(Calendar.DAY_OF_MONTH) +4) + "/" + (currentDate.get(Calendar.MONTH)+1) + "/" + currentDate.get(Calendar.YEAR));
            }
        }else if (bf.toString().equals("1")){ //on spot
            //Log.e("b",bf.toString());
            if ( currentDay == userDay &&  currentMonth == userMonth && currentYear == userYear) {
                txtDate.setText(sdf.format(myCalendar.getTime()));
                txtDate.setError(null);
            }else {
                txtDate.setText(null);
                txtDate.setError("Date should be " + currentDate.get(Calendar.DAY_OF_MONTH)+ "/" + (currentDate.get(Calendar.MONTH)+1) + "/"  + currentDate.get(Calendar.YEAR));
            }
        }else{
            //charity
            txtDate.setText(sdf.format(myCalendar.getTime()));
            txtDate.setError(null);
        }

    }

    private void updateTime(EditText txtTime, Calendar myTime){
        currentTime = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        int hour = currentTime.get(Calendar.HOUR_OF_DAY) + 2;
        currentTime.set(Calendar.HOUR_OF_DAY,hour);
        int i = currentTime.compareTo(myTime);

        Cursor res = db.getAllData();
        StringBuffer bf = new StringBuffer();
        while (res.moveToNext())
        {
            bf.append(res.getString(2));
        }

        if(bf.toString().equals("2") ){ //big event
            txtTime.setText(sdf.format(myTime.getTime()));
        }
        else if (bf.toString().equals("1")){ //on spot
            if (i > 0 ){
                // currentTime = 18.36, userTime = 18.36 // currentTime = 18.36, userTime = 19.36 // currentTime = 18.36, userTime = 20.36
                // currentTime = 18.36, userTime = 14.36
                txtTime.setError("Time should be after " + currentTime.get(Calendar.HOUR_OF_DAY) + ":" + currentTime.get(Calendar.MINUTE));
            }
            else
                // currentTime = 18.36, userTime = 21.36
                txtTime.setText(sdf.format(myTime.getTime()));
        }
        else {
            Log.e("Charity","Button");
            //txtTime.setText(sdf.format(myTime.getTime()));
        }

    }

}
/*else if (bf.toString().equals("1")){ //on spot
            //Log.e("b",bf.toString());
            if ( currentDay == userDay &&  currentMonth == userMonth && currentYear == userYear) {
                txtDate.setText(sdf.format(myCalendar.getTime()));
                txtDate.setError(null);
            }else if (currentYear == userYear){
                if (currentMonth < userMonth){
                    txtDate.setText(sdf.format(myCalendar.getTime()));
                    txtDate.setError(null);
                }else if (currentMonth == userMonth){
                    if (currentDay < userDay){
                        txtDate.setText(sdf.format(myCalendar.getTime()));
                        txtDate.setError(null);
                    }else if (currentDay == userDay){
                        Log.e("on spot", " same day");
                        txtDate.setText(sdf.format(myCalendar.getTime()));
                        txtDate.setError(null);
                    }else {
                        txtDate.setText(null);
                        txtDate.setError("Date should be after " + currentDate.get(Calendar.DAY_OF_MONTH)+ "/" + currentDate.get(Calendar.MONTH) + "/"  + currentDate.get(Calendar.YEAR));
                    }
                }else {
                    txtDate.setText(null);
                    txtDate.setError("Date should be after " + currentDate.get(Calendar.DAY_OF_MONTH)+ "/" + currentDate.get(Calendar.MONTH) + "/"  + currentDate.get(Calendar.YEAR));
                }
            }else {
                txtDate.setText(null);
                txtDate.setError("Date should be after " + currentDate.get(Calendar.DAY_OF_MONTH)+ "/" + currentDate.get(Calendar.MONTH) + "/"  + currentDate.get(Calendar.YEAR));
            }*/