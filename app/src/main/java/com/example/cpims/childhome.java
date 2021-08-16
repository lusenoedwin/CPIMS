package com.example.cpims;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class childhome extends AppCompatActivity {

    TextView getname,dob,getchildhome,getreg;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    FloatingActionButton addnew_child;
    Context context;
    ListView listView;

    //    private final CollectionReference defiledRef = db.collection("defiled");
    private childhomeAdapter adapter;
    ImageView sback,deletechild,updatedechild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childhome);

        getname=findViewById(R.id.getname);
        dob=findViewById(R.id.dob);
        getchildhome=findViewById(R.id.getchildhome);
        getreg=findViewById(R.id.getreg);
        db = FirebaseFirestore.getInstance();
        recyclerView= findViewById(R.id.recycler_view);
        addnew_child=findViewById(R.id.addnew_child);


        setUpRecyclerView();


        addnew_child.setOnClickListener(v -> {
            Intent intent= new Intent(childhome.this,addnewchildhome.class);
            startActivity(intent);
        });




    }


    private  void setUpRecyclerView(){
        Query query = db.collection("childrenhomes");

        FirestoreRecyclerOptions<childhomeModel> options = new FirestoreRecyclerOptions.Builder<childhomeModel>()
                .setQuery(query, childhomeModel.class)
                .build();

        adapter = new childhomeAdapter(options);
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