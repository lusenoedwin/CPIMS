package com.example.cpims;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Button Login;

    private EditText emailid;
    private EditText pass;
    private CheckBox showpass;

    ProgressBar progresslogin;
    boolean valid = true;
    FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progresslogin=findViewById(R.id.progresslogin);
        emailid = findViewById(R.id.txtmail);
        pass = findViewById(R.id.txtpassword);
        Login = findViewById(R.id.btnsignin);
        showpass= findViewById(R.id.checkboxshow);

       // mAuth = FirebaseAuth.getInstance();


        showpass.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // show password
                pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                // hide password
                pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        });



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkField(emailid);
                checkField(pass);

                progresslogin.setVisibility(View.VISIBLE);


                if (valid){
                    fAuth.signInWithEmailAndPassword(emailid.getText().toString(), pass
                            .getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(AuthResult authResult) {
//                            Toast.makeText(MainActivity.this,"Login Success",  Toast.LENGTH_SHORT).show();

                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
                            TextView tv = layout.findViewById(R.id.txtvw);
                            tv.setText("Login Success");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();

                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
//                     Toast.makeText(MainActivity.this,"Error!! check login credentials", Toast.LENGTH_SHORT).show();

                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.customtoast_failed, findViewById(R.id.custom_toast_failed));
                            TextView tv = layout.findViewById(R.id.txtvw);
                            tv.setText("Error!! Check Login Credentials!!");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 100);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    });

                }
            }
        });





    }

    private void checkUserAccessLevel(String uid) {

        DocumentReference df = fStore.collection("users").document(uid);

        //extract the data from the document

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Log.d("TAG","onSucess: "+ documentSnapshot.getData());

                //Identify the user access level

                if (documentSnapshot.getString("isAdmin") !=null){
                    //isAdmin
                    progresslogin.setVisibility(View.VISIBLE);

                    startActivity(new Intent(getApplicationContext(),AdminNavActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isUser") !=null){

                    progresslogin.setVisibility(View.VISIBLE);
                    startActivity(new Intent(getApplicationContext(),useractivity.class));
                    finish();
                }
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



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fAuth.signOut();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onPause() {
        super.onPause();
        fAuth.signOut();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}