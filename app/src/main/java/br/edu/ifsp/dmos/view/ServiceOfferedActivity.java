package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.ServiceOfferedMVP;
import br.edu.ifsp.dmos.presenter.ServiceOfferedPresenter;

public class ServiceOfferedActivity extends AppCompatActivity implements ServiceOfferedMVP.View, View.OnClickListener {

    private ServiceOfferedPresenter presenter;
    private FloatingActionButton mActionButton;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_offered);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Serviços Oferecidos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findById();
        setListener();
        presenter = new ServiceOfferedPresenter(this, this);
    }

    private void setListener() {
        mActionButton.setOnClickListener(this);
    }

    private void findById() {
        mActionButton = findViewById(R.id.fab_add_service);
        mRecyclerView = findViewById(R.id.recyclerview_service);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == mActionButton){
            Intent intent = new Intent(this, AddServiceActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.populate(mRecyclerView);
        presenter.startListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopListener();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

}
