package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import br.edu.ifsp.dmos.mvp.MenuMVP;
import br.edu.ifsp.dmos.view.EditProfileActivity;
import br.edu.ifsp.dmos.view.FavoritedServiceActivity;
import br.edu.ifsp.dmos.view.HiredServiceActivity;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.LoginActivity;
import br.edu.ifsp.dmos.view.ServiceOfferedActivity;
import br.edu.ifsp.dmos.view.ServiceSolicitionForYou;
import br.edu.ifsp.dmos.view.UpdatePasswordActivity;

public class MenuPresenter implements MenuMVP.Presenter {

    private Context context;
    private MenuMVP.View view;
    private String usuario;

    public MenuPresenter(MenuMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    //---------NAVEGAR PARA A TELA EDITAR PERFIL----------------------
    @Override
    public void visualizarPerfil(Bundle bundle) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //---------NAVEGAR DE VOLTA A TELA HOME----------------------------
    @Override
    public void backToHome(Bundle bundle) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    //---------NAVEGAR PARA A TELA SERVICOS CONTRATADOS-----------------
    @Override
    public void servicosContratados() {
        Intent intent = new Intent(context, HiredServiceActivity.class);
        context.startActivity(intent);
    }

    //---------NAVEGAR PARA A TELA SERVICOS OFERECIDOS-------------------
    @Override
    public void servicosOferecidos(Bundle bundle) {
        Intent intent = new Intent(context, ServiceOfferedActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //---------NAVEGAR PARA A TELA SERVICOS SOLICITADOS-------------------
    @Override
    public void servicosSolicitados() {
        Intent intent = new Intent(context, ServiceSolicitionForYou.class);
        context.startActivity(intent);
    }

    //---------NAVEGAR PARA A TELA SERVICOS FAVORITOS----------------------
    @Override
    public void servicosFavoritos() {
        Intent intent = new Intent(context, FavoritedServiceActivity.class);
        context.startActivity(intent);
    }

    //---------NAVEGAR PARA TELA SERVICOS FAVORITOS------------------------
    @Override
    public void alterSenha(Bundle bundle) {
        Intent intent = new Intent(context, UpdatePasswordActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //---------NAVEGAR PARA A TELA LOGIN AO SE DESLOGAR---------------------
    @Override
    public void exist() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
