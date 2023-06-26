package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.ServiceSolicitationMVP;
import br.edu.ifsp.dmos.presenter.ServiceSolicitationPresenter;

public class ServiceSolicitationActivity extends AppCompatActivity implements ServiceSolicitationMVP.View {

    private EditText dataExecucao;
    private TextView infoAdd;
    private Button btnFinalizar;
    private ServiceSolicitationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_solicitation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Solicitar Serviço");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new ServiceSolicitationPresenter(this, this);

        findViewById();
        setListener();
    }

    public void findViewById(){
        dataExecucao = findViewById(R.id.dataExecucao);
        infoAdd = findViewById(R.id.AddInfoSelect);
        btnFinalizar = findViewById(R.id.Addbtn_avalia);
    }

    private void setListener() {
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringDataPrevista = dataExecucao.getText().toString();
                String stringInfoAdd = infoAdd.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date utilDate = null;
                try {
                    utilDate = dateFormat.parse(stringDataPrevista);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date data = new Date(utilDate.getTime());

                if (stringInfoAdd.isEmpty() || stringDataPrevista.isEmpty() ) {
                    showMessage("Preencha todos os campos");
                } else {
                    presenter.registrarContrato("", "", stringDataPrevista, stringInfoAdd);
                }

            }
        });
    }

    @Override
    public Context getContext() {return this;}

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}