/*
package my.maslianah.com.pickfeastfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import my.maslianah.com.pickfeastfyp.model.Customer;
import my.maslianah.com.pickfeastfyp.model.Packages;

public class create_account_2 extends AppCompatActivity {

    EditText custAdd,custNumber;
    DatabaseReference mDatabase ;
    String list = "";
    ArrayAdapter<String>arrayAdapter;
    ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_2);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Packages");
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView = (ListView)findViewById(R.id.lv);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //Caterer caterer = postSnapshot.getValue(Caterer.class);
                    //Charity charity = postSnapshot.getValue(Charity.class);
                    //AddOn addOn = postSnapshot.getValue(AddOn.class);
                    Packages pack = postSnapshot.getValue(Packages.class);
                    list = list + pack.toString();
                    arrayAdapter.add(list);
                }
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

      *//*

*/
/*  mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Customers").child("CUST001").child("custAddress").setValue(custAdd.getText().toString());
        mDatabase.child("Customers").child("CUST001").child("custPhone").setValue(custNumber.getText().toString());
*//*
*/
/*

    }


    public void next(View v)
    {
        Intent i = new Intent(this, create_account_3.class);
        startActivity(i);
    }
}
*//*

private ArrayAdapter<String> checkIndian() {

        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Indian")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Indian")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }

private ArrayAdapter<String> checkMalay() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Malay")) {
        list = pack.toStringFirst();



        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        arrayCopy.add(list);
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Malay")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }

private ArrayAdapter<String> checkChinese() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Chinese")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Chinese")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }

private ArrayAdapter<String> checkWestern() {

        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Western")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }

        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Western")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
//        }
//        return null;
        }

private ArrayAdapter<String> checkOthers() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Others")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackCuisine()).contains("Others")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;

        }

private ArrayAdapter<String> checkBanquet() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Banquet")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Banquet")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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

        return arrayAdapter;
        }

private ArrayAdapter<String> checkBuffet() {

        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Buffet")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Buffet")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }

private ArrayAdapter<String> checkDome() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Dome")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Dome")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;

        }

private ArrayAdapter<String> checkPack() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Pack")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Pack")) {

        list = pack.toStringFirst();
        arrayCopy.add(list);
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }

private ArrayAdapter<String> checkLive() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Live Station")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Live Station")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;

        }

private ArrayAdapter<String> checkWedding() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Wedding")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Wedding")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }
private ArrayAdapter<String> checkParty() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Party")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Party")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }
private ArrayAdapter<String> checkPrivate() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Private")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Private")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }
private ArrayAdapter<String> checkOpen() {
        arrayList.clear();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Open House")) {
        list = pack.toStringFirst();


        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Open House")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }
private ArrayAdapter<String> checkMeeting() {
        arrayList.clear();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(DataSnapshot dataSnapshot) {
        if (!arrayList1.isEmpty()) {

        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Meeting")) {
        list = pack.toStringFirst();

        for (int i = 0; i < arrayList1.size(); i++) {

        if (arrayList1.get(i).equals(list))
        break;
        else {
        if (i!=arrayList1.size()-1)
        continue;
        else {
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
        break;
        }
        }

        }
        }
        }
        }else
        {
        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

        Packages pack = dataSnapshot1.getValue(Packages.class);
        if ((pack.getPackDescription()).contains("Meeting")) {

        list = pack.toStringFirst();
        list2 = "\nPackage " + pack.toStringSecond();
        arrayAdapter2.add(list2);
        arrayCopy.add(list);
        arrayAdapter.add(list);
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
        return arrayAdapter;
        }*/
