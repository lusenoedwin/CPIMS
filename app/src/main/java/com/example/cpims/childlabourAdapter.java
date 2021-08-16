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

public class childlabourAdapter extends FirestoreRecyclerAdapter<childlabourmodel, childlabourAdapter.childlabourHolder> {
    FirebaseFirestore db;


    private childlabourAdapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public childlabourAdapter(@NonNull FirestoreRecyclerOptions<childlabourmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(childlabourAdapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull childlabourAdapter.childlabourHolder holder, int position, @NonNull childlabourmodel model) {
        holder.childname.setText(model.getChildname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());
        holder.deletechildlabour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("childlabour").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("childlabour").document().getId();
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
    public childlabourAdapter.childlabourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customchildlabour,
                parent,false);
        return new childlabourAdapter.childlabourHolder(v);

    }

    class childlabourHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView dob;
        TextView residence;
        ImageView deletechildlabour;

        public childlabourHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.childname);
            dob = itemView.findViewById(R.id.dob);
            residence = itemView.findViewById(R.id.residence);
            deletechildlabour= itemView.findViewById(R.id.deletechildlabour);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deletechildlabour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }
    }


}


