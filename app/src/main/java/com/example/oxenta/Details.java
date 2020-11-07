package com.example.oxenta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;
import java.util.Calendar;


public class Details extends AppCompatActivity {

    DatePickerDialog picker;
    EditText txtdobp;

    EditText txtinterestp;
    String[] urinterest;
    boolean[] checkdItems;
    ArrayList<Integer>mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);

       txtdobp = findViewById(R.id.txtdobp);
       txtdobp.setInputType(InputType.TYPE_NULL);

       txtdobp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Calendar cldr = Calendar.getInstance();
               int day = cldr.get(Calendar.DAY_OF_MONTH);
               int month = cldr.get(Calendar.MONTH);
               int year = cldr.get(Calendar.YEAR);

               //dialog
               picker = new DatePickerDialog(Details.this, new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int monthOFYear, int dayOfMonth) {
                    txtdobp.setText(dayOfMonth + "/" + (monthOFYear + 1) + "/" + year);
                   }
               }, year,month, day);
                       picker.show();
           }
       });

        txtinterestp= findViewById(R.id.txtinterestp);
        urinterest = getResources().getStringArray(R.array.urinterest_array);
        checkdItems = new boolean[urinterest.length];

        txtinterestp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Details.this);
                mBuilder.setTitle("Movie Categories");
                mBuilder.setMultiChoiceItems(urinterest, checkdItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked){
                            if (!mUserItems.contains(which)){
                                mUserItems.add(which);
                            }else {
                                mUserItems.remove(which);
                            }
                        }
                    }
                });
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = " ";
                        for (int i = 0; i < mUserItems.size(); i++){
                            item = item + urinterest[mUserItems.get(i)];
                            if (i != mUserItems.size()-1){
                                item = item + ",";
                            }
                        }
                        txtinterestp.setText(item);
                    }
                });
                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkdItems.length; i++){
                            checkdItems[i] = false;
                            mUserItems.clear();
                            txtinterestp.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });



    }
}




