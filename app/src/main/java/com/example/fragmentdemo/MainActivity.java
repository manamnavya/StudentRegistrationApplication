package com.example.fragmentdemo;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener,DisplayFragment.OnFragmentInteractionListener {
    String selectedDept;
    Button submitButton;
    EditText name;
    EditText email;
    RadioGroup department;
    RadioButton sis;
    RadioButton cs;
    RadioButton bio;
    RadioButton others;
    String moodstring;
    SeekBar mood;
    Student data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentManager().beginTransaction().add(R.id.linearLayout, new MainFragment(), "Main_fragment").commit();
setTitle("Main Activity");
    }



    @Override
    public void sendDatatodisplay(Student student) {
        this.data=student;
        if(data!=null) {
            getFragmentManager().beginTransaction().replace(R.id.linearLayout, DisplayFragment.newInstance(data), "display_fragment").commit();
            Log.d("tag",data.toString());
            setTitle("Display Activity");
        }
        }


    @Override
    public void sendDatatoedit(Student student, Integer flag) {
        getFragmentManager().beginTransaction().replace(R.id.linearLayout, MainFragment.newInstance(student,flag), "Main_frag").addToBackStack(null).commit();
        Log.d("tag2",flag.toString()+student.toString());
        setTitle("Edit Activity");

    }
}

