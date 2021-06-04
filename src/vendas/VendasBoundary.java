package vendas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class VendasBoundary extends Application {
    private TextField txtNome = new TextField();
    private TextField txtCodigoCliente = new TextField();
    private TextField txtEndereco = new TextField();
    private TextField txtCodigo = new TextField();
    private TextField txtData = new TextField();
    private TextField txtTotal = new TextField();

    private ComboBox<String> Situacao = new ComboBox<>();
    private String status[] = {"Preparando", "Cancelado", "Enviado", "Entregue"};

    private Button btnPesq = new Button("Pesquisar");
    private Button btnAlterar = new Button("Alterar");
    private Button btnBack = new Button("Voltar");

    private TableView table = new TableView();

    @Override
    public void start(Stage stage) {
        Pane pPane = new Pane();
        Scene scCeneFuncionario = new Scene(pPane, 700, 550);

        Rectangle shape = new Rectangle();
        shape.setHeight(250);
        shape.setWidth(200);
        shape.setArcHeight(30.0);
        shape.setArcWidth(30.0);

        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.BLACK);

        table.setMinSize(350,200);
        Situacao.getItems().addAll(status);

        Label lblCodigo = new Label("Codigo da venda:");
        Label lblCodigoCliente = new Label("Código do cliente:");
        Label lblEndereco = new Label("Endereço de entrega:");
        Label lblNome = new Label("Nome Cliente:");
        Label lblData = new Label("Data:");
        Label lblSituacao = new Label("Situação de venda:");
        Label lblTotal = new Label("Valor total:");

        TableColumn codigoProduto = new TableColumn("Código do produto");
        TableColumn nomeProduto = new TableColumn("Nome");
        TableColumn quantidade = new TableColumn("Qtd");
        TableColumn preco = new TableColumn("Valor");

        lblCodigo.relocate(60, 10);
        txtCodigo.relocate(40, 30);

        lblCodigoCliente.relocate(60, 120);
        txtCodigoCliente.relocate(40, 140);

        lblNome.relocate(60, 180);
        txtNome.relocate(40, 200);

        lblEndereco.relocate(60, 240);
        txtEndereco.relocate(40, 260);

        lblData.relocate(60, 300);
        txtData.relocate(40, 320);

        lblSituacao.relocate(60, 400);
        Situacao.relocate(60, 420);

        table.relocate(300, 20);
        table.getColumns().addAll(codigoProduto, nomeProduto, quantidade, preco);
        codigoProduto.prefWidthProperty().bind(table.widthProperty().multiply(0.38));


        lblTotal.relocate(330, 440);
        txtTotal.relocate(400, 438);
        txtTotal.setDisable(true);

        shape.relocate(20,110);

        btnAlterar.relocate(85, 460);
        btnPesq.relocate(80, 70);
        btnBack.relocate(420, 480);

        pPane.getChildren().addAll(lblNome, lblCodigo, txtCodigo, lblCodigoCliente, txtCodigoCliente, txtNome,
                lblEndereco, txtEndereco, lblData, txtData, lblSituacao, btnAlterar, table, Situacao, btnBack,
                lblTotal, txtTotal, btnPesq, shape);

        stage.setScene(scCeneFuncionario);
        stage.setTitle("Vendas");
        stage.setResizable(false);
        stage.show();
    }
}