package funcionario;

public interface FuncionarioDAO {

    void adicionar(Funcionario Fn);
    Funcionario pesquisarPorCodigo(long codigo);
    void excluir(Funcionario Fn);
    void alterar(Funcionario Fn);
    void admin();
}
