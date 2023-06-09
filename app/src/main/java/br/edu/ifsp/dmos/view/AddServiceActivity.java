package br.edu.ifsp.dmos.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
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

    private String nomeProfissional;
    private String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Adicionar Serviço");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        idUsuario = bundle.getString("idUsuarioBundle");

        findById();
        setListener();
        presenter = new AddServicePresenter(this, this, idUsuarioBundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void findById() {
        edittextNomeServico = findViewById(R.id.edittext_AddnomeServico);
        editTextmediaPreco = findViewById(R.id.edittext_AddmediaPreco);
        editTextformasPagamento = findViewById(R.id.edittext_AddformasPagamento);
        editTextformaExecucao = findViewById(R.id.edittext_AddformaExecucao);
        editTextinformacaoAdcional = findViewById(R.id.AddeditText);
        btnCad = findViewById(R.id.Addbtn_save);
        menu = findViewById(R.id.spinner_AddCategoria);


        createSimpleAdpater();

    }

    //---------CRIANDO O ADAPTER DO SPINNER DE CATEGORIA-------------------------------------
    private void createSimpleAdpater() {
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

                //---------ENVIANDO DADOS DA TAREFA PARA SER CADASTRADA NO PRESENTER-----------------------------------------------
                presenter.CadastrarTarefa(nomeServico,  idUsuario, nomeProfissional, categoria, Double.parseDouble(mediaPreco),
                        formasPagamento, formaExecucao, informacaoAdicional, "", null, "Nulo");

            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

}