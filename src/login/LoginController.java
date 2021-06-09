package login;

import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public List<String> permissao = new ArrayList<>();
    private LoginDAO loginDao = new LoginDAOImpl();

    public boolean validarLogin(Login Lg) {
        try{
            permissao = (loginDao.validarLogin(Lg));
            return permissao.contains(Lg.getSenha());
        }catch (Exception e){

        }
        return false;
    }

    public String getPermissao() {
        return String.valueOf(permissao);
    }
}
