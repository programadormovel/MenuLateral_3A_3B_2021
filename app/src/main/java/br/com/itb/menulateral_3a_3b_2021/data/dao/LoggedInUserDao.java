package br.com.itb.menulateral_3a_3b_2021.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.itb.menulateral_3a_3b_2021.data.model.LoggedInUser;

// Classe de conexaco do LoggedInUser
public class LoggedInUserDao {  //

    // Método de verificação de usuário e senha
    public static LoggedInUser verificaLogin(String email,
                                             String senha){
        LoggedInUser login = null;

        // TODO - CRIAR PESQUISA DO LOGIN
        try{
            // Preparando declaração de pesquisa
            PreparedStatement pst;
            pst = Conexao.conectar().prepareStatement("" +
                    "Select id, conta, senha, nome, email, nivel_acesso from login " +
                    "where email = ? and senha = ?;");
            // Passagem de Parâmetros
            pst.setString(1, email);
            pst.setString(2, senha);
            // Executar pesquisa
            ResultSet res = pst.executeQuery();
            // Verificar se resultado é nulo ou não
            if(res != null) {
                // Capturar dados do resultado
                while(res.next()){
                    login = new LoggedInUser(
                            String.valueOf(res.getInt(1)),
                            res.getString(4)
                    );
                }
            }

        }catch (Exception e){
            e.getMessage();
        }

        return login;
    }

    public static LoggedInUser inserirUsuario(LoggedInUser usuario){
        // TODO - INSERIR USUÁRIO NO BANCO DE DADOS

        try{
            // Preparar declaração
            String declaracao = "Insert Into login (conta, senha, nome, email, nivel_acesso) " +
                    "values (?,?,?,?,?);";
            // Prerarar objeto de conexão e declaração
            PreparedStatement pst = Conexao.conectar().prepareStatement(declaracao);
            // Passagem de parâmetros
            pst.setString(1, usuario.getConta());
            pst.setString(2, usuario.getSenha());
            pst.setString(3, usuario.getDisplayName());
            pst.setString(4, usuario.getEmail());
            pst.setInt(5, usuario.getNivelAcesso());
            // Executar inserção
            pst.executeUpdate();
            // Verificar se usuário entrou capturando ID
            String declaracao2 = "Select id from login where email = ?";
            PreparedStatement pst2 = Conexao.conectar().prepareStatement(declaracao2);
            pst2.setString(1, usuario.getEmail());
            ResultSet resultado = pst2.executeQuery();
            if(resultado!=null){
                while(resultado.next()){
                    usuario.setUserId(String.valueOf(resultado.getInt(1)));
                }
                return usuario;
            }

        }catch (SQLException e){
          e.getMessage();
        }

        return null;
    }
}
