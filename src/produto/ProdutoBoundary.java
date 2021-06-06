package produto;

import categoria.Categoria;
import categoria.CategoriaBoundary;
import categoria.CategoriaController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import marca.Marca;
import marca.MarcaBoundary;
import marca.MarcaController;
import tamanho.Tamanho;
import tamanho.TamanhoBoundary;
import tamanho.TamanhoController;

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

    ProdutoController prodCont = new ProdutoController();
    private MarcaController mcControl = new MarcaController();
    private CategoriaController ctControl = new CategoriaController();
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

        btnAdicionar.setVisible(true);
        txtNome.setEditable(true);
        txtCodigo.setEditable(true);
        txtCor.setEditable(true);
        txtPreco.setEditable(true);
        txtQuantidade.setEditable(true);
        txtDescricao.setEditable(true);
        cbTamanho.setDisable(false);
        cbMarca.setDisable(false);
        cbCategoria.setDisable(false);

        carregarCombo();

        GridPane gridPane = new GridPane();
        Scene scene = new Scene(border, 700, 450);
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
        gridPane.add(lblCategoria, 0,3);
        gridPane.add(cbCategoria,1,3);
        gridPane.add(lblTamanho,3,3);
        gridPane.add(cbTamanho,4,3);
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
            prodCont.adicionar(boundaryToEntity());
            this.entityToBoundary(new Produto());
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();
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
            Pd.setCodMarca(prodCont.buscarPorNomeMarca(cbMarcaValue));
            Pd.setCodCategoria(prodCont.buscarPorNomeCategoria(cbCategoriaValue));
            Pd.setCodTamanho(prodCont.buscarPorNomeTamanho(cbTamanhoValue));
        }catch (Exception e){

        }
        return Pd;
    }

    public void entityToBoundary(Produto Pd) { //consultando a entidade e jogando na tela
        try {
            if (Pd != null) {
                Marca mc;
                Tamanho tm;
                Categoria ct;

                txtCodigo.setText(String.valueOf(Pd.getCodProduto()));
                txtNome.setText(Pd.getNomeProduto());
                txtDescricao.setText(Pd.getDescricao());
                txtCor.setText(Pd.getCor());
                txtQuantidade.setText(String.valueOf(Pd.getQuantidade()));
                txtPreco.setText(String.valueOf(Pd.getPreco()));

                mc = mcControl.pesquisarPorCodigo(Pd.getCodMarca());
                cbMarca.setValue(mc.getNomeMarca());

                tm = tmControl.pesquisarPorCodigo(Pd.getCodTamanho());
                cbTamanho.setValue(tm.getTamanho());

                ct = ctControl.pesquisarPorCodigo(Pd.getCodCategoria());
                cbCategoria.setValue(ct.getCategoria());
            } else {
                entityToBoundary(new Produto());
                alertMess.setHeaderText("PRODUTO NÃO EXISTE.");
                alertMess.showAndWait();
            }
        }catch (Exception e){

        }
    }

    public void carregarCombo(){
        cbMarca.getItems().removeAll(cbMarca.getItems());
        cbCategoria.getItems().removeAll(cbCategoria.getItems());
        cbTamanho.getItems().removeAll(cbTamanho.getItems());
        cbMarca.getItems().addAll(prodCont.carregarMarcas());
        cbTamanho.getItems().addAll(prodCont.carregarTamanhos());
        cbCategoria.getItems().addAll(prodCont.carregarCategoria());
    }

    public void pesquisar(int cod){
        entityToBoundary(prodCont.pesquisarPorCodigo(cod));
        btnAdicionar.setVisible(false);
        txtNome.setEditable(false);
        txtCodigo.setEditable(false);
        txtCor.setEditable(false);
        txtPreco.setEditable(false);
        txtQuantidade.setEditable(false);
        txtDescricao.setEditable(false);
        cbTamanho.setDisable(true);
        cbMarca.setDisable(true);
        cbCategoria.setDisable(true);
    }

}
