package categoria;

public interface CategoriaDAO {

    void adicionar(Categoria Ct);
    Categoria pesquisarPorCodigo(int txtPesquisar);
    void excluir(Categoria Ct);
    void alterar(Categoria Ct);
}
