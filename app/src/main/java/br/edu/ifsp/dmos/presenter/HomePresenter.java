package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.view.ListServiceByCategoryActivity;
import br.edu.ifsp.dmos.view.MenuActivity;

public class HomePresenter implements HomeMVP.Presenter {

    private HomeMVP.View view;
    private Context context;

    public HomePresenter(HomeMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void GoToMenu(Bundle bundle) {


        Intent intent = new Intent(context, MenuActivity.class);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void BuscarTema(String tema) {
        Intent intent = new Intent(context, ListServiceByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tema", tema);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void search(String nome) {
        Intent intent = new Intent(context, ListServiceByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        Log.d("chegueiSearch", nome);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
