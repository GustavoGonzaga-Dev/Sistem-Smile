package marca;

import funcionario.FuncionarioBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MarcaBoundary extends Application {

    private TextField txtCodigo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtPesquisar = new TextField();

    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAdd = new Button("Adicionar");
    private Button btnRmv = new Button("Remover");
    private Button btnAlt = new Button("Alterar");
    private Button btnCon = new Button("Concluir");
    private Button btnCan = new Button("Cancelar");

    private MarcaController marcaControl = new MarcaController();
    private boolean valido = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void start(Stage Stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneMarca = new Scene(pPane, 400, 250);
        MarcaController.valida(txtCodigo,txtPesquisar);

        Label lblCodigo = new Label("Codigo:");
        Label lblNome = new Label("Nome:");
        Label lblQuadrado = new Label();

        lblQuadrado.relocate(35, 55);
        lblQuadrado.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        txtPesquisar.relocate(65,10);
        txtPesquisar.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        btnPesquisar.relocate(300, 10);

        lblCodigo.relocate(50, 70);
        txtCodigo.relocate(50, 90);

        lblNome.relocate(50, 120);
        txtNome.relocate(50, 140);

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

        pPane.getChildren().addAll(lblQuadrado, lblCodigo, txtCodigo, lblNome, txtNome, btnAdd, btnPesquisar, btnAlt,
                btnRmv, txtPesquisar, btnCan, btnCon);

        btnAdd.setOnAction((e) -> {
           valido = marcaControl.ValidarCampos(txtCodigo.getText(), txtNome.getText());
           if(valido){
               marcaControl.adicionar(boundaryToEntity());
               alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
               alertMess.showAndWait();
               this.entityToBoundary(new Marca());
           }else{
               alertWarn.setHeaderText("INSIRA OS CAMPOS CORRETAMENTE");
               alertWarn.showAndWait();
           }
        });

        btnPesquisar.setOnAction((e) ->{
            try{
                Marca Mc = marcaControl.pesquisarPorCodigo(Integer.parseInt(txtPesquisar.getText()));
                this.entityToBoundary(Mc);
                if(Mc!= null){
                    btnPesquisar.setVisible(false);
                    txtPesquisar.setEditable(false);
                    txtCodigo.setEditable(false);
                    txtNome.setEditable(false);
                    btnAdd.setVisible(false);
                    btnAlt.setVisible(true);
                    btnRmv.setVisible(true);
                    btnCan.setVisible(true);
                }
            }catch (Exception ee){
                alertWarn.setHeaderText("Não Existe");
                alertWarn.showAndWait();
            }
        });

        btnCan.setOnAction((event -> {
            this.entityToBoundary(new Marca());
            btnPesquisar.setVisible(true);
            txtPesquisar.setEditable(true);
            txtCodigo.setEditable(true);
            txtNome.setEditable(true);
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
            txtNome.setEditable(true);
        }));

        btnRmv.setOnAction((event -> {
            try {
                marcaControl.excluir(boundaryToEntity());
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            alertMess.setHeaderText("Marca excluida!");
            alertMess.showAndWait();
            btnCan.fire();
        }));

        btnCon.setOnAction((event -> {
            valido = marcaControl.ValidarCampos(txtCodigo.getText(), txtNome.getText());
            if (valido){
                marcaControl.alterar(boundaryToEntity());
                alertMess.setHeaderText("ALTERADO COM SUCESSO!");
                alertMess.showAndWait();
                btnCon.setVisible(false);
                btnCan.fire();
            } else {
                alertWarn.setHeaderText("Preencha os campos corretamente!");
                alertWarn.showAndWait();
            }
        }));

        Stage.setScene(scCeneMarca);
        Stage.setTitle("Marca S2");
        Stage.setResizable(false);
        Stage.show();
    }

    public Marca boundaryToEntity() {
        Marca Mc = new Marca();
        Mc.setNomeMarca(txtNome.getText());
        try {
            Mc.setCodigo(Integer.parseInt(txtCodigo.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mc;
    }

    public void entityToBoundary(Marca Mc) {
        if (Mc != null) {
            txtCodigo.setText(String.valueOf(Mc.getCodigo()));
            txtNome.setText(Mc.getNomeMarca());
        } else {
            alertMess.setHeaderText("MARCA NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }
}
