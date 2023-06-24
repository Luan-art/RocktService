package br.edu.ifsp.dmos.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.presenter.ServiceOfferedPresenter;
import br.edu.ifsp.dmos.view.ItemClickListener;

public class ServiceOfferedAdapter extends FirestoreRecyclerAdapter<Service, ServiceOfferedAdapter.Holder> {

    private ItemClickListener clickListener;

    private ServiceOfferedMVP.Presenter presenter;

    private ServiceOfferedMVP.View view;
    private Context context;
    public ServiceOfferedAdapter(@NonNull FirestoreRecyclerOptions<Service> options, ServiceOfferedMVP.View view,
                                 Context context) {
        super(options);
        this.view = view;
        this.context = context;
        presenter = new ServiceOfferedPresenter(view, context);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_offered, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Service model) {

        holder.nomeTextView.setText(model.getNomeServico());
        holder.precoTextView.setText(String.valueOf(model.isMediaPreco()));

        holder.edittClickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.delet(model);
            }
        });

        holder.deletClickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.edit(model);
            }
        });


    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nomeTextView;
        public TextView precoTextView;

        public ImageView edittClickImg;

        public ImageView deletClickImg;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_title_listitem);
            precoTextView = itemView.findViewById(R.id.text_cost_listitem);
            edittClickImg = itemView.findViewById(R.id.image_edit);
            deletClickImg = itemView.findViewById(R.id.image_delet);
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
