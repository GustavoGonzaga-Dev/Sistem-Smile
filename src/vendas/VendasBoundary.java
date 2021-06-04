package vendas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        Scene scCeneFuncionario = new Scene(pPane, 550, 560);

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

        lblCodigo.relocate(40, 10);
        txtCodigo.relocate(20, 30);

        lblCodigoCliente.relocate(40, 80);
        txtCodigoCliente.relocate(20, 100);

        lblNome.relocate(40, 140);
        txtNome.relocate(20, 160);

        lblEndereco.relocate(40, 200);
        txtEndereco.relocate(20, 220);

        lblData.relocate(40, 260);
        txtData.relocate(20, 280);

        lblSituacao.relocate(40, 400);
        Situacao.relocate(40, 420);

        table.relocate(200, 0);
        table.getColumns().addAll(codigoProduto, nomeProduto, quantidade, preco);

        lblTotal.relocate(300, 420);
        txtTotal.relocate(373, 413);
        txtTotal.setDisable(true);

        btnAlterar.relocate(65, 460);
        btnPesq.relocate(65, 320);
        btnBack.relocate(420, 480);

        pPane.getChildren().addAll(lblNome, lblCodigo, txtCodigo, lblCodigoCliente, txtCodigoCliente, txtNome,
                lblEndereco, txtEndereco, lblData, txtData, lblSituacao, btnAlterar, table, Situacao, btnBack,
                lblTotal, txtTotal, btnPesq);

        stage.setScene(scCeneFuncionario);
        stage.setTitle("Vendas");
        stage.setResizable(false);
        stage.show();
    }
}