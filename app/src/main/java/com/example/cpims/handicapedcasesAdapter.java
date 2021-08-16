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

public class handicapedcasesAdapter extends FirestoreRecyclerAdapter<handicapedcasesmodel, handicapedcasesAdapter.handicapedHolder> {
    FirebaseFirestore db;


    private handicapedcasesAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public handicapedcasesAdapter(@NonNull FirestoreRecyclerOptions<handicapedcasesmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(handicapedcasesAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull handicapedcasesAdapter.handicapedHolder holder, int position, @NonNull handicapedcasesmodel model) {
        holder.name.setText(model.getName());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());

        holder.deletehandicaped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("handicapped").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("handicapped").document().getId();
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
    public handicapedcasesAdapter.handicapedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.handicapedcustom,
                parent,false);
        return new handicapedcasesAdapter.handicapedHolder(v);

    }

    class handicapedHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView dob;
        TextView residence;
        ImageView deletehandicaped;

        public handicapedHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.gethandicaped);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletehandicaped= itemView.findViewById(R.id.deletehandicaped);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletehandicaped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}

