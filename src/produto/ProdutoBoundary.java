package produto;

import categoria.CategoriaBoundary;
import categoria.CategoriaController;
import funcionario.Funcionario;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import marca.MarcaBoundary;
import marca.MarcaController;
import tamanho.TamanhoBoundary;
import tamanho.TamanhoController;

import java.util.List;

public class ProdutoBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtCodigo = new TextField();
    private TextField txtCor = new TextField();

    private ComboBox<String> cbMarca = new ComboBox<>();
    private ComboBox<String> cbCategoria = new ComboBox<>();
    private ComboBox<String> cbTamanho = new ComboBox<>();

    private TextField txtPreco = new TextField();
    private TextField txtQuantidade = new TextField();
    private TextField txtDescricao = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnFechar = new Button("Fechar");
    private Button btnMarca = new Button("+");
    private Button btnCategoria = new Button("+");
    private Button btnTamanho = new Button("+");

    private ProdutoController prodControl = new ProdutoController();
    private MarcaBoundary marcaTela = new MarcaBoundary();
    private MarcaController mcControl = new MarcaController();
    private CategoriaBoundary categoriaTela = new CategoriaBoundary();
    private CategoriaController ctControl = new CategoriaController();
    private TamanhoBoundary tamanhoTela = new TamanhoBoundary();
    private TamanhoController tmControl = new TamanhoController();

    private String cbMarcaValue;
    private String cbTamanhoValue;
    private String cbCategoriaValue;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private HBox addBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 15, 20, 15));
        hbox.setSpacing(10);
        return hbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border = new BorderPane();
        HBox hbox = addBox();
        border.setTop(hbox);
        border.setLeft(addBox());

        carregarCombo();

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(border, 650, 400);
        gridPane.setVgap(20);
        gridPane.setHgap(5);
        border.setCenter(gridPane);

        Label lblNome = new Label("Nome:");
        Label lblCodigo = new Label("Codigo:");
        Label lblCor = new Label("Cor:");
        Label lblMarca = new Label("Marca:");
        Label lblCategoria = new Label("Categoria:");
        Label lblTamanho = new Label("Tamanho:");
        Label lblPreco = new Label("Preço:");
        Label lblQuantidade = new Label("Quantidade em Estoque:");
        Label lblDescricao = new Label("Descrição:");

        gridPane.add(lblNome,0,0);
        gridPane.add(txtNome,1,0);
        gridPane.add(lblCodigo,0,1);
        gridPane.add(txtCodigo,1,1);
        gridPane.add(lblCor,2,1);
        gridPane.add(txtCor,3,1);
        gridPane.add(lblMarca,0,2);
        gridPane.add(cbMarca,1,2);
        gridPane.add(btnMarca,2,2);
        gridPane.add(lblCategoria, 0,3);
        gridPane.add(cbCategoria,1,3);
        gridPane.add(btnCategoria,2,3);
        gridPane.add(lblTamanho,3,3);
        gridPane.add(cbTamanho,4,3);
        gridPane.add(btnTamanho,5,3);
        gridPane.add(lblPreco,0,4);
        gridPane.add(txtPreco,1,4);
        gridPane.add(lblQuantidade,2,4);
        gridPane.add(txtQuantidade,3,4);
        gridPane.add(lblDescricao,0,5);
        txtDescricao.getStylesheets().add(ProdutoBoundary.class.getResource("StylesProduto.css").toExternalForm());

        AnchorPane anchorPane = new AnchorPane();
        HBox box = new HBox();
        box.setPadding(new Insets(0,40,10,20));
        box.setSpacing(10);
        box.getChildren().addAll(txtDescricao, btnAdicionar);
        anchorPane.getChildren().addAll( box);
        AnchorPane.setBottomAnchor(box,8.0);
        AnchorPane.setRightAnchor(box,5.0);
        border.setBottom(anchorPane);

        btnAdicionar.setOnAction((event -> {
            prodControl.adicionar(boundaryToEntity());
            this.entityToBoundary(new Produto());
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();
        }));

        btnCategoria.setOnAction((event -> {
            Stage stageCat = new Stage();
            try {
                categoriaTela.start(stageCat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        btnMarca.setOnAction((event -> {
            Stage stageMar = new Stage();
            try {
                marcaTela.start(stageMar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        btnTamanho.setOnAction((event -> {
            Stage stageTam = new Stage();
            try {
                tamanhoTela.start(stageTam);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));

        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Produto S2");
        stage.show();
    }

    public Produto boundaryToEntity(){
        Produto Pd = new Produto();
        Pd.setNomeProduto(txtNome.getText());
        Pd.setCor(txtCor.getText());
        Pd.setDescricao(txtDescricao.getText());
        cbMarcaValue = cbMarca.getValue();
        cbCategoriaValue = cbCategoria.getValue();
        cbTamanhoValue = cbTamanho.getValue();
        try {
            Pd.setCodProduto(Integer.parseInt(txtCodigo.getText()));
            Pd.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            Pd.setPreco(Double.parseDouble(txtPreco.getText()));
            Pd.setCodMarca(prodControl.buscarPorNomeMarca(cbMarcaValue));
            Pd.setCodCategoria(prodControl.buscarPorNomeCategoria(cbCategoriaValue));
            Pd.setCodTamanho(prodControl.buscarPorNomeTamanho(cbTamanhoValue));
        }catch (Exception e){

        }
        return Pd;
    }

    public void entityToBoundary(Produto Pd) { //consultando a entidade e jogando na tela
        if (Pd != null) {
            txtCodigo.setText(String.valueOf(Pd.getCodProduto()));
            txtNome.setText(Pd.getNomeProduto());
            txtDescricao.setText(Pd.getDescricao());
            txtCor.setText(Pd.getCor());
            txtQuantidade.setText(String.valueOf(Pd.getQuantidade()));
            txtPreco.setText(String.valueOf(Pd.getPreco()));
            cbMarca.setValue(String.valueOf(mcControl.pesquisarPorCodigo(Pd.getCodMarca())));
            cbTamanho.setValue(String.valueOf(tmControl.pesquisarPorCodigo(Pd.getCodTamanho())));
            cbCategoria.setValue(String.valueOf(ctControl.pesquisarPorCodigo(Pd.getCodCategoria())));
        } else {
            alertMess.setHeaderText("PRODUTO NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }

    public void carregarCombo(){
        cbMarca.getItems().addAll(prodControl.carregarMarcas());
        cbTamanho.getItems().addAll(prodControl.carregarTamanhos());
        cbCategoria.getItems().addAll(prodControl.carregarCategoria());
    }

}
