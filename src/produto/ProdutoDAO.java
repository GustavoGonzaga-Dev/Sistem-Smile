package produto;

import java.util.List;

public interface ProdutoDAO {
    List<String> carregarMarcas();
    List<String> carregarTamanhos();
    List<String> carregarCategoria();
    int buscarPorNomeMarca(String cbMarcaValue);
    int buscarPorNomeCategoria(String cbCategoriaValue);
    int buscarPorNomeTamanho(String cbTamanhoValue);
    void adicionar(Produto Pd);
    void excluir(int cod);
    void alterar(Produto pd);
    Produto pesquisarPorCodigo(int cod);
}
