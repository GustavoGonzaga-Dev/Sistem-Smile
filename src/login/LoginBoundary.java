package login;

import funcionario.FuncionarioBoundary;
import funcionario.FuncionarioController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.MenuBoundary;

import java.io.FileInputStream;

public class LoginBoundary extends Application {

    private TextField password = new TextField();
    private TextField txtEmail = new TextField();
    private PasswordField txtSenha = new PasswordField();

    private CheckBox check = new CheckBox("Mostrar Senha");

    private Button btnEntrar = new Button("Entrar");

    private LoginController logControl = new LoginController();
    private FuncionarioController adm = new FuncionarioController();

    private Alert alertWarn = new Alert(Alert.AlertType.WARNING);
    private Alert alertMess = new Alert(Alert.AlertType.INFORMATION);

    private boolean permitido = false;

    private MenuBoundary menuTela = new MenuBoundary();

    public static void main(String[] args) {
        Application.launch(LoginBoundary.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneLogin = new Scene(pPane, 500, 330);
        adm.admin();

        Label lblEmail = new Label("Email:");
        Label lblSenha = new Label("Senha:");

        Image img = new Image(new FileInputStream("img/logoPandaVermelho.png"));
        ImageView imgLogo = new ImageView(img);
        imgLogo.relocate(165, -10);
        imgLogo.setFitHeight ( 185 );
        imgLogo.setFitWidth ( 160 );

        lblEmail.relocate(140, 160);
        txtEmail.relocate(140, 180);
        txtEmail.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        lblSenha.relocate(140, 220);
        txtSenha.relocate(140, 240);
        txtSenha.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        check.relocate(370,243);
        check.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val)->{
            if(check.isSelected()){
                password.setText(txtSenha.getText());
                password.setVisible(true);
                txtSenha.setVisible(false);
                return;
            }
            txtSenha.setText(password.getText());
            txtSenha.setVisible(true);
            password.setVisible(false);
        });
        password.setVisible(false);
        password.relocate(140,240);
        password.getStylesheets().add(FuncionarioBoundary.class.getResource("Style.css").toExternalForm());

        btnEntrar.relocate(225, 280);

        pPane.getChildren().addAll(lblEmail, txtEmail, btnEntrar, lblSenha, txtSenha, imgLogo, check, password);

        btnEntrar.setOnAction((e) -> {
            permitido = logControl.validarLogin(boundaryToEntity());

            if(permitido){
                alertMess.setHeaderText("tudo certo, bora pro menu");
                alertMess.showAndWait();
                try {
                    String nvnPerm = logControl.getPermissao();
                    Stage stage = new Stage();
                    primaryStage.close();
                    menuTela.start(stage);
                    menuTela.menuComum(nvnPerm);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }else{
                alertWarn.setHeaderText("ta errado");
                alertWarn.showAndWait();
            }
        });

        primaryStage.setScene(scCeneLogin);
        primaryStage.setTitle("Login S2");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Login boundaryToEntity() {
        Login Lg = new Login();
        Lg.setEmail(txtEmail.getText());
        Lg.setSenha(txtSenha.getText());
        return Lg;
    }
}
