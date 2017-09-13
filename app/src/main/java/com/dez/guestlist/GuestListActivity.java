/*
Lab 3
Programmer: Dez Hunter
Date: September 6, 2017
File Name: GuestList
Purpose: Show the contents of a GuestList
 */


package com.dez.guestlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GuestListActivity extends AppCompatActivity {

    EditText mEnterGuestET; //Instance variable name typically prefaced with m
    Button mSaveGuestButton; //Makes it easier to distriguish betweeen local / global vars
    TextView mGuestListTV;  //Global mGuestList texst view variable

    ArrayList<String> mGuests;

    private final static String GUEST_LIST_KEY = "guest list budle key";

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Make Visibile
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        if(savedInstanceState !=null){
            //There's some state data - check to see if there's a guest list arrag
            mGuests = savedInstanceState.getStringArrayList(GUEST_LIST_KEY);
            //if there's no data for the key, mGuest's will be set to null

        }

        //If there was no saved InstanceState, or no data for the key, create a New Array
        if (mGuests == null){
                  mGuests = new ArrayList<String>();
        }

        mEnterGuestET = (EditText) findViewById(R.id.guest_name_edit_text); //Initialize Creation of the Guest name text
        mSaveGuestButton = (Button) findViewById(R.id.save_button); //Initialize Creation of the save button
        mGuestListTV = (TextView) findViewById(R.id.guest_list_text_view); //Initialize Creation of the guest list text view

       updateGuestList(); //Update guest List, so data from the bundle is displayed

        mSaveGuestButton.setOnClickListener(new View.OnClickListener(){ //Place a listener on the SavedGuestButton
            @Override
            public void onClick (View v){

                String newGuestName = mEnterGuestET.getText().toString(); //Get the string, and text
                //Make sure user has entered some text
                if (newGuestName.length() >0) {  //If the length on this newGuestName is greater then add newGuest
                    mGuests.add(newGuestName); //Add newGuestName to mGuests
                    mEnterGuestET.getText().clear(); //Clear context of EditText, ready for new
                    GuestListActivity.this.updateGuestList(); //

                }else{
                    Toast.makeText(GuestListActivity.this, //make Toast PLease enter a guest name
                            "Please enter a guest name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateGuestList(){  //Create private class updateGuestList
        //Create a string from all of the names in the MGuest list, seperated by newlines
        String displayString = ""; //Display String of Data
        for (String name : mGuests){
            displayString = displayString + name +"\n"; //Display a name and then add a name
        }
        mGuestListTV.setText(displayString);  //SetTezt display string on mGuestListTV object
    }
    @Override
    protected void onSaveInstanceState(Bundle outBundle){ //Create Save instance State
            outBundle.putStringArrayList(GUEST_LIST_KEY, mGuests); //Put StringDataType, with String, Int, String Array

    }
}



