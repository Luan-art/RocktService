package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        //presenter = new ServiceSolicitionForYouPresenter(this, this);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Procurar solicitados para você");

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

    private void findById() {
        mRecyclerView = findViewById(R.id.recyclerview_service_solicitionForYou);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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