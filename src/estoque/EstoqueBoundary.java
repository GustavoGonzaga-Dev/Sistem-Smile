package estoque;

import categoria.CategoriaBoundary;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import marca.MarcaBoundary;
import produto.ProdutoBoundary;
import tamanho.TamanhoBoundary;


public class EstoqueBoundary extends Application {

    private Button btnCategoria = new Button("Gerenciar Categorias");
    private Button btnTamanho = new Button("Gerenciar Tamanhos");
    private Button btnMarca = new Button("Gerenciar Marcas");
    private Button btnAdicionar = new Button("+");

    private TextField txtBusca = new TextField();

    private CategoriaBoundary categoriaTela = new CategoriaBoundary();
    private TamanhoBoundary tamanhoTela = new TamanhoBoundary();
    private MarcaBoundary marcaTela = new MarcaBoundary();
    private ProdutoBoundary produtoTela = new ProdutoBoundary();

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(20);
        hbox.getChildren().addAll(txtBusca);
        txtBusca.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        return hbox;
    }

    private EstoqueController control = new EstoqueController();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox hbox = addBox();
        borderPane.setTop(hbox);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(40);
        gridPane.setHgap(10);

        Scene scCeneEstoque = new Scene(borderPane, 600, 350);


        borderPane.setRight(gridPane);
        gridPane.add(btnMarca,0,0);
        gridPane.add(btnCategoria,0,1);
        gridPane.add(btnTamanho,0,2);
        gridPane.add(btnAdicionar,0,3);

        control.Tabela();
        borderPane.setCenter(control.getTable());

        StringConverter integerToStringConverter = new IntegerStringConverter();
//      Bindings.bindBidirectional(txtId.textProperty(), control.codigoProperty(), integerToStringConverter);


        btnCategoria.setOnAction((event -> {
            Stage stageCategoria = new Stage();
            try {
                categoriaTela.start(stageCategoria);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnMarca.setOnAction((event -> {
            Stage stageMarca = new Stage();
            try {
                marcaTela.start(stageMarca);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnTamanho.setOnAction((event -> {
            Stage stageTamanho = new Stage();
            try {
                tamanhoTela.start(stageTamanho);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnAdicionar.setOnAction((event -> {
            Stage stageProduto = new Stage();
            try {
                produtoTela.start(stageProduto);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        stage.setScene(scCeneEstoque);
        stage.setTitle("Estoque S2");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(EstoqueBoundary.class, args);
    }
}
