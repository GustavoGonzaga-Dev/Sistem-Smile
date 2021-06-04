package estoque;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class EstoqueController {

    private ObservableList<Estoque> estoque = FXCollections.observableArrayList();
    private TableView<Estoque> tableEstoque = new TableView<>();

    private IntegerProperty codigo = new SimpleIntegerProperty();
    public IntegerProperty codigoProperty(){
        return codigo;
    }

    private StringProperty nome = new SimpleStringProperty();
    public StringProperty nomeProperty(){
        return nome;
    }

    private DoubleProperty valor = new SimpleDoubleProperty();
    public DoubleProperty valorProperty(){
        return valor;
    }

    private IntegerProperty quantidade = new SimpleIntegerProperty();
    public IntegerProperty quantidadeProperty(){
        return quantidade;
    }

    public void setEntity(Estoque est){
        if(est != null){
            codigo.set(est.getCodProduto());
            nome.set(est.getNomeProduto());
            valor.set(est.getValor());
            quantidade.set(est.getQuantidade());
        }
    }

    public Estoque getEntity(){
        Estoque est = new Estoque();
        est.setCodProduto(codigo.get());
        est.setNomeProduto(nome.get());
        est.setValor(valor.get());
        est.setQuantidade(quantidade.get());
        return est;
    }

    public void adicionar(){
        Estoque est = getEntity();
        estoque.add(est);
    }



    public void Tabela(){
        TableColumn<Estoque,Integer> colunaCodigo = new TableColumn<>("Codigo Produto");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("codigo produto"));

        TableColumn<Estoque, String> colunaNome = new TableColumn<>("Nome do Produto");
        colunaNome.setCellValueFactory(new PropertyValueFactory<Estoque, String>("nome do produto"));

        TableColumn<Estoque, Double> colunaValor = new TableColumn<>("Valor");
        colunaValor.setCellValueFactory(new PropertyValueFactory<Estoque, Double>("valor"));

        TableColumn<Estoque,Integer> colunaQuantidade = new TableColumn<>("Quantidade");
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("quantidade"));

    tableEstoque.getColumns().addAll(colunaCodigo, colunaNome, colunaValor, colunaQuantidade);
    tableEstoque.setItems(estoque);
    }
    public TableView<Estoque> getTable(){
        return tableEstoque;
    }

}
