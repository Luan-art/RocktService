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
import br.edu.ifsp.dmos.mvp.FavoritServiceAdapterMVP;

public class FavoritServiceAdapter extends RecyclerView.Adapter<ListServiceRecyclerAdapter.ViewHolder>{

    private FavoritServiceAdapterMVP.Presenter presenter;
    private Context context;
    private List<Service> data;

    public FavoritServiceAdapter(Context context, List<Service> data, FavoritServiceAdapterMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ListServiceRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListServiceRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;

        public ImageView deletClickImg;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_title_listitem);
            deletClickImg = itemView.findViewById(R.id.image_delet);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
