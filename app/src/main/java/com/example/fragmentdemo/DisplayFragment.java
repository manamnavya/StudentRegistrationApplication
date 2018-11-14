package com.example.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    Integer flag=-1;
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Student mParam1;


    private OnFragmentInteractionListener mListener;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(Student param1) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        Log.d("param1",param1.toString());
/*
        args.putString(ARG_PARAM2, param2);
*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Student) getArguments().getSerializable(ARG_PARAM1);
            /*mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Student) getArguments().getSerializable(ARG_PARAM1);
            /*mParam2 = getArguments().getString(ARG_PARAM2);*/
            TextView name=getView().findViewById(R.id.textName);
            TextView email=getView().findViewById(R.id.textEmail);
            TextView dept=getView().findViewById(R.id.textDep);
            TextView mood=getView().findViewById(R.id.textMood);
            name.setText(mParam1.getName());
            email.setText(mParam1.getEmail());
            dept.setText(mParam1.getDepartment());
            mood.setText(mParam1.getMood()+"% Positive");
            ImageButton editname=getActivity().findViewById(R.id.ibName);
            ImageButton editemail=getActivity().findViewById(R.id.ibEmail);
            ImageButton editDep=getActivity().findViewById(R.id.ibDep);
            ImageButton editMood=getActivity().findViewById(R.id.ibMood);
            editname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=0;
                    mListener.sendDatatoedit(mParam1,flag);
                }
            });
            editemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=1;
                    mListener.sendDatatoedit(mParam1,flag);
                }
            });
            editDep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=2;
                    mListener.sendDatatoedit(mParam1,flag);
                }
            });
            editMood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag=3;
                    mListener.sendDatatoedit(mParam1,flag);
                }
            });


        }
       /* if(flag!=-1)
        {
            mListener.sendDatatoedit(mParam1,flag);
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_display, container, false);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void sendDatatoedit(Student student,Integer flag);
    }
}
