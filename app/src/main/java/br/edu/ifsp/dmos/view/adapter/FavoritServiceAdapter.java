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
import br.edu.ifsp.dmos.mvp.FavoritServiceMVP;
import br.edu.ifsp.dmos.presenter.FavoritedServicePresenter;
import br.edu.ifsp.dmos.view.ItemClickListener;

public class FavoritServiceAdapter extends FirestoreRecyclerAdapter<Service, FavoritServiceAdapter.Holder> {

    private ItemClickListener clickListener;
    private FavoritServiceMVP.Presenter presenter;
    private FavoritServiceMVP.View view;
    private Context context;

    public FavoritServiceAdapter(@NonNull FirestoreRecyclerOptions<Service> options, FavoritServiceMVP.View view, Context context) {
        super(options);
        this.view = view;
        this.context = context;
        presenter = new FavoritedServicePresenter(view, context);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Service model) {
        holder.textTitleListFavorit.setText(model.getNomeServico());
        holder.textNameFavorit.setText(model.getNomeProfissional());
        holder.textPreco.setText(String.valueOf(model.getMediaPreco()));
         holder.imageDeletFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.deletTesk(model);
            }
        });
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_favorites, parent, false);
        return new Holder(view);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textTitleListFavorit;
        public TextView textNameFavorit;
        public TextView textPreco;
        public ImageView imageDeletFavorit;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textTitleListFavorit = itemView.findViewById(R.id.text_title_listfavorit);
            textNameFavorit = itemView.findViewById(R.id.text_nameFavorit);
            textPreco = itemView.findViewById(R.id.text_precoFavorit);
            imageDeletFavorit = itemView.findViewById(R.id.image_delet_favorit);
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
