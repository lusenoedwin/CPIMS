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

public class riskenvAdapter extends FirestoreRecyclerAdapter<riskenvmodel, riskenvAdapter.riskenvHolder> {
    FirebaseFirestore db;


    private riskenvAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public riskenvAdapter(@NonNull FirestoreRecyclerOptions<riskenvmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(riskenvAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull riskenvAdapter.riskenvHolder holder, int position, @NonNull riskenvmodel model) {
        holder.childname.setText(model.getChildname());
        holder.fname.setText(model.getFname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deleteriskenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("riskenv").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("riskenv").document().getId();
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
    public riskenvAdapter.riskenvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customriskenv,
                parent,false);
        return new riskenvAdapter.riskenvHolder(v);

    }

    class riskenvHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView fname;
        TextView dob;
        TextView residence;
        ImageView deleteriskenv;

        public riskenvHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            fname = itemView.findViewById(R.id.fname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deleteriskenv= itemView.findViewById(R.id.deleteriskenv);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deleteriskenv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}
