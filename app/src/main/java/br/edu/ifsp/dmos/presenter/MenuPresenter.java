package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.dmos.mvp.MenuMVP;
import br.edu.ifsp.dmos.view.EditProfileActivity;
import br.edu.ifsp.dmos.view.HiredActivity;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.ServiceOfferedActivity;
import br.edu.ifsp.dmos.view.ServiceSolicited;

public class MenuPresenter implements MenuMVP.Presenter {

    private Context context;
    private MenuMVP.View view;

    public MenuPresenter(MenuMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void visualizarPerfil() {
        Intent intent = new Intent(context, EditProfileActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void backToHome() {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void servicosContratados() {
        Intent intent = new Intent(context, HiredActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void servicosOferecidos() {
        Intent intent = new Intent(context, ServiceOfferedActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void servicosSolicitados() {
        Intent intent = new Intent(context, ServiceSolicited.class);
        context.startActivity(intent);

    }

    @Override
    public void servicosFavoritos() {
        Intent intent = new Intent(context, Service.class);
        context.startActivity(intent);

    }

    @Override
    public void alterSenha() {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void exist() {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);

    }
}
