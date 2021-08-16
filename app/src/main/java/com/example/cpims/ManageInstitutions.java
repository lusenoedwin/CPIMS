package com.example.cpims;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ManageInstitutions extends AppCompatActivity {




    TextView statesname,statesregno,location,telephone;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    FloatingActionButton addnew_inst;
    Context context;
    ListView listView;
    //    private final CollectionReference defiledRef = db.collection("defiled");
    private statesAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_institutions);


        statesname = findViewById(R.id.state);
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_view);
        statesregno = findViewById(R.id.regno);
        location = findViewById(R.id.loc);
        telephone = findViewById(R.id.telephone);


        setUpRecyclerView();

                addnew_inst= findViewById(R.id.addnew_institution);

                addnew_inst.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ManageInstitutions.this,registerstates.class);
                        startActivity(intent);
                    }
                });
    }




        private  void setUpRecyclerView(){
            Query query = db.collection("states");

            FirestoreRecyclerOptions<statesmodel> options = new FirestoreRecyclerOptions.Builder<statesmodel>()
                    .setQuery(query, statesmodel.class)
                    .build();

            adapter = new statesAdapter(options);
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected void onStart() {
            super.onStart();
            adapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            adapter.stopListening();
        }

    }

