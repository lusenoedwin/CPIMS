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

public class separatedcasesadapter extends FirestoreRecyclerAdapter<separatedcasesmodel, separatedcasesadapter.separatedHolder> {
    FirebaseFirestore db;

    private separatedcasesadapter.OnItemClickListener mListener;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public separatedcasesadapter(@NonNull FirestoreRecyclerOptions<separatedcasesmodel> options) {
        super(options);
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);
    }

    public  void  setOnClickListener(separatedcasesadapter.OnItemClickListener listener){
        mListener= listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull separatedcasesadapter.separatedHolder holder, int position, @NonNull separatedcasesmodel model) {
        holder.childname.setText(model.getChildname());
        holder.gname.setText(model.getGname());
        holder.dob.setText(model.getDob());
        holder.residence.setText(model.getResidence());


        holder.deleteseparated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("separated").document(String.valueOf(getItemId(position))).delete();
                String documentId = db.collection("separated").document().getId();
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
    public separatedcasesadapter.separatedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.separatedcustom,
                parent,false);
        return new separatedcasesadapter.separatedHolder(v);

    }

    class separatedHolder extends RecyclerView.ViewHolder{
        TextView childname;
        TextView dob;
        TextView residence;
        TextView gname;
        ImageView deleteseparated;

        public separatedHolder(@NonNull View itemView) {
            super(itemView);

            childname = itemView.findViewById(R.id.getchildname);
            gname = itemView.findViewById(R.id.getgname);
            dob = itemView.findViewById(R.id.getdob);
            residence = itemView.findViewById(R.id.getresidence);
            deleteseparated= itemView.findViewById(R.id.deleteseparated);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });



            deleteseparated.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

        }

    }

    }



