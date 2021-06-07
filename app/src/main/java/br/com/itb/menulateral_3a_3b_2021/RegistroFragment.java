package br.com.itb.menulateral_3a_3b_2021;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import br.com.itb.menulateral_3a_3b_2021.data.dao.LoggedInUserDao;
import br.com.itb.menulateral_3a_3b_2021.data.model.LoggedInUser;

public class RegistroFragment extends Fragment {

    private RegistroViewModel mViewModel;
    private EditText edtNome, edtEmail, edtSenha, edtConfirma;
    private Button botaoRegistrar;

    public static RegistroFragment newInstance() {
        return new RegistroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // TODO: Realizar registro no banco de dados
        View root = inflater.inflate(R.layout.registro_fragment, container, false);

        // Estabelecer vínculos com objetos da tela
        edtNome = root.findViewById(R.id.name_registro);
        edtEmail = root.findViewById(R.id.username_registro);
        edtSenha = root.findViewById(R.id.password_registro);
        edtConfirma = root.findViewById(R.id.confirm_password_registro);
        botaoRegistrar = root.findViewById(R.id.registro);
        botaoRegistrar.setEnabled(true);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // acionar o botão de inserção
        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Captura os dados da tela
                String nome = edtNome.getText().toString();
                String conta = edtEmail.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                int nivelAcesso = 1;

                // Prepara objeto usuário que vai ser inserido
                LoggedInUser usuario = new LoggedInUser(nome, conta, email, senha, nivelAcesso);

                // Inserir usuário no banco
                LoggedInUser usuarioInserido = LoggedInUserDao.inserirUsuario(usuario);

                // Verificar se inseriu com sucesso
                if(usuarioInserido!=null){
                    Snackbar.make(view, "SUCESSO", Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(view, "ERRO", Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);
        // TODO: Use the ViewModel
    }

}