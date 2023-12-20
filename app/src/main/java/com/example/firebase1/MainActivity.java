package com.example.firebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference databaseReference = database.getReference("User");



        User user = new User("Krishna" , "krishna@gmail.com", "NEC","Narasaraopet","996644562");

        databaseReference.setValue(user);

        TextView textView = findViewById(R.id.textView);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView4 = findViewById(R.id.textView4);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    if (snapshot.hasChild("name") && snapshot.hasChild("email") &&
                            snapshot.hasChild("college") && snapshot.hasChild("address")
                            && snapshot.hasChild("mobile")){
                        User user1 = snapshot.getValue(User.class);
                        textView.setText("name :" +user1.getName());
                        textView1.setText("Email :" +user1.getEmail());
                        textView2.setText("College :" +user1.getCollege());
                        textView3.setText("address :" +user1.getAddress());
                        textView4.setText("Mobile :" +user1.getMobile());
                    }
                    else
                    {
                        String message = snapshot.getValue(String.class);
                        textView.setText(message);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        });
    }
}