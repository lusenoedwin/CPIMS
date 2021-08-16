package com.example.cpims;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addnewdefiledcase extends AppCompatActivity {
    Spinner sex,tribe,religion,subcounty,ward,relationship;String text = "";
    EditText childname, dob,residence,birthreg,country,caregivername,caregiverphone,cstatement,caregiverid;
    Button regdefiledcase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewdefiledcase);
        sex=findViewById(R.id.sex);
        tribe=findViewById(R.id.tribe);
        religion=findViewById(R.id.religion);
        subcounty=findViewById(R.id.subcounty);
        ward=findViewById(R.id.ward);
        relationship=findViewById(R.id.relationship);
        childname = findViewById(R.id.childname);
        dob = findViewById(R.id.dob);
        residence = findViewById(R.id.residence);
        birthreg=findViewById(R.id.birthreg);
        country=findViewById(R.id.country);
        caregivername=findViewById(R.id.caregivername);
        caregiverphone=findViewById(R.id.caregiverphone);
        cstatement= findViewById(R.id.cstatement);
        caregiverid= findViewById(R.id.caregiverid);
        regdefiledcase = findViewById(R.id.regdefiledcase);

        final FirebaseFirestore firebaseFirestore;
        final DocumentReference ref;
        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("defiled").document();





        regdefiledcase.setOnClickListener((View view) -> {
            if(childname.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter  full names", Toast.LENGTH_SHORT).show();

            }else if(dob.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter dob", Toast.LENGTH_SHORT).show();

            }else if(birthreg.getText().toString().equals("")){
                Toast.makeText(addnewdefiledcase.this, "Please enter birth reg no:", Toast.LENGTH_SHORT).show();

            }else if(country.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter country", Toast.LENGTH_SHORT).show();

            }else if(residence.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter place of residence", Toast.LENGTH_SHORT).show();

            }else if(caregivername.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter caregiver name", Toast.LENGTH_SHORT).show();

            }else if(caregiverphone.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter caregiver Phone No", Toast.LENGTH_SHORT).show();


            }else if(caregiverid.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter Father's Phone No", Toast.LENGTH_SHORT).show();

            }else if(cstatement.getText().toString().equals("")) {
                Toast.makeText(addnewdefiledcase.this, "Please enter case statement", Toast.LENGTH_SHORT).show();



            }else {

                ref.get().addOnSuccessListener((DocumentSnapshot documentSnapshot) -> {

                    if (documentSnapshot.exists())
                    {
                        Toast.makeText(addnewdefiledcase.this, "Sorry,this case exists", Toast.LENGTH_SHORT).show();
                    } else {
                        Map<String, Object> reg_entry = new HashMap<>();
                        reg_entry.put("name",childname.getText().toString());
                        reg_entry.put("dob", dob.getText().toString());
                        reg_entry.put("residence", residence.getText().toString());
                        reg_entry.put("birth reg", birthreg.getText().toString());
                        reg_entry.put("country", country.getText().toString());
                        reg_entry.put("caregiver name", caregivername.getText().toString());
                        reg_entry.put("caregiver phone", caregiverphone.getText().toString());
                        reg_entry.put("case statement", cstatement.getText().toString());
                        reg_entry.put("caregiver ID", caregiverid.getText().toString());
                        reg_entry.put("sex", sex.getSelectedItem().toString());
                        reg_entry.put("tribe", tribe.getSelectedItem().toString());
                        reg_entry.put("religion", religion.getSelectedItem().toString());
                        reg_entry.put("sub-county", subcounty.getSelectedItem().toString());
                        reg_entry.put("ward", ward.getSelectedItem().toString());
                        reg_entry.put("relationship", relationship.getSelectedItem().toString());


//                              String myId = ref.getId();
                        firebaseFirestore.collection("defiled")
                                .add(reg_entry)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.toastcustomeverywhere, findViewById(R.id.toastcustomevery));
                                        TextView tv = layout.findViewById(R.id.txtvw);
                                        tv.setText("Case Successfully registered");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);
                                        toast.show();

//                                        Toast.makeText(addnewdefiledcase.this, " Case Successfully registered", Toast.LENGTH_LONG).show();
                                        Intent intent= new Intent(addnewdefiledcase.this,AdminNavActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .addOnFailureListener(e -> Log.d("Error", e.getMessage()));
                    }
                });
            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sex.setAdapter(adapter);

        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.tribe, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        tribe.setAdapter(adapter1);

        tribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.religion, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        religion.setAdapter(adapter2);

        religion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.sub_county, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        subcounty.setAdapter(adapter3);

        subcounty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.wards, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ward.setAdapter(adapter4);

        ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.relationship, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        relationship.setAdapter(adapter5);

        relationship.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
                // todo get selected text and set it to a global variable
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}

