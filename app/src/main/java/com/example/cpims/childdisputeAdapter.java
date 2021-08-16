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

public class childdisputeAdapter extends FirestoreRecyclerAdapter<childdisputemodel, childdisputeAdapter.childdisputeHolder> {
    FirebaseFirestore db;


    private childdisputeAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public childdisputeAdapter(@NonNull FirestoreRecyclerOptions<childdisputemodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(childdisputeAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull childdisputeAdapter.childdisputeHolder holder, int position, @NonNull childdisputemodel model) {
        holder.childname.setText(model.getChildname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());
        holder.fname.setText(model.getFname());

        holder.deletechilddispute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("childdispute").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("childdispute").document().getId();
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
    public childdisputeAdapter.childdisputeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.childdisputecustom,
                parent,false);
        return new childdisputeAdapter.childdisputeHolder(v);

    }

    class childdisputeHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView dob;
        TextView residence;
        TextView fname;
        ImageView deletechilddispute;

        public childdisputeHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.childname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            fname = itemView.findViewById(R.id.fname);
            deletechilddispute= itemView.findViewById(R.id.deletechilddispute);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletechilddispute.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}

