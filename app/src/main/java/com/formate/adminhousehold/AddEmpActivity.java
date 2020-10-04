package com.formate.adminhousehold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddEmpActivity extends AppCompatActivity {

    //variables
    EditText emp_name, emp_aadhar_no, emp_phone;
    Button submit;

    Spinner spinner;
    //getDSpin
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerDataList;

    FirebaseDatabase rootNode;
    DatabaseReference reference, databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Employee");

        /*// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/

        emp_name = findViewById(R.id.emp_name);
        spinner = findViewById(R.id.myspinner);
        emp_aadhar_no = findViewById(R.id.emp_aadhar_no);
        emp_phone = findViewById(R.id.emp_phone);

        databaseReference = FirebaseDatabase.getInstance().getReference("Areas");

        //getDSpin
        spinnerDataList = new ArrayList<>();

        spinnerDataList.add(0, "Select Area from the list");
        adapter = new ArrayAdapter<String>(AddEmpActivity.this,
                R.layout.support_simple_spinner_dropdown_item, spinnerDataList);

        spinner.setAdapter(adapter);
        retrieveData();
    }

    //getDSpin
    private void retrieveData() {
        listener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot item : dataSnapshot.getChildren()) {

                    spinnerDataList.add(item.getValue().toString());

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addemp(View view) {


        //get all the values
        String name = emp_name.getText().toString();
        String phoneNo = emp_phone.getText().toString();
        String aadharNo= emp_aadhar_no.getText().toString();
        String area = spinner.getSelectedItem().toString();


        EmpHelperClass helperClass = new EmpHelperClass(name,phoneNo,aadharNo,area);

        reference.child(aadharNo).setValue(helperClass);
    }
}
