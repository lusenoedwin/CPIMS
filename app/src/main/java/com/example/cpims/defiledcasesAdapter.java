package com.example.cpims;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class defiledcasesAdapter extends FirestoreRecyclerAdapter <defiledcasesmodel, defiledcasesAdapter.defiledHolder>{


    FirebaseFirestore db;


    private  OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public defiledcasesAdapter(@NonNull FirestoreRecyclerOptions<defiledcasesmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull defiledHolder holder, int position, @NonNull defiledcasesmodel model) {
      holder.name.setText(model.getName());
      holder.status.setText(model.getStatus());
      holder.dob.setText(model.getDob());
      holder.residence.setText(model.getResidence());


      holder.deletedefiled.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


             db.collection("defiled").document(String.valueOf(getItemId(position))).delete();
      String documentId = db.collection("defiled").document().getId();
      db.document(documentId).set(documentId);
          }
      });

        }
//
//  public boolean onOptionsItemSelected(MenuItem item){
//
//        switch (item.getItemId()){
//
//            //The delete button
//            case  R.id.deletedefiled:
//
//                deleteItem(item.getOrder());
//                return  super.onOptionsItemSelected(item);
//
//            default:
//                return false;
//        }
//  }
    @NonNull
    @Override
    public defiledHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.defiledcustom,
                parent,false);
        return new defiledHolder(v);

    }

    class defiledHolder extends RecyclerView.ViewHolder{
               TextView name;
               TextView status;
               TextView dob;
               TextView residence;
               ImageView deletedefiled;

        public defiledHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.getdefiled);
            status = itemView.findViewById(R.id.status);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletedefiled= itemView.findViewById(R.id.deletedefiled);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletedefiled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}
