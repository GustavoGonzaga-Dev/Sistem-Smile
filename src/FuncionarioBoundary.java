import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FuncionarioBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtConfEmail = new TextField();
    private TextField txtCodigo = new TextField();
    private TextField txtSenha = new TextField();
    private TextField txtConfSenha = new TextField();

    private Label lblImg = new Label();

    private ComboBox<String> cbPermissao = new ComboBox<>();
    private String perm[] = { "Master", "Comum"};

    private Button btnCarregarImagem = new Button("Carregar imagem");
    private Button btnAdd = new Button("Adicionar");
    private Button btnPesq = new Button("Pesquisar");
    private Button btnExcluir = new Button("Excluir");
    private Button btnAlterar = new Button("Alterar");
    private Button btnConcluir = new Button("Concluir");

    private boolean valido = false;
    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private FuncionarioController control = new FuncionarioController();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneFuncionario = new Scene(pPane, 500, 330);

        cbPermissao.getItems().addAll(perm);

        Label lblCodigo = new Label("Codigo:");
        Label lblEmail = new Label("Email:");
        Label lblConfEmail = new Label("Confirmar Email:");
        Label lblNome = new Label("Nome:");
        Label lblPermissao = new Label("Permissão:");
        Label lblSenha = new Label("Senha:");
        Label lblConfSenha = new Label("Confirmar Senha:");
        Label lblTextoPesq = new Label("*Para pesquisar, insira o codigo do Funcionario.");
        Label lblTextoAlt = new Label("*Para alterar, insira o valor no campo desejado.");
        Label lblTextoEx = new Label("*Para excluir, click no campo \n(codigo) e depois no botão excluir.");

        lblNome.relocate(140, 23);
        txtNome.relocate(250, 20);
        txtNome.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblEmail.relocate(140, 63);
        txtEmail.relocate(250, 60);
        txtEmail.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblConfEmail.relocate(140, 103);
        txtConfEmail.relocate(250, 100);
        txtConfEmail.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblCodigo.relocate(20, 153);
        txtCodigo.relocate(70, 150);

        lblTextoPesq.relocate(70, 180);
        lblTextoPesq.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblTextoAlt.relocate(70, 180);
        lblTextoAlt.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblTextoEx.relocate(10, 280);
        lblTextoEx.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblSenha.relocate(20, 223);
        txtSenha.relocate(70, 220);

        lblConfSenha.relocate(230, 223);
        txtConfSenha.relocate(340, 220);

        lblPermissao.relocate(230, 153);
        cbPermissao.relocate(340, 150);

        lblImg.relocate(0,0);
        btnCarregarImagem.relocate(20, 100);

        btnAlterar.relocate(350, 280);
        btnAdd.relocate(180, 280);
        btnPesq.relocate(250, 280);
        btnConcluir.relocate(220, 280);
        btnExcluir.relocate(180, 280);
        btnExcluir.getStylesheets().add(FuncionarioBoundary.class.getResource("btnExcluirStyle.css").toExternalForm());

        pPane.getChildren().addAll(lblNome, txtNome, lblEmail, txtEmail, lblConfEmail, txtConfEmail, lblCodigo, txtCodigo, lblPermissao,
                cbPermissao, btnAdd, btnPesq, btnExcluir, btnAlterar, btnConcluir, lblSenha, txtSenha, lblConfSenha, txtConfSenha,
                btnCarregarImagem, lblImg, lblTextoPesq, lblTextoAlt, lblTextoEx);

        btnExcluir.setVisible(false);
        btnAlterar.setVisible(false);
        btnConcluir.setVisible(false);
        lblTextoAlt.setVisible(false);
        lblTextoEx.setVisible(false);

        btnCarregarImagem.setOnAction((e)->{
            FileChooser fil_chooser = new FileChooser();
            File file = fil_chooser.showOpenDialog(stage);
            if (file != null) {
                lblImg.setText(file.getAbsolutePath());
            }
        });

        btnAdd.setOnAction((e)->{
            valido = control.validarCampos(txtNome.getText(), txtEmail.getText(), txtConfEmail.getText(), txtCodigo.getText(),
                    cbPermissao.getValue(), txtSenha.getText(), txtConfSenha.getText());
            control.adicionar(boundaryToEntity());
            this.entityToBoundary(new Funcionario());
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();
        });

        btnPesq.setOnAction((e)->{
            Funcionario Fn = control.pesquisarPorCodigo(Long.parseLong(txtCodigo.getText()));
            this.entityToBoundary(Fn);
            if(Fn != null){
                lblTextoEx.setVisible(true);
                btnAdd.setVisible(false);
                btnAlterar.setVisible(true);
                btnExcluir.setVisible(true);
            }
        });

        btnExcluir.setOnAction((e)->{
            try {
                control.excluirPorCodigo(Long.parseLong(txtCodigo.getText()));
                alertMess.setHeaderText("Funcionario excluido!");
                alertMess.showAndWait();
                this.entityToBoundary(new Funcionario());

                lblTextoEx.setVisible(false);
                btnAdd.setVisible(true);
                btnAlterar.setVisible(false);
                btnExcluir.setVisible(false);
            }catch (Exception e1){
                alertWarn.setHeaderText("Preencha o campo de (CODIGO) corretamente!");
                alertWarn.showAndWait();
            }
        });

        btnAlterar.setOnAction((e)->{
            lblTextoPesq.setVisible(false);
            lblTextoEx.setVisible(false);
            lblTextoAlt.setVisible(true);
            btnConcluir.setVisible(true);
            btnExcluir.setVisible(false);
            btnAlterar.setVisible(false);
            btnPesq.setVisible(false);
            txtCodigo.setEditable(false);
        });

        btnConcluir.setOnAction((e)->{
            valido = control.validarCampos(txtNome.getText(), txtEmail.getText(), txtConfEmail.getText(), txtCodigo.getText(),
                    cbPermissao.getValue(), txtSenha.getText(), txtConfSenha.getText());
            control.alterar(boundaryToEntity());
            this.entityToBoundary(new Funcionario());
            alertMess.setHeaderText("ALTERADO COM SUCESSO!");
            alertMess.showAndWait();
            btnConcluir.setVisible(false);
            btnExcluir.setVisible(false);
            btnAdd.setVisible(true);
            btnPesq.setVisible(true);
            txtCodigo.setEditable(true);
        });

        stage.setScene(scCeneFuncionario);
        stage.setTitle("Funcionário S2");
        stage.show();
    }

    public Funcionario boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Funcionario Fn = new Funcionario();
        if (valido) {
            Fn.setNome(txtNome.getText());
            Fn.setEmail(txtEmail.getText());
            Fn.setConfEmail(txtConfEmail.getText());
            Fn.setSenha(txtSenha.getText());
            Fn.setConfSenha(txtConfSenha.getText());
            try {
                Fn.setCodigo(Long.parseLong(txtCodigo.getText()));
                Fn.setPermissao(cbPermissao.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            alertWarn.setHeaderText("PREENCHA TODOS OS CAMPOS CORRETAMENTE!");
            alertWarn.showAndWait();
        }
        return Fn;
    }

    public void entityToBoundary(Funcionario Fn) { //consultando a entidade e jogando na tela
        if (Fn != null) {
            txtCodigo.setText(String.valueOf(Fn.getCodigo()));
            txtNome.setText(Fn.getNome());
            txtEmail.setText(Fn.getEmail());
            txtConfEmail.setText(Fn.getConfEmail());
            txtSenha.setText(Fn.getSenha());
            txtConfSenha.setText(Fn.getConfSenha());
            cbPermissao.setValue(Fn.getPermissao());
        }else{
            alertMess.setHeaderText("Dados incorretos.");
            alertMess.showAndWait();
        }
    }

    public static void main(String[] args) {
        Application.launch(FuncionarioBoundary.class, args);
    }
}
