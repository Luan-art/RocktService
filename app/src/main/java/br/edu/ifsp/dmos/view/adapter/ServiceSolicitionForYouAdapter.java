package br.edu.ifsp.dmos.view.adapter;

import android.content.Context;
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
import br.edu.ifsp.dmos.mvp.ServiceSolicitionForYouMVP;
import br.edu.ifsp.dmos.presenter.ServiceSolicitionForYouPresenter;
import br.edu.ifsp.dmos.view.ItemClickListener;

public class ServiceSolicitionForYouAdapter extends FirestoreRecyclerAdapter<Service, ServiceSolicitionForYouAdapter.Holder> {
    private ItemClickListener clickListener;

    private ServiceSolicitionForYouMVP.Presenter presenter;

    private ServiceSolicitionForYouMVP.View view;

    private Context context;

    public ServiceSolicitionForYouAdapter(@NonNull FirestoreRecyclerOptions<Service> options, ServiceSolicitionForYouMVP.View view,
                                          Context context) {
        super(options);
        this.view = view;
        this.context = context;
        presenter = new ServiceSolicitionForYouPresenter(view, context);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Service model) {
        holder.textTitle.setText(model.getNomeServico());
        holder.textName.setText(model.getNomeServico());
        if (model.getDate() != null) {
            holder.textDate.setText(model.getDate().toString());
        } else {
            holder.textDate.setText("");
        }     //manter assim enquanto não fazemos as novas listas para adicionar Data
        holder.textTel.setText(model.getAddInfo());//arumar
        holder.textDesc.setText(model.getCategoria());//arrumar
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_solicited_by_you, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textName;
        public TextView textDate;
        public TextView textTel;
        public TextView textDesc;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title_solicitadForYou);
            textName = itemView.findViewById(R.id.text_name_SolicitadForYou);
            textDate = itemView.findViewById(R.id.text_date);
            textTel = itemView.findViewById(R.id.text_tel_solicitadForYou);
            textDesc = itemView.findViewById(R.id.text_desc_SolicitadForYou);
        }
    }
}
