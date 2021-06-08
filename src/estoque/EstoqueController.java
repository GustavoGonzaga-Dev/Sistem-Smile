package estoque;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.List;

public class EstoqueController {

    private ObservableList<Estoque> lista = FXCollections.observableArrayList();
    private TableView<Estoque> tableEstoque = new TableView<>();

    private EstoqueDAO estoqueDAO = new EstoqueDAOImpl();

//    private IntegerProperty codProduto = new SimpleIntegerProperty();


    private StringProperty nome = new SimpleStringProperty();


//    private DoubleProperty valor = new SimpleDoubleProperty();
//
//
//    private IntegerProperty quantidade = new SimpleIntegerProperty();


    public void coloca(String nomeProduto){
        List<Estoque> estoque = estoqueDAO.colocarValores(nomeProduto);
        lista.clear();
        lista.addAll(estoque);
    }

    public void colocarValores(){
        coloca(nome.get());
    }

    public void Tabela(){
        coloca("");

        TableColumn<Estoque,Integer> colunaCodigo = new TableColumn<>(" Codigo ");
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("codProduto"));

        TableColumn<Estoque, String> colunaNome = new TableColumn<>("Nome Produto");
        colunaNome.setCellValueFactory(new PropertyValueFactory<Estoque, String>("nomeProduto"));

        TableColumn<Estoque, Double> colunaValor = new TableColumn<>("  Valor R$ ");
        colunaValor.setCellValueFactory(new PropertyValueFactory<Estoque, Double>("valor"));

        TableColumn<Estoque,Integer> colunaQuantidade = new TableColumn<>(" Quantidade ");
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<Estoque, Integer>("quantidade"));

    tableEstoque.getColumns().addAll(colunaCodigo, colunaNome, colunaValor, colunaQuantidade);
    tableEstoque.setItems(lista);

    }



    public TableView<Estoque> getTable(){
        return tableEstoque;
    }

    private static void tamanhoCampo(TextField txtField, Integer tamanho){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > tamanho){
                txtField.setText(oldValue);
            }
        });

    }

    private static void posiciona(TextField txtField){
        Platform.runLater(() ->{
            if (txtField.getText().length() != 0){
                txtField.positionCaret(txtField.getText().length());
            }
        });
    }

    public static void valida(TextField txtField){
        tamanhoCampo(txtField, 20);
        txtField.lengthProperty().addListener((observable, oldValue, newValue) ->
        {
            String textoDigitado = txtField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            txtField.setText(textoDigitado);
            posiciona(txtField);
        });
    }
}
