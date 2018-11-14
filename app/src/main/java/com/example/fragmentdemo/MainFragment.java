package com.example.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import static android.icu.text.UnicodeSet.CASE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Student mParam1;
    private Integer mParam2;
    EditText name;
    EditText email;
    RadioGroup department;
    String moodstring;
    SeekBar mood;
    RadioButton sis;
    RadioButton cs;
    RadioButton bio;
    RadioButton others;


    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(Student param1, Integer param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Student) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
 /*   public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(s);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = (EditText) getActivity().findViewById(R.id.etName);
        email = (EditText) getActivity().findViewById(R.id.etEmail);
        department = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        mood = (SeekBar) getActivity().findViewById(R.id.seekBar);
        if (getArguments() != null) {
            getActivity().findViewById(R.id.button).setEnabled(false);
            getActivity().findViewById(R.id.button).setVisibility(View.INVISIBLE);
            getActivity().findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
            getActivity().findViewById(R.id.textView3).setVisibility(View.INVISIBLE);
            name.setText(mParam1.getName());
            email.setText(mParam1.getEmail());
            sis = (RadioButton) getActivity().findViewById(R.id.rbSIS);
            cs = (RadioButton) getActivity().findViewById(R.id.rbCS);
            bio = (RadioButton) getActivity().findViewById(R.id.rbBIO);
            others = (RadioButton) getActivity().findViewById(R.id.rbOthers);
            if(mParam1.department==sis.getText())
                sis.setChecked(true);
            else if(mParam1.department==cs.getText())
                cs.setChecked(true);
            else if(mParam1.department==bio.getText())
                bio.setChecked(true);
            else
                others.setChecked(true);

            mood.setProgress(Integer.valueOf(mParam1.getMood()));
            switch (mParam2) {
                case 0:
                    name.setVisibility(View.VISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    department.setVisibility(View.INVISIBLE);
                    mood.setVisibility(View.INVISIBLE);

                    break;
                case 1:
                    name.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.VISIBLE);
                    department.setVisibility(View.INVISIBLE);
                    mood.setVisibility(View.INVISIBLE);


                    break;
                case 2:
                    name.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    mood.setVisibility(View.INVISIBLE);
                    getActivity().findViewById(R.id.textView2).setVisibility(View.VISIBLE);

                    department.setVisibility(View.VISIBLE);

                    break;

                case 3:
                    name.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    department.setVisibility(View.INVISIBLE);
                    mood.setVisibility(View.VISIBLE);
                    getActivity().findViewById(R.id.textView3).setVisibility(View.VISIBLE);

                    break;
            }
            getActivity().findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String selectedDept = ((RadioButton) getActivity().findViewById(department.getCheckedRadioButtonId())).getText().toString();
                    Log.d("dep", selectedDept + "");
                    moodstring = String.valueOf(mood.getProgress());
                    mParam1.name = name.getText().toString();
                    mParam1.email = email.getText().toString();
                    mParam1.department = selectedDept;
                    mParam1.mood = moodstring;
                    mListener.sendDatatodisplay(mParam1);
                }
            });
        }

        else {
            getActivity().findViewById(R.id.btnSave).setEnabled(false);
            getActivity().findViewById(R.id.btnSave).setVisibility(View.INVISIBLE);
            getActivity().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (name.getText() != null && email.getText() != null && department.getCheckedRadioButtonId() != -1) {
                        if (checkEmail(email))
                        {
                            String selectedDept = ((RadioButton) getActivity().findViewById(department.getCheckedRadioButtonId())).getText().toString();
                            Log.d("dep", selectedDept + "");
                            moodstring = String.valueOf(mood.getProgress());
                            Student student = new Student();
                            student.name = name.getText().toString();
                            student.email = email.getText().toString();
                            student.department = selectedDept;
                            student.mood = moodstring;
                            mListener.sendDatatodisplay(student);
                        }
                        else
                            Toast.makeText(getActivity(), "Enter a valid e-mail.", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "Please fill in all the details ", Toast.LENGTH_SHORT).show();

                }

            });
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
        void sendDatatodisplay(Student student);
    }
    public static boolean checkEmail(EditText email) {
        String emailReg = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.getText().toString().matches(emailReg)) {
            return true;
        }
        else
        {
            return false;
        }

    }
}
