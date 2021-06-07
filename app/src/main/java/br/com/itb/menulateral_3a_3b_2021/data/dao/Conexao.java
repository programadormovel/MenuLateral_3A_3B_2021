package br.com.itb.menulateral_3a_3b_2021.data.dao;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

// Classe de Conexão com o Banco de dados Externo
public class Conexao {

    // Método de conexão com o banco de dados externo
    public static Connection conectar(){
        // Declaração da conexão
        Connection conexao = null;

        // IP DO SERVIDOR 192.168.0.200
        // IP REMOTO PRAP3-2021.mssql.somee.com

        try{
            // Liberar politica de segurança
            StrictMode.ThreadPolicy policy;
            policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll()
                    .build();

            StrictMode.setThreadPolicy(policy);

            // Verificar se o driver de conexão foi importado
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            // Construção da conexão
            conexao = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://PRAP3AB.mssql.somee.com;" +
                            "databaseName=PRAP3AB;user=teste3ab;password=master33##"
            );

            /*conexao = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://192.168.0.200;" +
                            "databaseName=PRAP3AB;user=sa;password=123456"
            );*/
        }catch (SQLException | ClassNotFoundException e){
            e.getMessage();
        }

        // Retorno do status de conexão
        return conexao;
    }
}
