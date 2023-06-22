package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.view.MenuActivity;

public class HomePresenter implements HomeMVP.Presenter {

    private HomeMVP.View view;
    private Context context;

    public HomePresenter(HomeMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void GoToMenu() {
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void BuscarTema(String tema) {

    }

    @Override
    public void search() {

    }
}
