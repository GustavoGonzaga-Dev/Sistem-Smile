package produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public List<String> carregarMarcas(){
        List<String> lista = produtoDAO.carregarMarcas();
    return lista;
    }

    public List<String> carregarTamanhos(){
        List<String> lista = produtoDAO.carregarTamanhos();
        return lista;
    }

    public List<String> carregarCategoria(){
        List<String> lista = produtoDAO.carregarCategoria();
        return lista;
    }

    public int buscarPorNomeMarca(String cbMarcaValue){
        return produtoDAO.buscarPorNomeMarca(cbMarcaValue);
    }

    public int buscarPorNomeCategoria(String cbCategoriaValue){
        return produtoDAO.buscarPorNomeCategoria(cbCategoriaValue);
    }

    public int buscarPorNomeTamanho(String cbTamanhoValue){
        return  produtoDAO.buscarPorNomeTamanho(cbTamanhoValue);
    }

    public void adicionar(Produto Pd){
        produtoDAO.adicionar(Pd);
    }

    public void excluir(int cod){
        produtoDAO.excluir(cod);
    }

    public void alterar(Produto pd){
        produtoDAO.alterar(pd);
    }

    public Produto pesquisarPorCodigo(int cod){
        return produtoDAO.pesquisarPorCodigo(cod);
    }

}
