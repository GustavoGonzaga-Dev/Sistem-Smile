package estoque;

import categoria.CategoriaBoundary;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import marca.MarcaBoundary;
import produto.Produto;
import produto.ProdutoBoundary;
import tamanho.TamanhoBoundary;
import vendas.Vendas;

public class EstoqueBoundary extends Application {

    private Button btnCategoria ;
    private Button btnTamanho ;
    private Button btnMarca ;
    private Button btnAdicionar ;
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnFechar;

    private Label lblInfo = new Label("*Para pesquisar, insira o \n" +" codigo do produto.");

    private TextField txtBusca = new TextField();
    private String cssLayout =  "-fx-border-color: gray;\n" +
                                "-fx-border-insets: 3;\n" +
                                "-fx-border-width: 1;\n" ;
    private String cssBorda =  "-fx-border-color: green;\n" +
                               "-fx-border-insets: 3;\n" +
                               "-fx-border-width: 2;\n" ;

    private CategoriaBoundary categoriaTela = new CategoriaBoundary();
    private TamanhoBoundary tamanhoTela = new TamanhoBoundary();
    private MarcaBoundary marcaTela = new MarcaBoundary();
    ProdutoBoundary produtoTela = new ProdutoBoundary();

    public int cod;

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(30, 20, 20, 20));
        hbox.setSpacing(20);
        hbox.getChildren().addAll(lblInfo,txtBusca, btnPesquisar);
        lblInfo.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        txtBusca.getStylesheets().add(EstoqueBoundary.class.getResource("StylesEstoque.css").toExternalForm());
        return hbox;
    }

    private VBox addVbox(){
        VBox vbox = new VBox();
        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        VBox vbox3 = new VBox();
        VBox vbox4 = new VBox();
        VBox vbox5 = new VBox();

        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Button btns[] = new Button[]{
                btnAdicionar = new Button("Adicionar novo produto"),
                btnCategoria = new Button("Gerenciar Categorias"),
                btnTamanho = new Button("Gerenciar Tamanhos"),
                btnMarca = new Button("Gerenciar Marcas"),
                btnFechar = new Button("Fechar"),
         };
        VBox.setMargin(btns[0], new Insets(8, 5, 8, 5));
        vbox1.getChildren().add(btns[0]);
        vbox1.setStyle(cssBorda);
        vbox1.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[1], new Insets(8, 5, 8, 5));
        vbox2.getChildren().add(btns[1]);
        vbox2.setStyle(cssLayout);
        vbox2.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[2], new Insets(8, 5, 8, 5));
        vbox3.getChildren().add(btns[2]);
        vbox3.setStyle(cssLayout);
        vbox3.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[3], new Insets(8, 5, 8, 5));
        vbox4.getChildren().add(btns[3]);
        vbox4.setStyle(cssLayout);
        vbox4.setAlignment(Pos.CENTER);
        VBox.setMargin(btns[4], new Insets(4, 5, 4, 5));
        vbox5.getChildren().add(btns[4]);
        vbox5.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(vbox1,vbox2,vbox3,vbox4,vbox5);
        return vbox;
    }

    private EstoqueController control = new EstoqueController();

    @Override
    public void start(Stage stage) throws Exception {
        EstoqueController.valida(txtBusca);
        BorderPane borderPane = new BorderPane();
        HBox hbox = addBox();
        borderPane.setTop(hbox);

        VBox vbox = addVbox();
        vbox.setAlignment(Pos.CENTER);
        borderPane.setRight(vbox);

        Scene scCeneEstoque = new Scene(borderPane, 650, 360);
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
                produtoTela.entityToBoundary(new Produto());
                stage.close();
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
                stage.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }));

        btnFechar.setOnAction((event -> {
            stage.close();
        }));

        stage.setScene(scCeneEstoque);
        stage.setTitle("Estoque S2");
        stage.setResizable(false);
        stage.show();
    }


}
