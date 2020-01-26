package com.cannizarro.doorbhash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RoomList extends AppCompatActivity {

    ListView listView;
    String roomName;
    String username;
    ArrayList<String> roomList;
    ArrayAdapter<String> adapter;

    ChildEventListener listener;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        roomList = new ArrayList<>();
        firebaseDatabase = MainActivity.firebaseDatabase;

        attachListener();

        listView=findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, roomList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                roomName=roomList.get(i);
                Intent intent1 = new Intent(getApplicationContext(), DialerScreen.class)
                        .putExtra("initiator", false)
                        .putExtra("username", username)
                        .putExtra("roomname", roomName);

                Log.d("Asrar Debug", roomName + " : Roomname");

                //Roomkeys is the real room name which enables us to have multiple rooms of same name

                startActivity(intent1);
            }
        });
    }

    private void attachListener(){

        if(listener == null){
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    roomList.add(dataSnapshot.getKey());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            firebaseDatabase.getReference("rooms").addChildEventListener(listener);
        }

    }
}
