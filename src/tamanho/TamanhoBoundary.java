package tamanho;

import funcionario.FuncionarioBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import marca.MarcaBoundary;

public class TamanhoBoundary extends Application {

    private TextField txtCodigo = new TextField();
    private TextField txtTamanho = new TextField();
    private TextField txtPesquisar = new TextField();

    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAdd = new Button("Adicionar");
    private Button btnRmv = new Button("Remover");
    private Button btnAlt = new Button("Alterar");
    private Button btnCon = new Button("Concluir");
    private Button btnCan = new Button("Cancelar");

    private TamanhoController tamanhoControl = new TamanhoController();
    private boolean valido = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void start(Stage Stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneTamanho = new Scene(pPane, 400, 250);

        Label lblCodigo = new Label("Codigo:");
        Label lblTamanho = new Label("Tamanho:");
        Label lblQuadrado = new Label();

        lblQuadrado.relocate(35, 55);
        lblQuadrado.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        txtPesquisar.relocate(65,10);
        txtPesquisar.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        btnPesquisar.relocate(300, 10);

        lblCodigo.relocate(50, 70);
        txtCodigo.relocate(50, 90);

        lblTamanho.relocate(50, 120);
        txtTamanho.relocate(50, 140);

        btnAdd.relocate(100, 200);
        btnAlt.relocate(50,200);
        btnRmv.relocate(300, 200);
        btnCon.relocate(150, 200);
        btnCan.relocate(150, 200);

        btnAlt.setVisible(false);
        btnCon.setVisible(false);
        btnRmv.setVisible(false);
        btnCan.setVisible(false);

        btnRmv.getStylesheets().add(FuncionarioBoundary.class.getResource("btnExcluirStyle.css").toExternalForm());

        pPane.getChildren().addAll(lblQuadrado, lblCodigo, txtCodigo, lblTamanho, txtTamanho, btnAdd, btnPesquisar, btnAlt,
                btnRmv, txtPesquisar, btnCan, btnCon);

        btnAdd.setOnAction((e) -> {
            valido = tamanhoControl.ValidarCampos(txtCodigo.getText(), txtTamanho.getText());
            if(valido){
                tamanhoControl.adicionar(boundaryToEntity());
                alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
                alertMess.showAndWait();
                this.entityToBoundary(new Tamanho());
            }else{
                alertWarn.setHeaderText("INSIRA OS CAMPOS CORRETAMENTE");
                alertWarn.showAndWait();
            }
        });

        btnPesquisar.setOnAction((e) ->{
            Tamanho Tm = tamanhoControl.pesquisarPorCodigo(Integer.parseInt(txtPesquisar.getText()));
            this.entityToBoundary(Tm);
            if(Tm!= null){
                btnPesquisar.setVisible(false);
                txtPesquisar.setEditable(false);
                txtCodigo.setEditable(false);
                txtTamanho.setEditable(false);
                btnAdd.setVisible(false);
                btnAlt.setVisible(true);
                btnRmv.setVisible(true);
                btnCan.setVisible(true);
            }
        });

        btnCan.setOnAction((event -> {
            this.entityToBoundary(new Tamanho());
            btnPesquisar.setVisible(true);
            txtPesquisar.setEditable(true);
            txtCodigo.setEditable(true);
            txtTamanho.setEditable(true);
            btnAdd.setVisible(true);
            btnAlt.setVisible(false);
            btnRmv.setVisible(false);
            btnCan.setVisible(false);
        }));

        btnAlt.setOnAction((event -> {
            btnAlt.setVisible(false);
            btnRmv.setVisible(false);
            btnCon.setVisible(true);
            btnCan.setVisible(false);
            txtTamanho.setEditable(true);
        }));

        btnRmv.setOnAction((event -> {
            try {
                tamanhoControl.excluir(boundaryToEntity());
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            alertMess.setHeaderText("Tamanho excluido!");
            alertMess.showAndWait();
            btnCan.fire();
        }));

        btnCon.setOnAction((event -> {
            valido = tamanhoControl.ValidarCampos(txtCodigo.getText(), txtTamanho.getText());
            if (valido){
                tamanhoControl.alterar(boundaryToEntity());
                alertMess.setHeaderText("ALTERADO COM SUCESSO!");
                alertMess.showAndWait();
                btnCon.setVisible(false);
                btnCan.fire();
            } else {
                alertWarn.setHeaderText("Preencha os campos corretamente!");
                alertWarn.showAndWait();
            }
        }));

        Stage.setScene(scCeneTamanho);
        Stage.setTitle("Tamanho S2");
        Stage.setResizable(false);
        Stage.show();
    }

    public Tamanho boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Tamanho Tm = new Tamanho();
        Tm.setTamanho(txtTamanho.getText());
        try {
            Tm.setCodigo(Integer.parseInt(txtCodigo.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Tm;
    }

    public void entityToBoundary(Tamanho Tm) { //consultando a entidade e jogando na tela
        if (Tm != null) {
            txtCodigo.setText(String.valueOf(Tm.getCodigo()));
            txtTamanho.setText(Tm.getTamanho());
        } else {
            alertMess.setHeaderText("TAMANHO NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }
}
