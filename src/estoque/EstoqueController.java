package estoque;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EstoqueController {

    private ObservableList<Estoque> lista = FXCollections.observableArrayList();
    private TableView<Estoque> tableEstoque = new TableView<>();
    private EstoqueDAO estoqueDAO = new EstoqueDAOImpl();

    public ObservableList<Estoque> coloca(){
        lista.clear();
        tableEstoque.getItems().clear();
        List<Estoque> estoque = estoqueDAO.colocarValores();
        lista.addAll(estoque);
        return lista;
    }

    public void colocarValores(){
        TableColumn<Estoque,Integer> colunaCodigo = new TableColumn<>(" Codigo ");
        TableColumn<Estoque, String> colunaNome = new TableColumn<>("Nome Produto");
        TableColumn<Estoque, Double> colunaValor = new TableColumn<>("  Valor R$ ");
        TableColumn<Estoque,Integer> colunaQuantidade = new TableColumn<>(" Quantidade ");

        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codProduto"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tableEstoque.getColumns().addAll(colunaCodigo, colunaNome, colunaValor, colunaQuantidade);
        tableEstoque.setMinSize(440, 410);
        tableEstoque.setMaxSize(440, 410);
        tableEstoque.setEditable(false);
        tableEstoque.getItems().clear();
        tableEstoque.getStylesheets().add(EstoqueController.class.getResource("StylesTabela.css").toExternalForm());

    }

    public void Tabela(){
        tableEstoque.getItems().clear();
        tableEstoque.setItems(coloca());
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
