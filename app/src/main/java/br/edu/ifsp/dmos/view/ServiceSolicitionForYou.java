package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.ServiceSolicitionForYouMVP;
import br.edu.ifsp.dmos.presenter.ServiceSolicitionForYouPresenter;

public class ServiceSolicitionForYou extends AppCompatActivity implements ServiceSolicitionForYouMVP.View {
    private ServiceSolicitionForYouPresenter presenter;

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_solicition_for_you);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Serviços Soliciatados para você");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findById();
        presenter = new ServiceSolicitionForYouPresenter(this, this);
    }

    private void findById() {
    }

    @Override
    public Context getContext() {
        return this;
    }
}