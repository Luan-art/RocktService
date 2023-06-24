package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.view.HiredServiceActivity;
import br.edu.ifsp.dmos.view.ServiceDescriptionActivity;
import br.edu.ifsp.dmos.view.adapter.ListServiceRecyclerAdapter;

public class ListServiceByCategoryRecyclerPresenter implements ListServiceByCategoryMVP.Adapter{

    private Context context;

    private ListServiceByCategoryMVP.Adapter adapter;

    @Override
    public void descricaoServico(Context context) {
        Intent intent = new Intent(context, ServiceDescriptionActivity.class);
        this.context = context;
        context.startActivity(intent);

    }
}
