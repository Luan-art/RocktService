package br.edu.ifsp.dmos.presenter;


import static br.edu.ifsp.dmos.constants.Constants.USERS_COLLECTION;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import br.edu.ifsp.dmos.R;
import br.edu.ifsp.dmos.mvp.LoginMVP;
import br.edu.ifsp.dmos.view.HomeActivity;
import br.edu.ifsp.dmos.view.SingUpActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private Context context;

    private FirebaseAuth firebaseAuth;


    public LoginPresenter(LoginMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void Login(String user, String password) {
        if (user.isEmpty() || password.isEmpty()) {
            view.showErrorMessage("Preencha todos os campos");
        } else {
            verificarUserESenha(user, password);
                    
        }
    }


    private void verificarUserESenha(String username, String password) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usuarioRef = db.collection(USERS_COLLECTION);
        usuarioRef.whereEqualTo("usuario", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                String storedPassword = documentSnapshot.getString("senha");
                                assert storedPassword != null;
                                if (storedPassword.equals(password)) {
                                    String userId = documentSnapshot.getId();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("usuario", username);
                                    Intent intent = new Intent(context, HomeActivity.class);
                                    intent.putExtras(bundle);
                                    context.startActivity(intent);
                                } else {
                                    view.showErrorMessage("Senha incorreta");
                                }
                            } else {
                                view.showErrorMessage("Usuário não encontrado");
                            }
                        } else {
                            view.showErrorMessage("Erro ao buscar usuário");
                        }
                    }
                });
    }

    @Override
    public void Cadast() {
        Intent intent = new Intent(context, SingUpActivity.class);
        context.startActivity(intent);
    }
}
