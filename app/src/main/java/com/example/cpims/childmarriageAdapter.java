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

public class childmarriageAdapter extends FirestoreRecyclerAdapter<childmarriagemodel, childmarriageAdapter.childmarriageHolder> {
    FirebaseFirestore db;


    private childmarriageAdapter.OnItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public childmarriageAdapter(@NonNull FirestoreRecyclerOptions<childmarriagemodel> options) {
        super(options);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(childmarriageAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull childmarriageAdapter.childmarriageHolder holder, int position, @NonNull childmarriagemodel model) {
        holder.childname.setText(model.getChildname());
        holder.sname.setText(model.getSname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletechildmarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("childmarriage").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("childmarriage").document().getId();
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
    public childmarriageAdapter.childmarriageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.childmarriagecustom,
                parent, false);
        return new childmarriageAdapter.childmarriageHolder(v);

    }

    class childmarriageHolder extends RecyclerView.ViewHolder {
        TextView childname;
        TextView sname;
        TextView dob;
        TextView residence;
        ImageView deletechildmarriage;

        public childmarriageHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            sname = itemView.findViewById(R.id.sname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletechildmarriage = itemView.findViewById(R.id.deletechildmarriage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });


            deletechildmarriage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }
}
