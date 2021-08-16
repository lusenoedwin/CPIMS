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

public class schooldroppedAdapter extends FirestoreRecyclerAdapter<schooldroppedmodel, schooldroppedAdapter.schooldroppedHolder> {
    FirebaseFirestore db;


    private schooldroppedAdapter.OnItemClickListener mListener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public schooldroppedAdapter(@NonNull FirestoreRecyclerOptions<schooldroppedmodel> options) {
        super(options);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(schooldroppedAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull schooldroppedAdapter.schooldroppedHolder holder, int position, @NonNull schooldroppedmodel model) {
        holder.childname.setText(model.getChildname());
        holder.schoolname.setText(model.getSchoolname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deleteschooldropped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("schooldropped").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("schooldropped").document().getId();
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
    public schooldroppedAdapter.schooldroppedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schooldroppedcustom,
                parent, false);
        return new schooldroppedAdapter.schooldroppedHolder(v);

    }

    class schooldroppedHolder extends RecyclerView.ViewHolder {
        TextView childname;
        TextView schoolname;
        TextView dob;
        TextView residence;
        ImageView deleteschooldropped;

        public schooldroppedHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            schoolname = itemView.findViewById(R.id.schoolname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deleteschooldropped = itemView.findViewById(R.id.deleteschooldropped);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });


            deleteschooldropped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }
}