package vendas;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class VendasController {


    Vendas v1 = new Vendas();
    Vendas v2 = new Vendas();
    Vendas v3 = new Vendas();

    public void carregar(){
        v1.setCodigoVenda(1);
        v1.setCodigoCliente(1);
        v1.setNomeCliente("Roberto");
        v1.setEndereco("Rua Catapimbas N°77, São Paulo SP");
        v1.setSituacao("Cancelado");
        v1.setData("11/04/2021");

        v2.setCodigoVenda(2);
        v2.setCodigoCliente(2);
        v2.setNomeCliente("Claudio");
        v2.setEndereco("Rua Carambolas N°88, São Paulo SP");
        v2.setSituacao("Entregue");
        v2.setData("08/05/2021");

        v3.setCodigoVenda(3);
        v3.setCodigoCliente(3);
        v3.setNomeCliente("Jurema");
        v3.setEndereco("Rua Cataporas N°99, São Paulo SP");
        v3.setSituacao("Preparando");
        v3.setData("16/11/2021");
    }

    public Vendas pesquisaCodigo(int codigo){
        if(v1.getCodigoVenda() == 0){
            carregar();
        }
        switch (codigo){
            case 1:
                return v1;
            case 2:
                return v2;
            case 3:
                return v3;
        }
        return null;
    }

    public void alterar(Vendas vd){
        if(vd.getCodigoVenda() == v1.getCodigoVenda()){
            v1.setSituacao(vd.getSituacao());
        }else if(vd.getCodigoVenda() == v2.getCodigoVenda()){
            v2.setSituacao(vd.getSituacao());
        }else if(vd.getCodigoVenda() == v3.getCodigoVenda()){
            v3.setSituacao(vd.getSituacao());
        }
    }

    private ObservableList<Tabela> tabelas = FXCollections.observableArrayList();
    TableView < Tabela > table = new TableView<>();
    public void geraTabela() {
        List<Tabela> tabelas = Arrays.asList(
                t1 = new Tabela(1, "Camisa", 10, 25.00),
                t2 = new Tabela(2, "Toca do Zé", 1, 400.00),
                t3 = new Tabela(3, "Blusa Preta Ardidas", 3, 200.00));

        TableColumn<Tabela, Integer> codigoProduto = new TableColumn("Código do produto");
        TableColumn<Tabela, String> nomeProduto = new TableColumn("Nome");
        TableColumn<Tabela, Integer> quantidade = new TableColumn("Qtd");
        TableColumn<Tabela, Double> preco = new TableColumn("Valor");

        codigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        table.setItems(FXCollections.observableArrayList(tabelas));
        table.getColumns().addAll(codigoProduto,nomeProduto,quantidade,preco);
    }

        private  SimpleIntegerProperty codigoProduto;
        private  SimpleStringProperty nomeProduto;
        private  SimpleIntegerProperty quantidade;
        private  SimpleDoubleProperty preco;

//        public void setando() {
//            t1.setCodigoProduto(1);
//            t1.setNomeProduto("Camisa Nike");
//            t1.setQuantidade(4);
//            t1.setPreco(200.00);
//
//            t2.setCodigoProduto(2);
//            t2.setNomeProduto("Toca do Zé");
//            t2.setQuantidade(2);
//            t2.setPreco(10.00);
//
//            t3.setCodigoProduto(3);
//            t3.setNomeProduto("Blusa Preta Puma");
//            t3.setQuantidade(5);
//            t3.setPreco(230.00);
//            codigoProduto.set(t.getCodigoProduto());
//            nomeProduto.set(t.getNomeProduto());
//            quantidade.set(t.getQuantidade());
//            preco.set(t.getPreco());
//        }
      Tabela t1 ;
      Tabela t2 ;
      Tabela t3 ;

//        public void pegando() {
//            t1.getCodigoProduto();
//
//        }


//        public Tabelas(Integer codigoProduto, String nomeProduto, Integer quantidade, Double preco){
//            this.codigoProduto = new SimpleIntegerProperty(codigoProduto);
//            this.nomeProduto = new SimpleStringProperty(nomeProduto);
//            this.quantidade = new SimpleIntegerProperty(quantidade);
//            this.preco = new SimpleDoubleProperty(preco);
//        }

//        private ObservableList<Tabela> tabela = FXCollections.observableArrayList();

//                new Tabela(1,"Camisa kiki tenis",4,200.00),
//                new Tabela(2,"Calça tico-tico",3,100.00),
//                new Tabela(3,"Toca do Zé",2,20.00),
//                new Tabela(4,"Blusa verdinha quentinha",1,400.0)

        public TableView<Tabela> getTable(){
            return table;
        }
    
        public int getCodigoProduto() {
            return codigoProduto.get();
        }

        public SimpleIntegerProperty codigoProdutoProperty() {
            return codigoProduto;
        }

        public void setCodigoProduto(int codigoProduto) {
            this.codigoProduto.set(codigoProduto);
        }

        public String getNomeProduto() {
            return nomeProduto.get();
        }

        public SimpleStringProperty nomeProdutoProperty() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto.set(nomeProduto);
        }

        public int getQuantidade() {
            return quantidade.get();
        }

        public SimpleIntegerProperty quantidadeProperty() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade.set(quantidade);
        }

        public double getPreco() {
            return preco.get();
        }

        public SimpleDoubleProperty precoProperty() {
            return preco;
        }

        public void setPreco(double preco) {
            this.preco.set(preco);
        }
    }
