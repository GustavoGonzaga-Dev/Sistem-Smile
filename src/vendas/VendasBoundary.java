package vendas;

import funcionario.Funcionario;
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

    private ComboBox<String> cbSituacao = new ComboBox<>();
    private String status[] = {"Preparando", "Cancelado", "Enviado", "Entregue"};

    private Button btnPesq = new Button("Pesquisar");
    private Button btnAlterarStatus = new Button("Alterar");
    private Button btnFechar = new Button("Fechar");

    private TableView table = new TableView();

    private VendasController vendaControl = new VendasController();

    @Override
    public void start(Stage stage) {
        Pane pPane = new Pane();
        Scene scCeneVenda = new Scene(pPane, 700, 550);

        Rectangle shape = new Rectangle();
        shape.setHeight(250);
        shape.setWidth(200);
        shape.setArcHeight(30.0);
        shape.setArcWidth(30.0);

        shape.setFill(Color.TRANSPARENT);
        shape.setStroke(Color.GRAY);

        table.setMinSize(350,200);
        cbSituacao.getItems().addAll(status);

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
        cbSituacao.relocate(60, 420);

        table.relocate(300, 20);
        table.getColumns().addAll(codigoProduto, nomeProduto, quantidade, preco);
        codigoProduto.prefWidthProperty().bind(table.widthProperty().multiply(0.38));


        lblTotal.relocate(330, 440);
        txtTotal.relocate(400, 438);
        txtTotal.setDisable(true);

        shape.relocate(20,110);

        btnAlterarStatus.relocate(85, 460);
        btnPesq.relocate(80, 70);
        btnFechar.relocate(420, 480);

        pPane.getChildren().addAll(shape, lblNome, lblCodigo, txtCodigo, lblCodigoCliente, txtCodigoCliente, txtNome,
                lblEndereco, txtEndereco, lblData, txtData, lblSituacao, btnAlterarStatus, table, cbSituacao, btnFechar,
                lblTotal, txtTotal, btnPesq);

        btnAlterarStatus.setOnAction((event -> {
            cbSituacao.getValue();
        }));

        btnPesq.setOnAction((event -> {
             vendaControl.pesquisaCodigo(Integer.parseInt(txtCodigo.getText()));
        }));

        btnFechar.setOnAction((event -> {
            stage.close();
        }));

        stage.setScene(scCeneVenda);
        stage.setTitle("Vendas");
        stage.setResizable(false);
        stage.show();
    }

    public void entityToBoundary(Vendas vd) { //consultando a entidade e jogando na tela
        if (vd != null) {
            txtCodigo.setText(String.valueOf(vd.getCodigoVenda()));
            txtNome.setText(vd.getNomeCliente());
            txtCodigoCliente.setText(String.valueOf(vd.getCodigoCliente()));
            txtData.setText(String.valueOf(vd.getData()));
            txtEndereco.setText(String.valueOf(vd.getEndereco()));
            txtTotal.setText(String.valueOf(vd.getValorTotal()));
            cbSituacao.setValue(String.valueOf(vd.getSituacao()));


        } else {

        }
    }

}