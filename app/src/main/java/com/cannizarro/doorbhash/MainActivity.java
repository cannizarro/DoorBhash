package com.cannizarro.doorbhash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN= 1;

    private String username;
    private String roomName;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;
    static FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    EditText roomText;
    Button createRoomButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("rooms");

        roomText = findViewById(R.id.roomName);
        createRoomButton = findViewById(R.id.create);
        createRoomButton.setEnabled(false);

        mFirebaseAuth = FirebaseAuth.getInstance();

        roomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    createRoomButton.setEnabled(true);
                } else {
                    createRoomButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {


                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser != null)
                {
                    //signed in
                    onSignedInInitialize(firebaseUser.getDisplayName());
                }
                else
                {
                    //signed out
                    onSignedOutCleanup();
                    // Choose authentication providers
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.GoogleBuilder().build());

                    // Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };

    }



    public void createRoom(View v) {

        //Creating room. Adding a child named what's in the edit text
        roomName = roomText.getText().toString();
        roomText.setText(null);
        //String roomKey;


        //roomKey = databaseReference.push().getKey();
        databaseReference.setValue(roomName);

        Intent intent = new Intent(getApplicationContext(), DialerScreen.class)
                .putExtra("initiator", true)
                .putExtra("username", username)
                .putExtra("roomname", roomName);
        startActivity(intent);

    }


    public void joinRoom(View v){
        Intent intent = new Intent(getApplicationContext(), RoomList.class)
                .putExtra("username", username);
        startActivity(intent);

    }

    public void signOut(View v){
        AuthUI.getInstance().signOut(getApplicationContext());

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_OK)
            {
                //Signed IN
                Toast.makeText(this, "Signed In.", Toast.LENGTH_SHORT).show();

            }
            else if(resultCode == RESULT_CANCELED)
            {
                //Signed out
                Toast.makeText(this, "Exiting", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListner);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListner != null){
            mFirebaseAuth.removeAuthStateListener(mAuthStateListner);
        }
    }
    private void onSignedInInitialize(String username)
    {
        this.username=username;
    }
    private void onSignedOutCleanup()
    {
        this.username=ANONYMOUS;
    }


}


