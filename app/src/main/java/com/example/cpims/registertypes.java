package com.example.cpims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class registertypes extends AppCompatActivity {
    EditText fullName,email,password,phone,registerResidence;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ArrayList<String>arrayList = new ArrayList<>();
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerusers);


        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        registerResidence = findViewById(R.id.registerResidence);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Spinner spinner = (Spinner)findViewById(R.id.typeuser);








//
//        String text = spinner.getSelectedItem().toString();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);
                checkField(registerResidence);
//                checkField(text);

                if (valid) {

                    fAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser user = fAuth.getCurrentUser();

//                            Toast.makeText(registertypes.this, "Account created Successfully",
//                                    Toast.LENGTH_SHORT).show();
//

                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
                            TextView tv = layout.findViewById(R.id.txtvw);
                            tv.setText("Account created Successfully");
                            Toast toast = new Toast(registertypes.this);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();

                            DocumentReference df = fStore.collection("users").document(user.getUid());

                            Map<String, Object> userinfo = new HashMap<>();
                            userinfo.put("FullName", fullName.getText().toString());
                            userinfo.put("UserEmail", email.getText().toString());
                            userinfo.put("PhoneNumber", phone.getText().toString());
                            userinfo.put("Residence", registerResidence.getText().toString());
//                            userinfo.put("UserType",text);


                            // specify if the user is admin

                            userinfo.put("isUser", "1");







                            df.set(userinfo);



                            startActivity(new Intent(getApplicationContext(), AdminNavActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
//                            Toast.makeText(registertypes.this, "Failed to create Account", Toast.LENGTH_SHORT).show();


                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.customtoast_failed, findViewById(R.id.custom_toast_failed));
                            TextView tv = layout.findViewById(R.id.txtvw);
                            tv.setText("Failed to create Account");
                            Toast toast = new Toast(registertypes.this);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                            toast.setView(layout);
                            toast.show();

                        }
                    });
                }

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_user, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.GRAY);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
//
//            startActivity(new Intent(getApplicationContext(),AdminNavActivity.class));
//
//            finish();
//        }
//    }



}
