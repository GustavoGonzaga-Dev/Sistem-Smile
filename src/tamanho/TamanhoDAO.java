package tamanho;

public interface TamanhoDAO {

    void adicionar(Tamanho Tm);

    Tamanho pesquisarPorCodigo(int txtPesquisar);

    void excluir(Tamanho Tm);

    void alterar(Tamanho Tm);
}
