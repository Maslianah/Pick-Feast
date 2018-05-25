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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import my.maslianah.com.pickfeastfyp.model.Customer;
import my.maslianah.com.pickfeastfyp.model.Packages;

public class create_account_1 extends AppCompatActivity {

    EditText custName,custMail,custAddress,custNumber,custPassword;
    DatabaseReference mDatabase ;
    FirebaseAuth mAuth;
    ProgressDialog mProgressDialog;

    int custID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_1);

        custName = (EditText)findViewById(R.id.text_name) ;
        custMail = (EditText)findViewById(R.id.text_customeremail) ;
        custAddress = (EditText)findViewById(R.id.text_customeraddress) ;
        custNumber = (EditText)findViewById(R.id.text_customernumber) ;
        custPassword = (EditText)findViewById(R.id.text_customerpassword) ;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

     */
/*  Customer cust = new Customer();

        cust.setCustName(custName.getText().toString());
        cust.setCustEmail(custMail.getText().toString());*//*


        String name = custName.getText().toString();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child("CUST001").child("custID").setValue(custID++);
        mDatabase.child("Customers").child("CUST001").child("custName").setValue(name);
        mDatabase.child("Customers").child("CUST001").child("custAddress").setValue(custAddress.getText().toString());
        mDatabase.child("Customers").child("CUST001").child("custMail").setValue(custMail.getText().toString());
        mDatabase.child("Customers").child("CUST001").child("custNumber").setValue(custNumber.getText().toString());
        mDatabase.child("Customers").child("CUST001").child("custPassword").setValue(custPassword.getText().toString());

       // signUp(custMail.getText().toString(),custPassword.getText().toString());

    }
   */
/* // to validate the form is user has filled the required fields
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
    }*//*


    public void sign_up (View view) {

        Log.d("MainActivity", "signUp");
       */
/* if (!validateForm()) {
            return;
        }*//*

        showProgressDialog();
        String email = custMail.getText().toString();
        String password = custPassword.getText().toString();
        // to sign up the user at firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("MainActivity", "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            Toast.makeText(create_account_1.this, "Successful! Please Sign In",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(create_account_1.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(create_account_1.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

}
*/
/*

private boolean checkStyle() {

        if (cbBanquet.isChecked())
        {
            checkStylePackage("Banquet");
            noCb = true;
        }
        if(cbBuffet.isChecked()){
            //listView.setAdapter(checkStylePackage("Buffet"));
            checkStylePackage("Buffet");
            noCb = true;
        }
        if(cbLive.isChecked()){
            checkStylePackage("Live Station");
            noCb = true;
            //listView.setAdapter(checkStylePackage("Live Station"));
        }

        if(cbPack.isChecked()){
            checkStylePackage("Pack");
            noCb = true;
            ///listView.setAdapter(checkStylePackage("Pack"));
        }

        if( cbDome.isChecked()){
            checkStylePackage("Dome");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }

        if( cbM.isChecked()){
            checkStylePackage("Meeting");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if( cbP.isChecked()){
            checkStylePackage("Party");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }

        if( cbW.isChecked()){
            checkStylePackage("Wedding");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if( cbOh.isChecked()){
            checkStylePackage("Open House");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if( cbPe.isChecked()){
            checkStylePackage("Private");
            noCb = true;
            //  listView.setAdapter(checkStylePackage("Dome"));
        }
        if (noCb == true) {
            checkFunction = 1;
            return true;
        }
        else
            return false;
    }
    private boolean checkCuisine() {
        if(cbMalay.isChecked()) {
            here = checkCuisinePackage("Malay");
            noCb = true;
        }
        if(cbChinese.isChecked() ){
            here=checkCuisinePackage("Chinese");
            noCb = true;
        }
        if(cbIndian.isChecked()){
            //   checkStylePackage("Party");
            here = checkCuisinePackage("Indian");
            noCb = true;

        }
        if(cbWestern.isChecked()){
            here=checkCuisinePackage("Western");
            noCb = true;

        }
        if(cbOthers.isChecked() ){
            here=checkCuisinePackage("Others");
            noCb = true;

        }

        if (noCb == true) {
            return true;
        }
        else
            return false;
        //return here;
    }


    private CustomAdapter checkStylePackage(final String style) {
        arrayList.clear();
        rows.clear();


        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //for no check cbhalal
                if (!cbHalal.isChecked()) {
                    if (!rows1.isEmpty()) { //to avoid buffet and banquet has the same package
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                if ((pack.getPackDescription()).contains(style)) {
                                    list = pack.toStringFirst();
                                    //union, if intercept skip (means this is more the same style)
                                    for (int i = 0; i < rows1.size(); i++) {
                                        if (rows1.get(i).equals(list) && arrayListPack.get(a).equals(pack.getPackID()))
                                            break;
                                        else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                            if (i != rows1.size() - 1)
                                                continue;
                                            else {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapter2.add(list2);
                                                //rows1.add(list);
                                                //arrayAdapter.add(list);
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows1.add(desc);
                                                break;
                                            }
                                        }

                                    }
                                }
                            }
                        }
                        //if empty, add as usual. no intercept
                    } else {
                        for (int b = 0; b < arrayListPack.size(); b++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                //if it's in the selected style, and in the pack list. and halal right?
                                if ((pack.getPackDescription()).contains(style) && arrayListPack.get(b).equals(pack.getPackID())) {

                                    list = pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapter2.add(list2);
                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows1.add(desc);
                                    //adapter1.add(list);
                                    arrayAdapter.add(list);
                                }
                            }
                        }
                    }
                }
                // check intercept with halal
                else {
                    //if empty
                    if (rows1.isEmpty()) {
                        for (int a = 0; a < arrayListHalal.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                if (arrayListHalal.get(a).equals(list) && pack.getPackDescription().contains(style)) {
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapterV2.add(list2);
                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows1.add(desc);
                                    //rows1.add(list);
                                    arrayAdapter.add(list);
                                    break;
                                }
                            }
                        }
                    }
                    // if dome has been selected, dome needs to be removed in intercerpt
                    else
                    {
                        for (int a = 0; a < arrayListHalal.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                for (int b=0; b<rows1.size();b++){

                                    if (arrayListHalal.get(a).equals(list) && rows1.get(b).equals(list)) {
                                        break;
                                    }
                                    else if(arrayListHalal.get(a).equals(list))
                                    {
                                        if (b!=rows1.size()-1)
                                            continue;
                                        else {
                                            list2 = "\nPackage " + pack.toStringSecond();
                                            arrayAdapterV2.add(list2);
                                            rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                            rows1.add(desc);
                                            //adapter1.add(list);
                                            arrayAdapter.add(list);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onCancelled (DatabaseError databaseError) {

                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        return adapter;
    }

    private Boolean checkCuisinePackage(final String type) {
        arrayList.clear();
        rows.clear();
        detailList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //   adapter1 not empty
                //check without no halal
                if (!cbHalal.isChecked()) {
                    if (!rows1.isEmpty()) {
                        for (int a = 0; a < arrayListPack.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                if ((pack.getPackCuisine()).contains(type) && arrayListPack.get(a).equals(pack.getPackID())) {

                                    //INTERCEPTS
                                    if (checkFunction == 1) {
                                        Log.e("msg", "hereFunction1");
                                        for (int i = 0; i < rows1.size(); i++) {

                                            if (rows1.get(i).equals(list)) {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows2.add(desc);
                                                //rows2.add(list);
                                                arrayAdapter.add(list);
                                                break;
                                            }
                                        }
                                    }
                                    //UNION
                                    else if (arrayListPack.get(a).equals(pack.getPackID())) {
                                        Log.e("msg", "function!=1 "+checkFunction);
                                        for (int i = 0; i < rows1.size(); i++) {

                                            if (rows1.get(i).equals(list))
                                                break;
                                            else {
                                                if (i != rows1.size() - 1)
                                                    continue;
                                                else {
                                                    list2 = "\nPackage " + pack.toStringSecond();
                                                    //arrayAdapterV2.add(list2);
                                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                    rows2.add(desc);
                                                    //rows2.add(list);
                                                    arrayAdapter.add(list);
                                                    break;
                                                }
                                            }

                                        }
                                    }
                                    //adapter1 IS empty
                                }
                            }
                        }
                    }
                    //empty list (no style)
                    else {
                        if (checkFunction==1)
                        {

                        }
                        else{for (int b = 0; b < arrayListPack.size(); b++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                //add it like usual man, no style, no intercept or union
                                if ((pack.getPackCuisine()).contains(type) && arrayListPack.get(b).equals(pack.getPackID())) {

                                    list = pack.toStringFirst();
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapterV2.add(list2);
                                    //rows2.add(list);
                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows2.add(desc);
                                    arrayAdapter.add(list);

                                }

                            }
                        }
                        }
                    }
                }

                //is check arrayListhalal
                else
                {
                    //intercept between halal no style
                    if (rows1.isEmpty())
                    {
                        Log.e("msg", "adapter1 empty?");

                        for (int a = 0; a < arrayListHalal.size(); a++) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                if (arrayListHalal.get(a).equals(list) && pack.getPackCuisine().contains(type)) {
                                    list2 = "\nPackage " + pack.toStringSecond();
                                    arrayAdapterV2.add(list2);
                                    rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                    rows2.add(desc);
                                    //rows2.add(list);
                                    break;
                                }
                            }
                        }
                    }

                    //intercept between halal and style

                    else{

                        if (checkFunction==1) {
                            Log.e("msg", "cuisine intercept with style");
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Packages pack = dataSnapshot1.getValue(Packages.class);
                                list = pack.toStringFirst();
                                for (int b=0; b<rows1.size();b++){

                                    if (pack.getPackCuisine().contains(type) && rows1.get(b).equals(list)) {
                                        list2 = "\nPackage " + pack.toStringSecond();
                                        arrayAdapterV2.add(list2);
                                        rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                        rows2.add(desc);
                                        //rows2.add(list);
                                        arrayAdapter.add(list);
                                        break;
                                    }
                                }
                            }
                        }
                        //if not function 1, means function 0, intercept between halal and cuisine

                        else{
                            Log.e("msg", "cstupid");
                            for (int a = 0; a < arrayListHalal.size(); a++) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Packages pack = dataSnapshot1.getValue(Packages.class);
                                    list = pack.toStringFirst();
                                    for (int b=0; b<rows1.size();b++){

                                        if (arrayListHalal.get(a).equals(list) && rows1.get(b).equals(list)) {
                                            break;
                                        }
                                        else if(arrayListHalal.get(a).equals(list))
                                        {
                                            if (b!=rows1.size()-1)
                                                continue;
                                            else {
                                                list2 = "\nPackage " + pack.toStringSecond();
                                                arrayAdapterV2.add(list2);
                                                rowPack desc = new rowPack(checkImages(pack.getCatID()),list,list2);
                                                rows2.add(desc);
                                                //rows2.add(list);
                                                arrayAdapter.add(list);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } }

                }
                // onSpotList.size
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });


        return true;
    }


     private ArrayAdapter<String> checkHalal() {

        arrayList.clear();
        mHalal = FirebaseDatabase.getInstance().getReference().child("Caterers");
        mHalal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Caterer caterer = dataSnapshot1.getValue(Caterer.class);
                    if (caterer.getHalalReg().contains("Non-Halal")) {
                        list = caterer.getCatererID();
                        arrayAdapter.add(list);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        mLocation = FirebaseDatabase.getInstance().getReference().child("Packages");
        mLocation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Packages pc = dataSnapshot1.getValue(Packages.class);
                    list = pc.toStringFirst();
                    for (int a = 0; a < arrayListPack.size(); a++) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).equals(pc.getCatID()) && arrayListPack.get(a).equals(pc.getPackID())) {
                                //arrayAdapter.add(list);
                                list2 = "\nPackage " + pc.toStringSecond();
                                arrayAdapter2.add(list2);
                                arrayHalal.add(list);
                            }
                        }
                    }
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        return arrayHalal;

    }
 */



/* private CustomAdapter checkHalal(){

        arrayList.clear();
        rows.clear();
        mHalal = FirebaseDatabase.getInstance().getReference().child("Caterers");
        mHalal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Caterer caterer = dataSnapshot1.getValue(Caterer.class);
                    if (caterer.getHalalReg().contains("Non-Halal")) {
                        list = caterer.getCatID();
                        arrayAdapter.add(list);
                       // arrayAdapter.add(list);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        mLocation = FirebaseDatabase.getInstance().getReference().child("Packages");
        mLocation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Packages pc = dataSnapshot1.getValue(Packages.class);
                        list = pc.toStringFirst();
                        for (int a = 0; a<arrayListPack.size(); a++) {
                            for (int i = 0; i < arrayList.size(); i++) {
                            if (arrayList.get(i).equals(pc.getCatID()) && arrayListPack.get(a).equals(pc.getPackID())) {
                                //arrayAdapter.add(list);
                                list2 = "\nPackage " + pc.toStringSecond();
                                arrayAdapter2.add(list2);
                                arrayAdapter.add(list);
                                rowPack desc = new rowPack(checkImages(pc.getCatID()),list,list2);
                                rows1.add(desc);
                                //rows1.add(list);
                            }
                        }
                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        return adapter1;

    }*/