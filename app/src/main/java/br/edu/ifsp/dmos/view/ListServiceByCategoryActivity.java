package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.ListServiceByCategoryMVP;
import br.edu.ifsp.dmos.presenter.ListServiceByCategoryPresenter;

public class ListServiceByCategoryActivity extends AppCompatActivity implements ListServiceByCategoryMVP.View{

    private ListServiceByCategoryMVP.Presenter presenter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_service_by_category);

        findByID();
        setListener();
        presenter = new ListServiceByCategoryPresenter(this, this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Listar Serviço por categoria");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setListener() {

    }

    private void findByID() {
        mRecyclerView = findViewById(R.id.recyclerview_service_socicitated_category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Procurar trabalhos oferecidos");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("tema")) {
                String tema = extras.getString("tema");
                presenter.populateByCategoria(mRecyclerView, tema);
                presenter.startListener();
            } else if (extras.containsKey("nome")) {
                String nome = extras.getString("nome");
                presenter.populateByName(mRecyclerView, nome);
                Log.d("chegueiListActivity", nome);

                presenter.startListener();
            }
        }


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

    @Override
    public Context getContext() {
        return this;
    }

}