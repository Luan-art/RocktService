package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifsp.dmos.mvp.HomeMVP;
import br.edu.ifsp.dmos.view.ListServiceByCategoryActivity;
import br.edu.ifsp.dmos.view.MenuActivity;

public class HomePresenter implements HomeMVP.Presenter {

    private HomeMVP.View view;
    private Context context;

    private FirebaseFirestore database;




    public HomePresenter(HomeMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();

    }

    @Override
    public void GoToMenu(Bundle bundle) {


        Intent intent = new Intent(context, MenuActivity.class);

        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //---------PESQUISA BASEADA EM TEMA--------------
    @Override
    public void BuscarTema(String tema, Bundle bundle1) {
        Intent intent = new Intent(context, ListServiceByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("tema", tema);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    //---------PESQUISA BASEADA EM NOME DO SERVICO-------------
    @Override
    public void search(String nome, Bundle bundle1) {
        Intent intent = new Intent(context, ListServiceByCategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        Log.d("chegueiSearch", nome);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
