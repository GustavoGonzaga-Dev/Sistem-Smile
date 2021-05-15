import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FuncionarioBoundary extends Application {

    private TextField txtNome = new TextField();
    private TextField txtEmail = new TextField();
    private TextField txtCodigo = new TextField();
    private TextField txtSenha = new TextField();
    private TextField txtConfSenha = new TextField();

    private ComboBox<String> cbPermissao = new ComboBox<>();
    private String perm[] = { "Master", "Comum"};

    private Button btnAdd = new Button("Adicionar");
    private Button btnPesq = new Button("Pesquisar");

    private boolean valido = false;
    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private FuncionarioController control = new FuncionarioController();

    public static void main(String[] args) {
        Application.launch(FuncionarioBoundary.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCene = new Scene(pPane, 500, 230);

        cbPermissao.getItems().addAll(perm);

        Label lblCodigo = new Label("Codigo:");
        Label lblEmail = new Label("Email:");
        Label lblNome = new Label("Nome:");
        Label lblPermissao = new Label("Permissão:");
        Label lblSenha = new Label("Senha:");
        Label lblConfSenha = new Label("Confirmar Senha:");

        lblNome.relocate(230, 23);
        txtNome.relocate(280, 20);

        lblEmail.relocate(230, 63);
        txtEmail.relocate(280, 60);

        lblCodigo.relocate(20, 113);
        txtCodigo.relocate(70, 110);

        lblSenha.relocate(20, 163);
        txtSenha.relocate(70, 160);

        lblConfSenha.relocate(230, 163);
        txtConfSenha.relocate(340, 160);

        lblPermissao.relocate(230, 113);
        cbPermissao.relocate(340, 110);

        btnAdd.relocate(180, 200);
        btnPesq.relocate(250, 200);

        pPane.getChildren().addAll(lblNome, txtNome, lblEmail, txtEmail, lblCodigo, txtCodigo, lblPermissao,
                cbPermissao, btnAdd, btnPesq, lblSenha, txtSenha, lblConfSenha, txtConfSenha);

        btnAdd.setOnAction((e)->{
            control.adicionar(boundaryToEntity());
            this.entityToBoundary(new Funcionario());
        });

        btnPesq.setOnAction((e)->{
            Funcionario Fn = control.pesquisarPorCodigo(Long.parseLong(txtCodigo.getText()));
            this.entityToBoundary(Fn);
        });

        stage.setScene(scCene);
        stage.setTitle("Funcionário S2");
        stage.show();
    }

    public Funcionario boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Funcionario Fn = new Funcionario();
        valido = control.validarSenha(txtSenha.getText(), txtConfSenha.getText());
        if (valido) {
            Fn.setNome(txtNome.getText());
            Fn.setEmail(txtEmail.getText());
            Fn.setSenha(txtSenha.getText());
            Fn.setConfSenha(txtConfSenha.getText());
            try {
                Fn.setCodigo(Long.parseLong(txtCodigo.getText()));
                Fn.setPermissao(cbPermissao.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
            alertMess.setHeaderText("CADASTRADO COM SUCESSO!");
            alertMess.showAndWait();
        }else {
            alertWarn.setHeaderText("SENHAS NÃO IGUAIS!!");
            alertWarn.showAndWait();
        }
        return Fn;
    }

    public void entityToBoundary(Funcionario Fn) { //consultando a entidade e jogando na tela
        if (Fn != null) {
            txtCodigo.setText(String.valueOf(Fn.getCodigo()));
            txtNome.setText(Fn.getNome());
            txtEmail.setText(Fn.getEmail());
            txtSenha.setText(Fn.getSenha());
            txtConfSenha.setText(Fn.getConfSenha());
            cbPermissao.setValue(Fn.getPermissao());
        }
    }
}
