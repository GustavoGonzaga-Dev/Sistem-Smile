package estoque;

public class Estoque {
    private int codProduto;
    private String nomeProduto;
    private double Valor;
    private int Quantidade;

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValor(double valor) {
        Valor = valor;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }
}
