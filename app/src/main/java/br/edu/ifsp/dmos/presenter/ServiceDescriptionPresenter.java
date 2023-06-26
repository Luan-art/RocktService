package br.edu.ifsp.dmos.presenter;

import android.content.Context;

import br.edu.ifsp.dmos.model.entites.Service;
import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.mvp.ServiceDescriptionMVP;
import br.edu.ifsp.dmos.view.ServiceDescriptionActivity;

public class ServiceDescriptionPresenter implements ServiceDescriptionMVP.Presenter {

    private ServiceDescriptionMVP.View view;
    private Context context;

    public ServiceDescriptionPresenter(ServiceDescriptionMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public Service findService() {

        Service service = new Service();
        service.retornarService("FsFhmfLPemvxSIzuGSlW");

        return service;
    }

    /*
    public User findUser() {

        User user = user.retornarUser();

        return service;
    }
    */

    @Override
    public void deatach() {
        this.view= null;
    }

    @Override
    public void contratarTarefa(Service service) {

    }
}
