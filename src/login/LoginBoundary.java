package login;

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
        pPane.getStylesheets().add(LoginBoundary.class.getResource("LoginStyle.css").toExternalForm());
        Scene scCeneLogin = new Scene(pPane, 500, 330);
        adm.admin();

        Label lblEmail = new Label("Email:");
        Label lblSenha = new Label("Senha:");

        Image img = new Image(new FileInputStream("img/logopd3.png"));
        ImageView imgLogo = new ImageView(img);
        imgLogo.relocate(-25, 15);
        imgLogo.setFitHeight ( 300 );
        imgLogo.setFitWidth ( 250 );

        lblEmail.relocate(240, 90);
        txtEmail.relocate(240, 110);

        lblSenha.relocate(240, 150);
        txtSenha.relocate(240, 170);

        check.relocate(245,200);
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
        password.relocate(240,170);

        btnEntrar.relocate(325, 250);

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
