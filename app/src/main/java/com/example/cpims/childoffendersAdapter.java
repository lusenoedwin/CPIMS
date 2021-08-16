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

public class childoffendersAdapter extends FirestoreRecyclerAdapter<childoffendersmodel, childoffendersAdapter.childoffendersHolder> {
    FirebaseFirestore db;


    private childoffendersAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public childoffendersAdapter(@NonNull FirestoreRecyclerOptions<childoffendersmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(childoffendersAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull childoffendersAdapter.childoffendersHolder holder, int position, @NonNull childoffendersmodel model) {
        holder.fullname.setText(model.getFullname());
        holder.dateofoffence.setText(model.getDateofoffence());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deletechildoffender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("childoffenders").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("childoffenders").document().getId();
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
    public childoffendersAdapter.childoffendersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.childoffenderscustom,
                parent,false);
        return new childoffendersAdapter.childoffendersHolder(v);

    }

    class childoffendersHolder extends RecyclerView.ViewHolder{
        TextView fullname;
        TextView dob;
        TextView residence;
        TextView dateofoffence;
        ImageView deletechildoffender;

        public childoffendersHolder(@NonNull View itemView) {
            super(itemView);

             fullname = itemView.findViewById(R.id.getfullname);
            dateofoffence = itemView.findViewById(R.id.dateofoffence);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletechildoffender= itemView.findViewById(R.id.deletechildoffender);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletechildoffender.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }


}

