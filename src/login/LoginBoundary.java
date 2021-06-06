package login;

import funcionario.FuncionarioBoundary;
import funcionario.FuncionarioController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.MenuBoundary;

import java.io.FileInputStream;

public class LoginBoundary extends Application {

    private TextField txtEmail = new TextField();
    private TextField txtSenha = new TextField();

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

        btnEntrar.relocate(225, 280);

        pPane.getChildren().addAll(lblEmail, txtEmail, btnEntrar, lblSenha, txtSenha, imgLogo);

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

    public Login boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Login Lg = new Login();
        Lg.setEmail(txtEmail.getText());
        Lg.setSenha(txtSenha.getText());
        return Lg;
    }
}
