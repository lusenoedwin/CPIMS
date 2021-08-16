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

public class childdispute extends AppCompatActivity {
    TextView getchildname,dob,fname,residence;
    FirebaseFirestore db;
    RecyclerView recycler_View4;
    FloatingActionButton addnewchilddispute;
    Context context;
    ListView listView;
    //    private final CollectionReference defiledRef = db.collection("defiled");
    private childdisputeAdapter adapter;
    ImageView sback,deletechilddispute,updatechilddispute;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_childdispute);

        getchildname =findViewById(R.id.getchildname);
        db = FirebaseFirestore.getInstance();
        recycler_View4 = findViewById(R.id.recycler_view4);
        dob=findViewById(R.id.getdob);
       fname=findViewById(R.id.getfname);
        residence=findViewById(R.id.getresidence);
        addnewchilddispute=findViewById(R.id.addnewchilddispute);
        deletechilddispute=findViewById(R.id.deletechilddispute);
        updatechilddispute=findViewById(R.id.updatechilddispute);




        setUpRecyclerView();

        addnewchilddispute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(childdispute.this,addnewchilddispute.class);
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
        Query query = db.collection("childdispute");

        FirestoreRecyclerOptions<childdisputemodel> options = new FirestoreRecyclerOptions.Builder<childdisputemodel>()
                .setQuery(query, childdisputemodel.class)
                .build();

        adapter = new childdisputeAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }



    public void onClick(View view){
        switch (view.getId()){
            case R.id.updatechilddispute:
                break;
            case  R.id.deletechilddispute:

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
