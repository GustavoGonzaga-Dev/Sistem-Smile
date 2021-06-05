package estoque;

import categoria.CategoriaBoundary;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import marca.MarcaBoundary;
import produto.ProdutoBoundary;
import produto.ProdutoController;
import tamanho.TamanhoBoundary;


public class EstoqueBoundary extends Application {

    private Button btnCategoria ;
    private Button btnTamanho ;
    private Button btnMarca ;
    private Button btnAdicionar ;
    private Button btnPesquisar = new Button("Pesquisar");

    private TextField txtBusca = new TextField();
    private String cssLayout =  "-fx-border-color: gray;\n" +
                                "-fx-border-insets: 3;\n" +
                                "-fx-border-width: 1;\n" ;

    private CategoriaBoundary categoriaTela = new CategoriaBoundary();
    private TamanhoBoundary tamanhoTela = new TamanhoBoundary();
    private MarcaBoundary marcaTela = new MarcaBoundary();
    ProdutoBoundary produtoTela = new ProdutoBoundary();

    public int cod;

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(20);
        hbox.getChildren().addAll(txtBusca, btnPesquisar);
        txtBusca.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        return hbox;
    }

    private VBox addVbox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button btns[] = new Button[]{
          btnCategoria = new Button("Gerenciar Categorias"),
          btnTamanho = new Button("Gerenciar Tamanhos"),
          btnMarca = new Button("Gerenciar Marcas"),
          btnAdicionar = new Button("+"),
        };
        for (int i = 0 ; i < 4 ; i++){
            VBox.setMargin(btns[i], new Insets(5,5,14,5));
            vbox.getChildren().add(btns[i]);
        }
        return vbox;
    }

    private EstoqueController control = new EstoqueController();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        HBox hbox = addBox();
        borderPane.setTop(hbox);

        VBox vbox = addVbox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle(cssLayout);
        borderPane.setRight(vbox);

        Scene scCeneEstoque = new Scene(borderPane, 650, 350);
        btnAdicionar.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());

        control.Tabela();
        borderPane.setCenter(control.getTable());

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

        btnPesquisar.setOnAction((event -> {
            cod = Integer.parseInt(txtBusca.getText());
            Stage stageProdutoPesq = new Stage();
            try {
                produtoTela.start(stageProdutoPesq);
                produtoTela.pesquisar(cod);
                //produtoControl.codigo(cod);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }));

        stage.setScene(scCeneEstoque);
        stage.setTitle("Estoque S2");
        stage.setResizable(false);
        stage.show();
    }


}
