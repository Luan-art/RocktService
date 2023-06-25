package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.AddServiceMVP;
import br.edu.ifsp.dmos.presenter.AddServicePresenter;

public class AddServiceActivity extends AppCompatActivity implements AddServiceMVP.View {

    private EditText edittextNomeServico;
    private EditText editTextmediaPreco;
    private EditText editTextformasPagamento;
    private EditText editTextformaExecucao;
    private EditText editTextinformacaoAdcional;
    private Button btnCad;
    private Spinner menu;

    private String idUsuarioBundle;

    private AddServiceMVP.Presenter presenter;

    String nomeProfissional;
    String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        Bundle bundle = getIntent().getExtras();
        nomeProfissional = bundle.getString("usuario");
        idUsuario = bundle.getString("idUsuarioBundle");
        Log.d("nome Do Usuario no add service activity", "Value: " + (nomeProfissional));
        Log.d("nome Do Usuario no add service activity", "Value: " + (idUsuario));

        findById();
        setListener();
        presenter = new AddServicePresenter(this, this, idUsuarioBundle);
    }


    private void findById() {
        edittextNomeServico = findViewById(R.id.edittext_AddnomeServico);
        editTextmediaPreco = findViewById(R.id.edittext_AddmediaPreco);
        editTextformasPagamento = findViewById(R.id.edittext_AddformasPagamento);
        editTextformaExecucao = findViewById(R.id.edittext_AddformaExecucao);
        editTextinformacaoAdcional = findViewById(R.id.AddeditText);
        btnCad = findViewById(R.id.Addbtn_save);
        menu = findViewById(R.id.spinner_AddCategoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adicionar Serviço");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cratSimpleAdpater();

    }

    private void cratSimpleAdpater() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        menu.setAdapter(adapter);
    }


    private void setListener() {
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeServico = edittextNomeServico.getText().toString();
                String mediaPreco = editTextmediaPreco.getText().toString();
                String formasPagamento = editTextformasPagamento.getText().toString();
                String formaExecucao = editTextformaExecucao.getText().toString();
                String informacaoAdicional = editTextinformacaoAdcional.getText().toString();

                String categoria = menu.getSelectedItem().toString();

                presenter.CadastrarTarefa(nomeServico,  idUsuario, nomeProfissional, categoria, Boolean.parseBoolean(mediaPreco),
                        formasPagamento, formaExecucao, informacaoAdicional, "", null, "Nulo");

            }
        });
    }




    @Override
    public Context getContext() {
        return this;
    }

}