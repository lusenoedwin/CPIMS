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

public class traumacasesAdapter extends FirestoreRecyclerAdapter<traumacasesmodel, traumacasesAdapter.traumaHolder> {
    FirebaseFirestore db;


    private traumacasesAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public traumacasesAdapter(@NonNull FirestoreRecyclerOptions<traumacasesmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(traumacasesAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull traumacasesAdapter.traumaHolder holder, int position, @NonNull traumacasesmodel model) {
        holder.childname.setText(model.getChildname());
        holder.fname.setText(model.getFname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletetrauma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("Trauma").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("Trauma").document().getId();
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
    public traumacasesAdapter.traumaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customtrauma,
                parent,false);
        return new traumacasesAdapter.traumaHolder(v);

    }

    class traumaHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView fname;
        TextView dob;
        TextView residence;
        ImageView deletetrauma;

        public traumaHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            fname = itemView.findViewById(R.id.fname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletetrauma= itemView.findViewById(R.id.deletetrauma);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletetrauma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}
