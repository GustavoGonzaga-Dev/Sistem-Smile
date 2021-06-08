package vendas;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Arrays;
import java.util.List;

public class VendasController {
    Tabela t1;
    Tabela t2;
    Tabela t3;

    List<Tabela> tabelas = Arrays.asList(t1 = new Tabela(1, "Camisa", 10, 25.00),
            t2 = new Tabela(2, "Toca do Zé", 1, 400.00),
            t3 = new Tabela(3, "Blusa Preta Ardidas", 3, 200.00));

    TableView < Tabela > table = new TableView<>();

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
                printarTable(codigo);
                return v1;
            case 2:
                printarTable(codigo);
                return v2;
            case 3:
                printarTable(codigo);
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

    public void geraTabela() {
        TableColumn<Tabela, Integer> codigoProduto = new TableColumn("Código do produto");
        TableColumn<Tabela, String> nomeProduto = new TableColumn("Nome");
        TableColumn<Tabela, Integer> quantidade = new TableColumn("Qtd");
        TableColumn<Tabela, Double> preco = new TableColumn("Valor");

        codigoProduto.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        nomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        preco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        table.getColumns().addAll(codigoProduto,nomeProduto,quantidade,preco);

        table.setMinSize(320, 300);
        table.setMaxSize(320, 300);
        table.setEditable(false);
        table.getItems().clear();
    }

    public void printarTable(int codigo){
        switch (codigo){
            case 1:
                table.getItems().clear();
                table.setItems(FXCollections.observableArrayList(tabelas));
                break;
            case 2:
                table.getItems().clear();
                table.setItems(FXCollections.observableArrayList(t2));
                break;
            case 3:
                table.getItems().clear();
                table.setItems(FXCollections.observableArrayList(t3, t1));
                break;
        }
    }

    public double total(int codigo){
        switch (codigo){
            case 1:
                return 1250;
            case 2:
                return 400;
            case 3:
                return 850;
        }
        return 0;
    }

        private  SimpleIntegerProperty codigoProduto;
        private  SimpleStringProperty nomeProduto;
        private  SimpleIntegerProperty quantidade;
        private  SimpleDoubleProperty preco;

        public TableView<Tabela> getTable(){
            return table;
        }
    }
