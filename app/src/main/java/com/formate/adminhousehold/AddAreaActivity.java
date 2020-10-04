package com.formate.adminhousehold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddAreaActivity extends AppCompatActivity {

    Spinner spinner;
    EditText add_area;
    Button addarea_btn;

    String textData = "";

    DatabaseReference databaseReference;

    //getDSpin
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_area);

        add_area = findViewById(R.id.add_area);
        addarea_btn = findViewById(R.id.addarea_btn);
        spinner = findViewById(R.id.myspinner);

        databaseReference = FirebaseDatabase.getInstance().getReference("Areas");

        //getDSpin

        spinnerDataList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(AddAreaActivity.this,
                R.layout.support_simple_spinner_dropdown_item,spinnerDataList);

        spinner.setAdapter(adapter);
        retrieveData();

    }

    public void btnAddData(View view) {

        textData = add_area.getText().toString().trim();
        databaseReference.push().setValue(textData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                add_area.setText("");
                spinnerDataList.clear();
                retrieveData();
                adapter.notifyDataSetChanged();
                Toast.makeText(AddAreaActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //getDSpin
    public void retrieveData(){
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot item:dataSnapshot.getChildren()){

                    spinnerDataList.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
