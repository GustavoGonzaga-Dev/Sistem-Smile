package marca;

public interface MarcaDAO {
    void adicionar(Marca mc);
    Marca pesquisarPorCodigo(int txtPesquisar);
    void excluir(Marca mc);
    void alterar(Marca mc);
}
