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
import br.edu.ifsp.dmos.mvp.HiredServiceMVP;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.presenter.HiredServicePresenter;
import br.edu.ifsp.dmos.presenter.ServiceOfferedPresenter;
import br.edu.ifsp.dmos.view.ItemClickListener;

public class HiredServiceAdapter extends FirestoreRecyclerAdapter<Service, HiredServiceAdapter.Holder> {

    private ItemClickListener clickListener;

    private HiredServiceMVP.Presenter presenter;

    private HiredServiceMVP.View view;

    private Context context;

    public HiredServiceAdapter(@NonNull FirestoreRecyclerOptions<Service> options, HiredServiceMVP.View view,
                               Context context) {
        super(options);
        this.view = view;
        this.context = context;
        presenter = new HiredServicePresenter(view, context);
    }

    @Override
    protected void onBindViewHolder(@NonNull HiredServiceAdapter.Holder holder, int position, @NonNull Service model) {

        holder.nomeTextView.setText(model.getNomeServico());
        holder.dataTextView.setText(String.valueOf(model.getDate()));
        holder.statusTextView.setText(model.getStatus());
        holder.nomeProfTextView.setText(model.getNomeProfissional());

        final int finalPosition = position;

        holder.imagAval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceId = getSnapshots().getSnapshot(finalPosition).getId();
                presenter.avaliat(serviceId);
            }
        });

        holder.imagExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serviceId = getSnapshots().getSnapshot(finalPosition).getId();
                presenter.avaliat(serviceId);
            }
        });



    }

    @NonNull
    @Override
    public HiredServiceAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_contract_view, parent, false);
        HiredServiceAdapter.Holder holder = new HiredServiceAdapter.Holder(view);
        return holder;
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nomeTextView;
        public TextView dataTextView;
        public TextView statusTextView;
        public TextView nomeProfTextView;

        public ImageView imagExcluir;
        public ImageView imagAval;

        public Holder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_title_listitemContratado);
            dataTextView = itemView.findViewById(R.id.text_date);
            statusTextView = itemView.findViewById(R.id.text_status);
            nomeProfTextView = itemView.findViewById(R.id.text_namePro);
            imagExcluir = itemView.findViewById(R.id.image_delet);
            imagAval = itemView.findViewById(R.id.image_Avaliativ);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(getSnapshots().getSnapshot(getBindingAdapterPosition()).getId());
            }
        }
    }
}
