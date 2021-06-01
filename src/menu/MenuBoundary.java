package menu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MenuBoundary extends Application {

    private TextField txtBuscar = new TextField();

    private Button btnEstoque = new Button("Estoque");
    private Button btnFuncionario = new Button("Gerenciar \n" + "Funcionario");
    private Button btnCaixa = new Button("Caixa");
    private Button btnVenda = new Button("Venda");

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPaine = new BorderPane();
        GridPane painel = new GridPane();
        Scene scene = new Scene(borderPaine, 500,330);

        painel.getStylesheets().add(MenuBoundary.class.getResource("StylesMenu.css").toExternalForm());

        borderPaine.getChildren().add(painel);
        borderPaine.setTop(txtBuscar);
        BorderPane.setAlignment(txtBuscar, Pos.CENTER);
        painel.add(btnEstoque, 0,1);
        painel.add(btnFuncionario,1,1);
        painel.add(btnVenda,0,2);
        painel.add(btnCaixa,1,2);
        painel.setVgap(30);
        painel.setHgap(50);
        painel.setTranslateX(125);
        painel.setTranslateY(30);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Menu S2");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(MenuBoundary.class, args);
    }

}
