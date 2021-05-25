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

    public void excluir(Funcionario Fn){
        for (Funcionario Fn3 : funcionarios){
            if(Fn3.getCodigo() == Fn.getCodigo()){
                funcionarios.remove(Fn3);
            }
        }
    }

    public void alterar (Funcionario Fn){
        for (Funcionario Fn2 : funcionarios){
            if(Fn2.getCodigo() == Fn.getCodigo()){
                funcionarios.remove(Fn2);
                funcionarios.add(Fn);
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

    public void admin() {
        Funcionario Fn = new Funcionario();
        Fn.setNome("ADMIN");
        Fn.setEmail("ADMIN@ADMIIN.COM");
        Fn.setConfEmail("ADMIN@ADMIIN.COM");
        Fn.setSenha("ADMIN1234");
        Fn.setConfSenha("ADMIN1234");
        Fn.setCodigo(0);
        Fn.setPermissao("MASTER");
        adicionar(Fn);
    }
}
