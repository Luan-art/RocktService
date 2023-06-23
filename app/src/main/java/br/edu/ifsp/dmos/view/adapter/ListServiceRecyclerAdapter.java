package br.edu.ifsp.dmos.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.presenter.ListServiceByCategoryPresenter;

public class ListServiceRecyclerAdapter extends RecyclerView.Adapter<ListServiceRecyclerAdapter.ViewHolder>{

    private Context context;
    private ListServiceByCategoryPresenter presenter;
    private List<Service> data;

    public ListServiceRecyclerAdapter(Context context,ListServiceByCategoryPresenter presenter,List<Service> data){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements ListServiceByCategoryMVP.Adapter {

        public ImageView infoImageView;
        public TextView titleTextView;
        public TextView costTextView;

        public ViewHolder(View itemView){
            super(itemView);
            infoImageView = itemView.findViewById(R.id.image_info);
            titleTextView = itemView.findViewById(R.id.text_title_listitemCategory);
            costTextView = itemView.findViewById(R.id.text_cost_listitemCategory);
            infoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.
                }
            });
        }
    }
}
