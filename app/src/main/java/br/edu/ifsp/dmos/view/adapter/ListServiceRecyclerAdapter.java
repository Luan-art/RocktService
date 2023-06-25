package br.edu.ifsp.dmos.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.presenter.ListServiceByCategoryRecyclerPresenter;
import br.edu.ifsp.dmos.view.ItemClickListener;


public class ListServiceRecyclerAdapter extends FirestoreRecyclerAdapter<Service, ListServiceRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Service> data;
    private ItemClickListener clickListener;

    private ListServiceByCategoryMVP.View view;

    private ListServiceByCategoryRecyclerPresenter presenter;

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ListServiceRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Service> options, ListServiceByCategoryMVP.View view, Context context) {
        super(options);
        this.context = context;
        presenter = new ListServiceByCategoryRecyclerPresenter(); // Inicializa o presenter
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Service model) {
        holder.titleTextView.setText(model.getNomeServico());
        holder.costTextView.setText(String.valueOf(model.getMediaPreco()));

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView infoImageView;
        public TextView titleTextView;
        public TextView costTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            infoImageView = itemView.findViewById(R.id.image_info);
            titleTextView = itemView.findViewById(R.id.text_title_listitemCategory);
            costTextView = itemView.findViewById(R.id.text_cost_listitemCategory);

            infoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("Task id", "Value: o que vai ser passado ao clique" + (taskId));

                    presenter.descricaoServico(context, taskId);
                }
            });
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
