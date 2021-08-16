package com.example.cpims;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class vulnerableAdapter extends FirestoreRecyclerAdapter<vulnerablemodel, vulnerableAdapter.vulnerableHolder> {


    FirebaseFirestore db;


    private vulnerableAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public vulnerableAdapter(@NonNull FirestoreRecyclerOptions<vulnerablemodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(vulnerableAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull vulnerableAdapter.vulnerableHolder holder, int position, @NonNull vulnerablemodel model) {
        holder.childname.setText(model.getChildname());
        holder.fname.setText(model.getFname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletevulnerable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("vulnerable").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("vulnerable").document().getId();
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
    public vulnerableAdapter.vulnerableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vulnerablecustom,
                parent,false);
        return new vulnerableAdapter.vulnerableHolder(v);

    }

    class vulnerableHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView fname;
        TextView dob;
        TextView residence;
        ImageView deletevulnerable;

        public vulnerableHolder(@NonNull View itemView) {
            super(itemView);
            childname = itemView.findViewById(R.id.getchildname);
            fname = itemView.findViewById(R.id.fname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletevulnerable= itemView.findViewById(R.id.deletevulnerable);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletevulnerable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}


