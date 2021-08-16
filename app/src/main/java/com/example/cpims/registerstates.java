package com.example.cpims;





        import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registerstates extends AppCompatActivity {
    Spinner registerstates;String text = "";
    EditText statename,stateregno,location,tel;
    Button regstates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerstates);

        registerstates= findViewById(R.id.typestate);
        statename=findViewById(R.id.statename);
        stateregno=findViewById(R.id.stateregno);
        location=findViewById(R.id.location);
        tel = findViewById(R.id.tel);
        regstates= findViewById(R.id.regstates);




        FirebaseFirestore db = FirebaseFirestore.getInstance();


        regstates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statename.getText().toString().equals("")) {
                    Toast.makeText(registerstates.this, "Please type states name", Toast.LENGTH_SHORT).show();

                }else if(stateregno.getText().toString().equals("")) {
                    Toast.makeText(registerstates.this, "Please type  state regno", Toast.LENGTH_SHORT).show();

                }else if(location.getText().toString().equals("")){
                    Toast.makeText(registerstates.this, "Please type  state location", Toast.LENGTH_SHORT).show();

                }else if(tel.getText().toString().equals("")) {
                    Toast.makeText(registerstates.this, "Please type state tel", Toast.LENGTH_SHORT).show();

                }else {


                    // Create a new user with a first and last name
                    Map<String, Object> user = new HashMap<>();
                    user.put("statename",statename.getText().toString());
                    user.put("stateregno", stateregno.getText().toString());
                    user.put("location", location.getText().toString());
                    user.put("tel", tel.getText().toString());
                    user.put("type", registerstates.getSelectedItem().toString());

// Add a new document with a generated ID
                    db.collection("states")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Toast.makeText(registerstates.this,"State added successfully",Toast.LENGTH_LONG).show();
                                    Intent intent= new Intent(com.example.cpims.registerstates.this,AdminNavActivity.class);
                                    startActivity(intent);
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





        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_state, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        registerstates.setAdapter(adapter);

        registerstates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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