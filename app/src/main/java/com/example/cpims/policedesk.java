package com.example.cpims;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class policedesk extends AppCompatActivity {

    Spinner category,text;
    EditText childname,placeofresidence,placeofaction,idno,suspectname;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policedesk);

        category= findViewById(R.id.category);
        childname=findViewById(R.id.childname);
        placeofresidence=findViewById(R.id.placeofresidence);
        placeofaction=findViewById(R.id.placeofaction);
        idno = findViewById(R.id.idno);
        suspectname=findViewById(R.id.suspectname);
        submit= findViewById(R.id.submit);



        final FirebaseFirestore firebaseFirestore;
        final DocumentReference ref;
        firebaseFirestore=FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("states").document();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(childname.getText().toString().equals("")) {
                    Toast.makeText(policedesk.this, "Please type child name", Toast.LENGTH_SHORT).show();

                }else if(placeofresidence.getText().toString().equals("")) {
                    Toast.makeText(policedesk.this, "Please type  place of residence", Toast.LENGTH_SHORT).show();

                }else if(placeofaction.getText().toString().equals("")){
                    Toast.makeText(policedesk.this, "Please type  place of action", Toast.LENGTH_SHORT).show();

//                }else if(category.getText().toString().equals("")) {
//                    Toast.makeText(police_desk.this, "Please type state tel", Toast.LENGTH_SHORT).show();
//
                }else if(idno.getText().toString().equals("")) {
                    Toast.makeText(policedesk.this, "Please type suspect Idno", Toast.LENGTH_SHORT).show();

                }else if(suspectname.getText().toString().equals("")) {
                    Toast.makeText(policedesk.this, "Please type suspect name", Toast.LENGTH_SHORT).show();



                }else {

                    ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            if (documentSnapshot.exists())
                            {
                                Toast.makeText(policedesk.this, "Sorry,this case already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                Map<String, Object> reg_entry = new HashMap<>();
                                reg_entry.put("childname",childname.getText().toString());
                                reg_entry.put("placeofresidence", placeofresidence.getText().toString());
                                reg_entry.put("placeofaction", placeofaction.getText().toString());
//                                reg_entry.put("category", category.getText().toString());
                                reg_entry.put("idno", idno.getText().toString());
                                reg_entry.put("suspectname", suspectname.getText().toString());




//                              String myId = ref.getId();
                                firebaseFirestore.collection("states")
                                        .add(reg_entry)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(policedesk.this, "Case Successfully filed", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("Error", e.getMessage());
                                            }
                                        });
                            }
                        }
                    });
                }
            }
        });





        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.CYAN);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}