package br.com.itb.menulateral_3a_3b_2021.data;

import android.util.Log;

import br.com.itb.menulateral_3a_3b_2021.data.dao.LoggedInUserDao;
import br.com.itb.menulateral_3a_3b_2021.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser login = LoggedInUserDao.verificaLogin(username, password);

            if(login == null){
                return new Result.Error(new IOException("Usuário não encontrado ou " +
                        "senha inválida!"));
            }

            // Realizar verificação de usuário e senha
            /*LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");*/
            return new Result.Success<>(login);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}