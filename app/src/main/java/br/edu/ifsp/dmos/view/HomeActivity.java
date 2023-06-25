package br.edu.ifsp.dmos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeMVP.View {

    public static final int REQUEST_PERMISSION_CODE = 999;
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
                presenter.BuscarTema("Tecnologia", bundle);
            }
        });

        btnEducacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Educação", bundle);
            }
        });

        btnCasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Casa", bundle);
            }
        });

        btnArte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Arte", bundle);
            }
        });

        btnSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Saúde", bundle);
            }
        });

        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.BuscarTema("Evento", bundle);
            }
        });

        // Adicione o setOnKeyListener para capturar a tecla "Enter"
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String query = search.getQuery().toString();
                    presenter.search(query, bundle);
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
                if (permissions[index].equalsIgnoreCase(Manifest.permission.ACCESS_FINE_LOCATION) &&
                        permissions[index].equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION)  &&
                        grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                    localizacaoAtual();
                } else {
                    Toast.makeText(this, "Permissão a GPS negada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void verificarPermissoes() {
        String permissoes_necessarias[] = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        List<String> requerer_permissoes = new ArrayList<>();

        for(String permissao : permissoes_necessarias){
            if(ContextCompat.checkSelfPermission(this, permissao) !=
                    PackageManager.PERMISSION_GRANTED){
                requerer_permissoes.add(permissao);
            }
        }
        if(!requerer_permissoes.isEmpty()){
            String pedido_permissoes[] =
                    requerer_permissoes.toArray(
                            new String[requerer_permissoes.size()]);

            ActivityCompat.requestPermissions(
                    this,
                    pedido_permissoes,
                    99);
        } else{

            localizacaoAtual();

        }

    }


    private void localizacaoAtual() {
        LocationManager mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addresses.size() > 0) {

                        String cidade = addresses.get(0).getLocality();
                        String estado = addresses.get(0).getAdminArea();


                        bundle.putString("cidade", cidade);
                        bundle.putString("estado", estado);

                        Toast.makeText(getApplicationContext(), "Cidade: " + cidade + ", Estado: " + estado, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    @Override
    public Context getContext() {
        return this;
    }
}
