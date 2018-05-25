package my.maslianah.com.pickfeastfyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import my.maslianah.com.pickfeastfyp.model.Caterer;
import my.maslianah.com.pickfeastfyp.model.Charity;
import my.maslianah.com.pickfeastfyp.model.Order;
import my.maslianah.com.pickfeastfyp.model.Packages;

import static android.support.constraint.Constraints.TAG;


public class CustomerOrder extends Fragment implements AdapterView.OnItemClickListener {

    private FirebaseUser user;
    private DatabaseReference mDatabase, mDB, mDB3;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> detailList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter, arrayAdapter2;
    String list = "", list2 = "", value = "";
    private ListView listView;
    public final static String CUST_ORDER_DETAILS = "my.maslianah.com.pickfeastfyp.CUST_ORDER_DETAILS";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);
        listView = (ListView) view.findViewById(R.id.lv2);
        listView.setOnItemClickListener(this);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, detailList);

        user = FirebaseAuth.getInstance().getCurrentUser();

       displayOrder();


        /*//list orders in list view
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                //arrayAdapter.notifyDataSetChanged();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    final Order orders = postSnapshot.getValue(Order.class);
                    final String mGroupId = postSnapshot.getKey();
                    assert orders != null;

                    if (arrayList.contains(orders.getOrderId())) {
                        arrayList.set(arrayList.indexOf(orders.getOrderId()), orders.getOrderId());
                    } else {

                        if (user.getUid().equals(orders.getCustID())) {
                            if (mGroupId.equals(orders.getOrderId())) {

                                if (!orders.FtoString().contains("CHARITY")) {
                                    list = "\nOrder Details: " + orders.getOrderId() + "\n\n" + orders.getEventDate() + "\n" + orders.getEventTime();
                                    list2 = "\n" + orders.toString() + "\nCharity Details\n" + "NULL";//+ checkDetails(orders.getPackId());
                                    arrayAdapter.add(list);
                                    arrayAdapter2.add(list2);
                                } else {
                                    mDB = FirebaseDatabase.getInstance().getReference().child("Charity");
                                    mDB.addValueEventListener(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot1) {

                                            for (DataSnapshot postSnapshot1 : dataSnapshot1.getChildren()) {

                                                Charity charity = postSnapshot1.getValue(Charity.class);
                                                assert charity != null;

                                                if (charity.getCharityID().equals(orders.getCharityID())) {
                                                    list = "\nOrder Details: " + orders.getOrderId() + "\n\n" + orders.getEventDate() + "\n" + orders.getEventTime();
                                                    list2 = "\n" + orders.toString() + "\nCharity Details\n" + charity.ordetDetailstoString();//+ checkDetails(orders.getPackId());
                                                    arrayAdapter.add(list);
                                                    arrayAdapter2.add(list2);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // [START_EXCLUDE]
                                            System.out.println("The read failed: " + databaseError.getMessage());
                                        }
                                    });
                                }

                            }

                        }
                    }
                }

                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });*/

        return view;
    }

    private void displayOrder() {

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        mDatabase.addChildEventListener(new ChildEventListener() {
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildAdded:" + dataSnapshot.getKey());
                // A new comment has been added, add it to the displayed list
                final Order order = dataSnapshot.getValue(Order.class);
                assert order != null;
                if (user.getUid().equals(order.getCustID())) {
                    if (!order.FtoString().contains("CHARITY")) {
                        list = "\nOrder Details: " + order.getOrderId() + "\n\n" + order.getEventDate() + "\n" + order.getEventTime();
                        list2 = "\n" + order.toString() + "\nCharity Details\n" + "NULL";//+ checkDetails(orders.getPackId());
                        arrayAdapter.add(list);
                        arrayAdapter2.add(list2);
                    } else {
                        mDB = FirebaseDatabase.getInstance().getReference().child("Charity");
                        mDB.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot1) {

                                for (DataSnapshot postSnapshot1 : dataSnapshot1.getChildren()) {

                                    Charity charity = postSnapshot1.getValue(Charity.class);
                                    assert charity != null;

                                    if (charity.getCharityID().equals(order.getCharityID())) {
                                        list = "\nOrder Details: " + order.getOrderId() + "\n\n" + order.getEventDate() + "\n" + order.getEventTime();
                                        list2 = "\n" + order.toString() + "\nCharity Details\n" + charity.orderDetailstoString();//+ checkDetails(order.getPackId());
                                        arrayAdapter.add(list);
                                        arrayAdapter2.add(list2);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // [START_EXCLUDE]
                                System.out.println("The read failed: " + databaseError.getMessage());
                            }
                        });
                    }
                }
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                Order newComment = dataSnapshot.getValue(Order.class);
                String commentKey = dataSnapshot.getKey();

                //arrayAdapter.notifyDataSetChanged();
            }

            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();
            }

            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                Order movedComment = dataSnapshot.getValue(Order.class);
                String commentKey = dataSnapshot.getKey();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "postComments:onCancelled", databaseError.toException());
            }
        });
    }
/*    @Override
    public void onStart() {
        super.onStart();
        //listView.setAdapter(arrayAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }*/

    private String checkDetails(final String packId) {
        mDB3 = FirebaseDatabase.getInstance().getReference().child("Packages");

        mDB3.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                //arrayAdapter.notifyDataSetChanged();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    final Packages pc = postSnapshot.getValue(Packages.class);
                    final String mGroupId = postSnapshot.getKey();
                    assert pc != null;

                    if (pc.getPackID().equals(packId)) {
                        mDB3 = FirebaseDatabase.getInstance().getReference().child("Caterers");
                        mDB3.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                    final Caterer caterer = postSnapshot.getValue(Caterer.class);
                                    final String mGroupId = postSnapshot.getKey();
                                    assert caterer != null;
                                    if (caterer.getCatID().equals(pc.getCatID())) {
                                        value = caterer.toString();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // [START_EXCLUDE]
                                System.out.println("The read failed: " + databaseError.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
        return value;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        StringBuilder builder = new StringBuilder();
        builder.append(detailList.get(position));


        Intent intent = new Intent(getActivity(), orderDetails.class);
        intent.putExtra(CUST_ORDER_DETAILS, builder.toString());

        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}


/*
COPY
package my.maslianah.com.pickfeastfyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import my.maslianah.com.pickfeastfyp.model.Charity;
import my.maslianah.com.pickfeastfyp.model.Order;


public class CustomerOrder extends Fragment implements AdapterView.OnItemClickListener {

    private FirebaseUser user;
    private DatabaseReference mDatabase, mDB, mDB3;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> detailList = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapter, arrayAdapter2;
    String list = "", list2 = "", value = "";
    private ListView listView;
    public final static String CUST_ORDER_DETAILS = "my.maslianah.com.pickfeastfyp.CUST_ORDER_DETAILS";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);
        listView = (ListView) view.findViewById(R.id.lv2);
        listView.setOnItemClickListener(this);
        arrayAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, detailList);

        user = FirebaseAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Orders");
        //list orders in list view
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                arrayAdapter.notifyDataSetChanged();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    final Order orders = postSnapshot.getValue(Order.class);
                    final String mGroupId = postSnapshot.getKey();

                    assert orders != null;
                    if (user.getUid().equals(orders.getCustID())) {
                        if (mGroupId.equals(orders.getOrderId())) {

                            if (!orders.FtoString().contains("CHARITY")) {
                                list = "\nOrder Details: " + orders.getOrderId() + "\n\n" + orders.getEventDate() + "\n" + orders.getEventTime();
                                list2 = "\n" + orders.toString() + "\nCharity Details\n" + "NULL";//+ checkDetails(orders.getPackId());
                                arrayAdapter.add(list);
                                arrayAdapter2.add(list2);
                            } else {
                                mDB = FirebaseDatabase.getInstance().getReference().child("Charity");
                                mDB.addValueEventListener(new ValueEventListener() {

                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot1) {

                                        for (DataSnapshot postSnapshot1 : dataSnapshot1.getChildren()) {

                                            Charity charity = postSnapshot1.getValue(Charity.class);
                                            assert charity != null;

                                            if (charity.getCharityID().equals(orders.getCharityID())) {
                                                list = "\nOrder Details: " + orders.getOrderId() + "\n\n" + orders.getEventDate() + "\n" + orders.getEventTime();
                                                list2 = "\n" + orders.toString() + "\nCharity Details\n" + charity.ordetDetailstoString();//+ checkDetails(orders.getPackId());
                                                arrayAdapter.add(list);
                                                arrayAdapter2.add(list2);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        // [START_EXCLUDE]
                                        System.out.println("The read failed: " + databaseError.getMessage());
                                    }
                                });
                            }

                        }

                    }
                }

                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // [START_EXCLUDE]
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        StringBuilder builder = new StringBuilder();
        ;
        builder.append(detailList.get(position));


        Intent intent = new Intent(getActivity(), orderDetails.class);
        intent.putExtra(CUST_ORDER_DETAILS, builder.toString());

        startActivity(intent);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
}
}
*/
