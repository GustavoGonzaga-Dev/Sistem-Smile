package categoria;

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

public class CategoriaBoundary extends Application {

    private TextField txtCodigo = new TextField();
    private TextField txtCategoria = new TextField();
    private TextField txtPesquisar = new TextField();

    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAdd = new Button("Adicionar");
    private Button btnRmv = new Button("Remover");
    private Button btnAlt = new Button("Alterar");
    private Button btnCon = new Button("Concluir");
    private Button btnCan = new Button("Cancelar");

    private CategoriaController CategoriaControl = new CategoriaController();
    private boolean valido = false;

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void start(Stage Stage) throws Exception {
        Pane pPane = new Pane();
        pPane.getStylesheets().add(MarcaBoundary.class.getResource("StyleText.css").toExternalForm());
        Scene scCeneCategoria = new Scene(pPane, 400, 250);
        CategoriaController.valida(txtCodigo,txtPesquisar);

        Label lblCodigo = new Label("Codigo:");
        Label lblCategoria = new Label("Categoria:");
        Label lblQuadrado = new Label();

        lblQuadrado.relocate(35, 55);
        lblQuadrado.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        txtPesquisar.relocate(65,10);
        txtPesquisar.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        btnPesquisar.relocate(300, 10);

        lblCodigo.relocate(50, 70);
        txtCodigo.relocate(50, 90);

        lblCategoria.relocate(50, 120);
        txtCategoria.relocate(50, 140);

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

        pPane.getChildren().addAll(lblQuadrado, lblCodigo, txtCodigo, lblCategoria, txtCategoria, btnAdd, btnPesquisar, btnAlt,
                btnRmv, txtPesquisar, btnCan, btnCon);

        btnAdd.setOnAction((e) -> {
            valido = CategoriaControl.ValidarCampos(txtCodigo.getText(), txtCategoria.getText());
            if(valido){
                CategoriaControl.adicionar(boundaryToEntity());
                alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
                alertMess.showAndWait();
                this.entityToBoundary(new Categoria());
            }else{
                alertWarn.setHeaderText("INSIRA OS CAMPOS CORRETAMENTE");
                alertWarn.showAndWait();
            }
        });

        btnPesquisar.setOnAction((e) ->{
            try{
                Categoria Ct = CategoriaControl.pesquisarPorCodigo(Integer.parseInt(txtPesquisar.getText()));
                this.entityToBoundary(Ct);
                if(Ct!= null){
                    btnPesquisar.setVisible(false);
                    txtPesquisar.setEditable(false);
                    txtCodigo.setEditable(false);
                    txtCategoria.setEditable(false);
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
            this.entityToBoundary(new Categoria());
            btnPesquisar.setVisible(true);
            txtPesquisar.setEditable(true);
            txtCodigo.setEditable(true);
            txtCategoria.setEditable(true);
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
            txtCategoria.setEditable(true);
        }));

        btnRmv.setOnAction((event -> {
            try {
                CategoriaControl.excluir(boundaryToEntity());
            }catch (Exception e1) {
                e1.printStackTrace();
            }
            alertMess.setHeaderText("Categoria excluido!");
            alertMess.showAndWait();
            btnCan.fire();
        }));

        btnCon.setOnAction((event -> {
            valido = CategoriaControl.ValidarCampos(txtCodigo.getText(), txtCategoria.getText());
            if (valido){
                CategoriaControl.alterar(boundaryToEntity());
                alertMess.setHeaderText("ALTERADO COM SUCESSO!");
                alertMess.showAndWait();
                btnCon.setVisible(false);
                btnCan.fire();
            } else {
                alertWarn.setHeaderText("Preencha os campos corretamente!");
                alertWarn.showAndWait();
            }
        }));

        Stage.setScene(scCeneCategoria);
        Stage.setTitle("Categoria S2");
        Stage.setResizable(false);
        Stage.show();
    }

    public Categoria boundaryToEntity() {
        Categoria Ct = new Categoria();
        Ct.setCategoria(txtCategoria.getText());
        try {
            Ct.setCodigo(Integer.parseInt(txtCodigo.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Ct;
    }

    public void entityToBoundary(Categoria Ct) {
        if (Ct != null) {
            txtCodigo.setText(String.valueOf(Ct.getCodigo()));
            txtCategoria.setText(Ct.getCategoria());
        } else {
            alertMess.setHeaderText("Categoria NÃO EXISTE.");
            alertMess.showAndWait();
        }
    }
}

