import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

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

    public boolean validarSenha(String senha, String confSenha){
        if (senha.equals(confSenha)){
            return true;
        }
        return false;
    }
}
