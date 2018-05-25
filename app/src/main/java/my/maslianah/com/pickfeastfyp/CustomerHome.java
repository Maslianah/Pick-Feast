package my.maslianah.com.pickfeastfyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CustomerHome extends Fragment {

    public final static String MAIN_ACTIVITY_BUTTON_ID = "my.maslianah.com.pickfeastfyp.MAIN_ACTIVITY_BUTTON_ID";
    Button btnOnSpot, btnBigEvent, btnCharity;
    DatabaseHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DatabaseHelper(getContext());
        // Inflate the layout for this fragment

       // Intent mIntent = getActivity().getIntent();
       // final int intValue = mIntent.getIntExtra(MainActivity.MAIN_ACTIVITY_BUTTON_ID, 0);

        View view = inflater.inflate(R.layout.activity_main, container, false);

        btnOnSpot = (Button) view.findViewById(R.id.buttonOnSpot);
        btnOnSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CatererListings.class);
                intent.putExtra(MAIN_ACTIVITY_BUTTON_ID, view.getId());
                startActivity(intent);
            }
        });
        btnBigEvent = (Button) view.findViewById(R.id.buttonBigEvent);
        btnBigEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CatererListings.class);
                intent.putExtra(MAIN_ACTIVITY_BUTTON_ID, view.getId());
                startActivity(intent);
            }
        });
        btnCharity = (Button) view.findViewById(R.id.buttonCharity);
        btnCharity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.updateCharityID("");
                Intent intent = new Intent(getActivity(), CharityListings.class);
                startActivity(intent);
            }
        });
        return view;
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
          //  mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
