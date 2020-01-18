package com.cannizarro.doorbhash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN= 1;

    private String username;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner = new FirebaseAuth.AuthStateListener() {
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
                        new AuthUI.IdpConfig.EmailBuilder().build(),
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

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
                            new AuthUI.IdpConfig.EmailBuilder().build(),
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



    public void createRoom() {
        Intent intent = new Intent(getApplicationContext(), DialerScreen.class)
                .putExtra("initiator", true)
                .putExtra("username", username);
        startActivity(intent);

    }


    public void joinRoom(){
        Intent intent = new Intent(getApplicationContext(), RoomList.class)
                .putExtra("initiator", false)
                .putExtra("username", username);;
        startActivity(intent);

    }

    public void signOut(){
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


