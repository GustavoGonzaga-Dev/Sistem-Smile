package menu;

import estoque.EstoqueBoundary;
import funcionario.FuncionarioBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import vendas.VendasBoundary;

public class MenuBoundary extends Application {

    private Button btnEstoque = new Button("Estoque");
    private Button btnFuncionario = new Button("Gerenciar \n" + "Funcionario");
    private Button btnCaixa = new Button("Caixa");
    private Button btnVenda = new Button("Venda");

    private FuncionarioBoundary funcTela = new FuncionarioBoundary();
    private EstoqueBoundary estTela = new EstoqueBoundary();
    private VendasBoundary vendTela = new VendasBoundary();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPaine = new BorderPane();
        GridPane painel = new GridPane();
        Scene scene = new Scene(borderPaine, 500,330);

        painel.getStylesheets().add(MenuBoundary.class.getResource("StylesMenu.css").toExternalForm());

        borderPaine.getChildren().add(painel);
        painel.add(btnEstoque, 0,1);
        painel.add(btnFuncionario,1,1);
        painel.add(btnVenda,0,2);
        painel.add(btnCaixa,1,2);
        btnCaixa.setDisable(true);
        painel.setVgap(30);
        painel.setHgap(50);
        painel.setTranslateX(125);
        painel.setTranslateY(30);

        btnFuncionario.setOnAction((e) -> {
            Stage stageFunc = new Stage();
            try {
                funcTela.start(stageFunc);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        btnEstoque.setOnAction((event -> {
            Stage stageEst = new Stage();
            try {
                estTela.start(stageEst);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnVenda.setOnAction((event -> {
            Stage stageVend = new Stage();
            try {
                vendTela.start(stageVend);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        stage.setScene(scene);
        stage.setTitle("Menu S2");
        stage.setResizable(false);
        stage.show();
    }

    public void menuComum(String nvnperm){
        if (nvnperm.contains("COMUM")){
            btnFuncionario.setVisible(false);
        }
    }
}
