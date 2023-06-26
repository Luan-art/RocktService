package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.mvp.ServiceDescriptionMVP;
import br.edu.ifsp.dmos.view.ServiceDescriptionActivity;
import br.edu.ifsp.dmos.view.ServiceSolicitationActivity;

public class ServiceDescriptionPresenter implements ServiceDescriptionMVP.Presenter {

    private ServiceDescriptionMVP.View view;
    private Context context;

    public ServiceDescriptionPresenter(ServiceDescriptionMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void deatach() {
        this.view= null;
    }

    @Override
    public void contratarTarefa(Bundle bundle) {
        Intent intent = new Intent(context, ServiceSolicitationActivity.class);
        context.startActivity(intent);
    }

    /*
    @Override
    public Service findService() {

        Service service = new Service();
        service.retornarService("FsFhmfLPemvxSIzuGSlW");

        return service;
    }

    */

    /*
    public User findUser() {

        User user = user.retornarUser();

        return service;
    }
    */
}
