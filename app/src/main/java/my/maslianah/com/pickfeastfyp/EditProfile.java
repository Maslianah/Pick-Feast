package my.maslianah.com.pickfeastfyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import my.maslianah.com.pickfeastfyp.model.Customer;

import static android.content.ContentValues.TAG;


public class EditProfile extends Fragment implements View.OnClickListener{


    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private TextView custName, custPhone, custAddress,custEmail,custPassword;
    Button btnSubmit,btnName,btnAddress,btnEmail,btnPhone,btnPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_edit_profile, container, false);
        btnSubmit = (Button) view.findViewById(R.id.saveDetails);
        btnName = (Button) view.findViewById(R.id.editName);
        btnPhone = (Button) view.findViewById(R.id.editPhone);
        btnAddress = (Button) view.findViewById(R.id.editAdd);
        btnEmail = (Button) view.findViewById(R.id.editMail);
        btnPassword = (Button) view.findViewById(R.id.editPass);
        custName = (TextView)view.findViewById(R.id.name);
        custPhone = (TextView)view.findViewById(R.id.phone);
        custAddress = (TextView)view.findViewById(R.id.address);
        custEmail = (TextView)view.findViewById(R.id.email);
        custPassword = (TextView)view.findViewById(R.id.password);
        btnSubmit.setOnClickListener(this);
        btnName.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnAddress.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnPassword.setOnClickListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            final String uid = user.getUid(); // get current user's unique id.
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Customer");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Customer cust = postSnapshot.getValue(Customer.class);
                        String mGroupId = postSnapshot.getKey();

                        if (uid.equals(mGroupId)){

                            custName.setText(cust.getCustName());
                            custPhone.setText(cust.getCustPhone());
                            custAddress.setText(cust.getCustAddress());
                            custEmail.setText(cust.getCustEmail());
                            custPassword.setText(cust.getCustPassword());
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
        // Inflate the layout for this fragment
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
    public void onClick(final View v) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        int id = v.getId();

        if (id == R.id.editName || id == R.id.editPhone  || id == R.id.editAdd  || id == R.id.editMail  || id == R.id.editPass ){
            String title = null;
            // get activity_prompt.xml view
            LayoutInflater li = LayoutInflater.from(getContext());
            View promptsView = li.inflate(R.layout.activity_prompt, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

            // set activity_prompt.xml to alert dialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);

            switch (id) {
                case R.id.editName: title = "Edit Name";break;
                case R.id.editPhone: title = "Edit Phone";break;
                case R.id.editAdd: title = "Edit Address";break;
                case R.id.editMail: title = "Edit E-mail";break;
                case R.id.editPass: title = "Edit Password";break;
            }

            // set dialog message
            alertDialogBuilder
                    .setTitle(title)
                    .setCancelable(false)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    switch ( v.getId()) {
                                        case R.id.editName:{
                                            custName.setText(userInput.getText());
                                            Toast.makeText(getContext(),"Name edited successfully",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        case R.id.editPhone: {
                                            custPhone.setText(userInput.getText());
                                            Toast.makeText(getContext(),"Telephone Number edited successfully",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        case R.id.editAdd:{
                                            custAddress.setText(userInput.getText());
                                            Toast.makeText(getContext(),"Address edited successfully",Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                        case R.id.editMail: {
                                            custEmail.setText(userInput.getText());
                                            Toast.makeText(getContext(),"Email edited successfully",Toast.LENGTH_SHORT).show();
                                            // Get auth credentials from the user for re-authentication
                                            assert user != null;
                                            AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(user.getEmail()), custPassword.getText().toString()); // Current Login Credentials \\
                                            // Prompt the user to re-provide their sign-in credentials
                                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Log.d(TAG, "User re-authenticated.");
                                                    //Now change your email address \\
                                                    //----------------Code for Changing Email Address----------\\
                                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                    assert user != null;
                                                    user.updateEmail(custEmail.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Toast.makeText(getContext(),"User email address updated.",Toast.LENGTH_SHORT).show();
                                                                        Log.d("MSG", "User email address updated.");
                                                                    }
                                                                }
                                                            });
                                                    //----------------------------------------------------------\\
                                                }
                                            });
                                            break;
                                        }
                                        case R.id.editPass: {
                                            // Get auth credentials from the user for re-authentication
                                            AuthCredential credential = EmailAuthProvider.getCredential(custEmail.getText().toString(), custPassword.getText().toString()); // Current Login Credentials \\
                                            custPassword.setText(userInput.getText());
                                            Toast.makeText(getContext(),"Password edited successfully",Toast.LENGTH_SHORT).show();
                                            // Prompt the user to re-provide their sign-in credentials
                                            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Log.d(TAG, "User re-authenticated.");
                                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                    assert user != null;
                                                    user.updatePassword(userInput.getText().toString())
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Toast.makeText(getContext(),"Password updated.",Toast.LENGTH_SHORT).show();
                                                                        Log.d("MSG", "Password updated.");
                                                                    }
                                                                }
                                                            });
                                                }
                                            });
                                            break;
                                        }
                                    }
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

        }
        else if(id == R.id.saveDetails) {
            if (user != null) {

                final String name = custName.getText().toString();
                final String phone = custPhone.getText().toString();
                final String address = custAddress.getText().toString();
                final String email = custEmail.getText().toString();//user.getEmail();
                final String password = custPassword.getText().toString();

                final String uid = user.getUid(); // get current user's unique id.
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Customer");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Customer cust;
                            String mGroupId = postSnapshot.getKey();

                            if (uid.equals(mGroupId)) {

                                custName.setText(name);
                                custPhone.setText(phone);
                                custAddress.setText(address);
                                custEmail.setText(email);
                                custPassword.setText(password);

                                cust = new Customer(0, password, name, phone, address, email);
                                mDatabase.child(uid).setValue(cust);
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
