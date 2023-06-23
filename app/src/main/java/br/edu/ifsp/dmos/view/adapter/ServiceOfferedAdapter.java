package br.edu.ifsp.dmos.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.view.ItemClickListener;

public class ServiceOfferedAdapter extends FirestoreRecyclerAdapter<Service, ServiceOfferedAdapter.Holder> {

    private ItemClickListener clickListener;

    public ServiceOfferedAdapter(@NonNull FirestoreRecyclerOptions<Service> options) {
        super(options);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_view, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Service model) {
        holder.nomeTextView.setText(model.getNomeServico());
        holder.precoTextView.setText(String.valueOf(model.isMediaPreco()));
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nomeTextView;
        public TextView precoTextView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_title_listitem);
            precoTextView = itemView.findViewById(R.id.text_cost_listitem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(getSnapshots().getSnapshot(getBindingAdapterPosition()).getId());
            }
        }
    }
}
