package com.example.cpims;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class childhomeAdapter extends FirestoreRecyclerAdapter <childhomeModel, childhomeAdapter.childhomeHolder>{




    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public childhomeAdapter(@NonNull FirestoreRecyclerOptions<childhomeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull childhomeAdapter.childhomeHolder holder, int position, @NonNull childhomeModel model) {
        holder.name.setText(model.getName());
        holder.childhome.setText(model.getChildhome());
        holder.dob.setText(model.getDob());
        holder.childhomereg.setText(model.getChildhomereg());
    }

    @NonNull
    @Override
    public childhomeAdapter.childhomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customchildhome,
                parent,false);
        return new childhomeHolder(v);
    }

    class childhomeHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView childhome;
        TextView dob;
        TextView childhomereg;

        public childhomeHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.getname);
            childhome = itemView.findViewById(R.id.chldhome);
            dob = itemView.findViewById(R.id.dob);
            childhomereg = itemView.findViewById(R.id.chldhomereg);
        }
    }




}
