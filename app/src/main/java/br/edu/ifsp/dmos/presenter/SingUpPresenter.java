package br.edu.ifsp.dmos.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;

import br.edu.ifsp.dmos.constants.Constants;
import br.edu.ifsp.dmos.model.entites.User;
import br.edu.ifsp.dmos.mvp.SignUpMVP;
import br.edu.ifsp.dmos.view.LoginActivity;

public class SingUpPresenter implements SignUpMVP.Presenter {

    private SignUpMVP.View view;
    private Context context;
    private FirebaseFirestore database;

    private String firestoreId = "";



    public SingUpPresenter(SignUpMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        database = FirebaseFirestore.getInstance();
    }

    @Override
    public void Enter() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    //-----------FUNCAO CADASTRAR NOVO USUARIO------------------------------
    //----------------------------------------------------------------------
    @Override
    public void RealizarCadastro(String nome, String email, String doc, Date dataNasci,
                                 String usuario, String telCel,  String senha, String confSenha) {


        CollectionReference listUsuarios = database.collection(Constants.USERS_COLLECTION);

        // Consulta para verificar se o nome de usuário já existe
        listUsuarios.whereEqualTo("email", email).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                boolean userExists = !task.getResult().isEmpty();
                if (userExists) {
                    Toast.makeText(view.getContext(), "Email já está em uso", Toast.LENGTH_SHORT).show();
                } else {

                    //----SE USUARIO NÃO EXISTIR AQUI SERA CADASTRADO----------
                    User user = new User(nome, email, doc, dataNasci, usuario, telCel, null, null, null, senha, 0.0, 0);

                    listUsuarios.add(user).addOnSuccessListener(documentReference -> {
                        Toast.makeText(view.getContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }).addOnFailureListener(e -> {
                        Toast.makeText(view.getContext(), "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                    });
                }
            } else {
                Toast.makeText(view.getContext(), "Erro ao verificar email de usuário.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean isNewUser() {
        return firestoreId.isEmpty();
    }
}
