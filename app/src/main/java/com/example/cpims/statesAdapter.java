package com.example.cpims;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class statesAdapter extends FirestoreRecyclerAdapter<statesmodel, statesAdapter.statesHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public statesAdapter(@NonNull FirestoreRecyclerOptions<statesmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull statesHolder holder, int position, @NonNull statesmodel model) {
//        holder.state.setText(model.getName());
//        holder.statesname.setText(model.getStatus());
//        holder.dob.setText(model.getDob());
//        holder.residence.setText(model.getResidence());

        holder.state.setText(model.getStatename());
        holder.regno.setText(model.getStateregno());
        holder.loc.setText(model.getLocation());
        holder.telephone.setText(model.getTel());

    }

    @NonNull
    @Override
    public statesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.state,
                parent,false);
        return new statesHolder(v);
    }

    class statesHolder extends RecyclerView.ViewHolder{
        TextView state;
        TextView regno;
        TextView loc;
        TextView telephone;
        public statesHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.state);
            regno = itemView.findViewById(R.id.regno);
            loc = itemView.findViewById(R.id.loc);
            telephone = itemView.findViewById(R.id.telephone);
        }
    }
}
