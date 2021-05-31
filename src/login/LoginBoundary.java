package login;

import funcionario.Funcionario;
import funcionario.FuncionarioBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginBoundary extends Application {

    private TextField txtEmail = new TextField();
    private TextField txtSenha = new TextField();

    private Button btnEntrar = new Button("Entrar");

    private LoginController logControl = new LoginController();

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private boolean permitido = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneLogin = new Scene(pPane, 500, 330);

        Label lblEmail = new Label("Email:");
        Label lblSenha = new Label("Senha:");

        lblEmail.relocate(140, 160);
        txtEmail.relocate(140, 180);
        txtEmail.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblSenha.relocate(140, 220);
        txtSenha.relocate(140, 240);
        txtSenha.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        btnEntrar.relocate(225, 280);

        pPane.getChildren().addAll(lblEmail, txtEmail, btnEntrar, lblSenha, txtSenha);

        btnEntrar.setOnAction((e) -> {
            permitido = LoginController.validarLogin(boundaryToEntity());

            if(permitido){
                alertMess.setHeaderText("Email ta certo");
                alertMess.showAndWait();
            }else{
                alertWarn.setHeaderText("Email ta errado");
                alertWarn.showAndWait();
            }
        });

        primaryStage.setScene(scCeneLogin);
        primaryStage.setTitle("Login S2");
        primaryStage.show();
    }

    public Login boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Login Lg = new Login();
        Lg.setEmail(txtEmail.getText());
        Lg.setSenha(txtSenha.getText());
        return Lg;
    }

}
