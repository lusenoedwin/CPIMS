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

public class separatedcases extends AppCompatActivity {
    TextView childname,dob,gname,gtell,residence;
    FirebaseFirestore db;
    RecyclerView recycler_View2;
    FloatingActionButton addnewseparated;
    Context context;
    ListView listView;
    //    private final CollectionReference defiledRef = db.collection("defiled");
    private separatedcasesadapter adapter;
    ImageView sback,deleteseparated,updateseparated;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separatedcases);

        childname =findViewById(R.id.getchildname);
        db = FirebaseFirestore.getInstance();
        recycler_View2 = findViewById(R.id.recycler_view2);
        dob=findViewById(R.id.getdob);
        gname =findViewById(R.id.getgname);
        residence=findViewById(R.id.getresidence);
        addnewseparated=findViewById(R.id.addnewseparated);
        deleteseparated =findViewById(R.id.deleteseparated);
        updateseparated =findViewById(R.id.updateseparated);




        setUpRecyclerView();

        addnewseparated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(separatedcases.this,addnewseparated.class);
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
        Query query = db.collection("separated");

        FirestoreRecyclerOptions<separatedcasesmodel> options = new FirestoreRecyclerOptions.Builder<separatedcasesmodel>()
                .setQuery(query, separatedcasesmodel.class)
                .build();

        adapter = new separatedcasesadapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }



    public void onClick(View view){
        switch (view.getId()){
            case R.id.updateseparated:
                break;
            case  R.id.deleteseparated:

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

