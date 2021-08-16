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

public class vaneAdapter extends FirestoreRecyclerAdapter<vanemodel, vaneAdapter.vaneHolder> {
    FirebaseFirestore db;


    private vaneAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public vaneAdapter(@NonNull FirestoreRecyclerOptions<vanemodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(vaneAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull vaneAdapter.vaneHolder holder, int position, @NonNull vanemodel model) {
        holder.childname.setText(model.getChildname());
        holder.fname.setText(model.getFname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletevane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("Vane").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("Vane").document().getId();
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
    public vaneAdapter.vaneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customvane,
                parent,false);
        return new vaneAdapter.vaneHolder(v);

    }

    class vaneHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView fname;
        TextView dob;
        TextView residence;
        ImageView deletevane;

        public vaneHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            fname = itemView.findViewById(R.id.fname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletevane= itemView.findViewById(R.id.deletevane);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletevane.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}
