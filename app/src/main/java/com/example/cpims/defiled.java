package com.example.cpims;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;


public class defiled extends AppCompatActivity {
    TextView getdefiled,dob,status,residence;
    FirebaseFirestore db;
    RecyclerView recyclerView;
    FloatingActionButton addnew_defiled;
    Context context;
    ListView listView;
//    private final CollectionReference defiledRef = db.collection("defiled");
    private defiledcasesAdapter adapter;
    ImageView sback,deletedefiled,updatedefiled;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defiled);

        getdefiled=findViewById(R.id.getdefiled);
        db = FirebaseFirestore.getInstance();
        recyclerView= findViewById(R.id.recycler_view);
        dob=findViewById(R.id.dob);
        status=findViewById(R.id.status);
        residence=findViewById(R.id.residence);
        addnew_defiled=findViewById(R.id.addnew_defiled);
        deletedefiled=findViewById(R.id.deletedefiled);
        updatedefiled=findViewById(R.id.updatedefiled);




setUpRecyclerView();

addnew_defiled.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(defiled.this,addnewdefiledcase.class);
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
       Query query = db.collection("defiled");

       FirestoreRecyclerOptions<defiledcasesmodel> options = new FirestoreRecyclerOptions.Builder<defiledcasesmodel>()
               .setQuery(query, defiledcasesmodel.class)
               .build();

       adapter = new defiledcasesAdapter(options);
       RecyclerView recyclerView = findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setAdapter(adapter);

   }



   public void onClick(View view){
         switch (view.getId()){
             case R.id.updatedefiled:
                 break;
             case  R.id.deletedefiled:

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
