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

    private ComboBox<String> cbSituacao = new ComboBox<>();
    private String status[] = {"Preparando", "Cancelado", "Enviado", "Entregue"};

    private Button btnPesq = new Button("Pesquisar");
    private Button btnAlterarStatus = new Button("Alterar");
    private Button btnFechar = new Button("Fechar");

    private VendasController vendaControl = new VendasController();
    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

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

        vendaControl.geraTabela();
        cbSituacao.getItems().addAll(status);

        Label lblCodigo = new Label("Codigo da venda:");
        Label lblCodigoCliente = new Label("Código do cliente:");
        Label lblEndereco = new Label("Endereço de entrega:");
        Label lblNome = new Label("Nome Cliente:");
        Label lblData = new Label("Data:");
        Label lblSituacao = new Label("Situação de venda:");
        Label lblTotal = new Label("Valor total:");

        txtTotal.setEditable(false);
        txtEndereco.setEditable(false);
        txtNome.setEditable(false);
        txtData.setEditable(false);
        txtCodigoCliente.setEditable(false);
        btnAlterarStatus.setDisable(true);
        cbSituacao.setDisable(true);

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

        vendaControl.getTable().relocate(300,20);
        pPane.getChildren().addAll(vendaControl.getTable());
        lblTotal.relocate(330, 440);
        txtTotal.relocate(400, 438);
        txtTotal.setDisable(true);

        shape.relocate(20,110);

        btnAlterarStatus.relocate(85, 460);
        btnPesq.relocate(80, 70);
        btnFechar.relocate(420, 480);

        pPane.getChildren().addAll(shape, lblNome, lblCodigo, txtCodigo, lblCodigoCliente, txtCodigoCliente, txtNome,
                lblEndereco, txtEndereco, lblData, txtData, lblSituacao, btnAlterarStatus, cbSituacao, btnFechar,
                lblTotal, txtTotal, btnPesq);

        btnAlterarStatus.setOnAction((event -> {
            vendaControl.alterar(boundaryToEntity());
            alertMess.setHeaderText("ALTERADO COM SUCESSO!");
            alertMess.showAndWait();
            entityToBoundary(new Vendas());
        }));

        btnPesq.setOnAction((event -> {
            entityToBoundary(vendaControl.pesquisaCodigo(Integer.parseInt(txtCodigo.getText())));
            txtTotal.setText(String.valueOf(vendaControl.total(Integer.parseInt(txtCodigo.getText()))));
            cbSituacao.setDisable(false);
            btnAlterarStatus.setDisable(false);
        }));

        btnFechar.setOnAction((event -> {
            entityToBoundary(new Vendas());
            stage.close();
        }));

        stage.setScene(scCeneVenda);
        stage.setTitle("Vendas");
        stage.setResizable(false);
        stage.show();
    }

    public void entityToBoundary(Vendas vd) {
        if(vd != null) {
            txtCodigo.setText(String.valueOf(vd.getCodigoVenda()));
            txtNome.setText(vd.getNomeCliente());
            txtCodigoCliente.setText(String.valueOf(vd.getCodigoCliente()));
            txtData.setText(vd.getData());
            txtEndereco.setText(String.valueOf(vd.getEndereco()));
            cbSituacao.setValue(String.valueOf(vd.getSituacao()));
        }else{
        alertWarn.setHeaderText("VENDA NÃO EXISTE");
        alertWarn.showAndWait();
        }
    }

    public Vendas boundaryToEntity(){
        Vendas vd = new Vendas();
        vd.setCodigoVenda(Integer.parseInt(txtCodigo.getText()));
        vd.setSituacao(cbSituacao.getValue());
        return vd;
    }
}