package com.example.cpims;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class childoffenders extends AppCompatActivity {
    TextView fullname,dob,dateofoffence,residence;
    FirebaseFirestore db;
    RecyclerView recycler_View1;
    FloatingActionButton addnewchildoffender;
    Context context;
    ListView listView;
    //    private final CollectionReference defiledRef = db.collection("defiled");
    private childoffendersAdapter adapter;
    ImageView sback,deletechildoffender,updatechildoffender;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childoffenders);

        fullname =findViewById(R.id.getfullname);
        db = FirebaseFirestore.getInstance();
        recycler_View1 = findViewById(R.id.recycler_view1);
        dob=findViewById(R.id.dob);
        dateofoffence=findViewById(R.id.dateofoffence);
        residence=findViewById(R.id.residence);
        addnewchildoffender=findViewById(R.id.addnewchildoffender);
        deletechildoffender=findViewById(R.id.deletechildoffender);
        updatechildoffender=findViewById(R.id.updatechildoffender);




        setUpRecyclerView();

        addnewchildoffender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(childoffenders.this,addnewchildoffenders.class);
                startActivity(intent);
            }
        });




    }


//public  void  deleteDefiledcase(@NonNull RecyclerView.ViewHolder viewHolder) {
//
//        int position = viewHolder.getAdapterPosition();
//        db.collection("defiled");
//        db.collection("defiled").getId();
//        db.document(position).delete();
//
//
//}

    private  void setUpRecyclerView(){
        Query query = db.collection("childoffenders");

        FirestoreRecyclerOptions<childoffendersmodel> options = new FirestoreRecyclerOptions.Builder<childoffendersmodel>()
                .setQuery(query, childoffendersmodel.class)
                .build();

        adapter = new childoffendersAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }



    public void onClick(View view){
        switch (view.getId()){
            case R.id.updatechildoffender:
                break;
            case  R.id.deletechildoffender:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are your sure about  this?");
                builder.setMessage("Deletion is permanent.......");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                       deleteDefiledcase();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog ad = builder.create();
                ad.show();


                break;


        }
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

