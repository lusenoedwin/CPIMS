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

public class sassAdapter  extends FirestoreRecyclerAdapter<sassmodel, sassAdapter.sassHolder> {
    FirebaseFirestore db;


    private sassAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public sassAdapter(@NonNull FirestoreRecyclerOptions<sassmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(sassAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull sassAdapter.sassHolder holder, int position, @NonNull sassmodel model) {
        holder.childname.setText(model.getChildname());
        holder.fname.setText(model.getFname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletesass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("sass").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("sass").document().getId();
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
    public sassAdapter.sassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customsass,
                parent,false);
        return new sassAdapter.sassHolder(v);

    }

    class sassHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView fname;
        TextView dob;
        TextView residence;
        ImageView deletesass;

        public sassHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            fname = itemView.findViewById(R.id.fname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletesass= itemView.findViewById(R.id.deletesass);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletesass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }
}
