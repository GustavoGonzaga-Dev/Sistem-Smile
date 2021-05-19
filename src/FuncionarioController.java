import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController extends Component {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void adicionar (Funcionario Fn){
        funcionarios.add(Fn);
    }

    public Funcionario pesquisarPorCodigo(long codigo){
        for (Funcionario Fn : funcionarios){
            if(Fn.getCodigo() == codigo){
                return Fn;
            }
        }
        return  null;
    }

    public void excluirPorCodigo(long codigo){
        for (Funcionario Fn : funcionarios){
            if(Fn.getCodigo() == codigo){
                funcionarios.remove(Fn);
            }
        }
    }

    public boolean validarCampos(String txtNome, String txtEmail, String txtConfEmail, String txtCodigo, String cbPermissao, String txtSenha, String txtconfSenha){
        List<String> lista = new ArrayList<>();
        lista.add(0, txtNome);
        lista.add(1, txtEmail);
        lista.add(2, txtConfEmail);
        lista.add(3, txtCodigo);
        lista.add(4, cbPermissao);
        lista.add(5, txtSenha);
        lista.add(6, txtconfSenha);

        for (String l : lista) {
            if (l.equals(null) || l.equals("")){
                lista.clear();
                return false;
            }
        }
        lista.clear();
        return validarEmailSenha(txtEmail, txtConfEmail, txtSenha, txtconfSenha);
    }

    public boolean validarEmailSenha(String txtEmail, String txtConfEmail, String txtsenha, String txtconfSenha){
        if (txtsenha.equals(txtconfSenha) && txtEmail.equals(txtConfEmail)){
            return true;
        }
        return false;
    }


}
