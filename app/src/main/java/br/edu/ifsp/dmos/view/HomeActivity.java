package br.edu.ifsp.dmos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeMVP.View{

    public static final int REQUEST_PERMISSION_CODE = 999;
    private static final String PERMISSION_GPS = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_COARSE = Manifest.permission.ACCESS_COARSE_LOCATION;
    private ImageView menuBtn;
    private SearchView search;
    private ImageButton btnTecnologia;
    private ImageButton btnEducacao;
    private ImageButton btnCasa;
    private ImageButton btnArte;
    private ImageButton btnSaude;
    private ImageButton btnEvento;
    private HomePresenter presenter;

    private TextView olaUser;
    private Bundle bundle;

    String idUsuarioBundle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findById();
        verificarPermissoes();
        setListener();

        bundle = getIntent().getExtras();
        //String usuario = bundle.getString("usuario");

        String nomeProfissional = bundle.getString("usuario");
        String idUsuario = bundle.getString("idUsuarioBundle");
        Log.d("nome Do Usuario no home activity novinho", "Value: " + (nomeProfissional));
        Log.d("nome Do Usuario no home activity", "Value: " + (idUsuario));

        olaUser.setText("Ola, " + nomeProfissional);
        presenter = new HomePresenter(this, this);
    }

    private void setListener() {
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.GoToMenu(bundle);

            }
        });

        btnTecnologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Tecnologia",bundle);
            }
        });

        btnEducacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Educação",bundle);
            }
        });

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Casa",bundle);
            }
        });

        btnArte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Arte",bundle);
            }
        });

        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Saúde",bundle);
            }
        });

        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Evento",bundle);
            }
        });

        // Adicione o setOnKeyListener para capturar a tecla "Enter"
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String query = search.getQuery().toString();
                    presenter.search(query,bundle);
                    return true;
                }
                return false;
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query, bundle);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }


    private void findById() {
        menuBtn = findViewById(R.id.menu);
        search = findViewById(R.id.home_searchview);
        btnTecnologia = findViewById(R.id.home_btn_tecnologia);
        btnEducacao = findViewById(R.id.home_btn_educacao);
        btnCasa = findViewById(R.id.home_btn_casa);
        btnArte = findViewById(R.id.home_btn_arte);
        btnSaude = findViewById(R.id.home_btn_saude);
        btnEvento = findViewById(R.id.home_btn_evento);
        olaUser = findViewById(R.id.olaUser);

    }


    // Trata o resultado da solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            for (int index = 0; index != permissions.length; index++) {
                if (permissions[index].equalsIgnoreCase(PERMISSION_GPS) && grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    // A permissão está concedida, você pode prosseguir com as operações relacionadas ao GPS
                    // TODO: Adicione seu código relacionado ao GPS aqui
                } else {
                    // A permissão foi negada, lide com isso adequadamente (por exemplo, mostre uma mensagem ao usuário)
                }
            }
        }
    }

    private void verificarPermissoes() {


         if (ContextCompat.checkSelfPermission(this, PERMISSION_GPS)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, PERMISSION_COARSE)
                        != PackageManager.PERMISSION_GRANTED) {

             ActivityCompat.requestPermissions(
                    this,
                    new String[]{PERMISSION_GPS, PERMISSION_COARSE},
                    REQUEST_PERMISSION_CODE
            );
        } else {
            // Todas as permissões estão concedidas, você pode prosseguir com as operações relacionadas ao GPS
            // TODO: Adicione seu código relacionado ao GPS aqui
        }
        
    }

    @Override
    public Context getContext() {
        return this;
    }

}
