package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.view.ServiceDescriptionActivity;

public class ListServiceByCategoryRecyclerPresenter implements ListServiceByCategoryMVP.Adapter{

    private Context context;

    private ListServiceByCategoryMVP.Adapter adapter;

    @Override
    public void descricaoServico(Context context) {
        Intent intent = new Intent(context, ServiceDescriptionActivity.class);
        context.startActivity(intent);
    }
}
