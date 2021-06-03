package estoque;

import categoria.CategoriaBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import marca.MarcaBoundary;
import tamanho.TamanhoBoundary;


public class EstoqueBoundary extends Application {

    private Button btnCat = new Button("Gerenciar Categorias");
    private Button btnTam = new Button("Gerenciar Tamanhos");
    private Button btnMar = new Button("Gerenciar Marcas");

    private CategoriaBoundary catTela = new CategoriaBoundary();
    private TamanhoBoundary tamTela = new TamanhoBoundary();
    private MarcaBoundary marTela = new MarcaBoundary();

    @Override
    public void start(Stage stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneEstoque = new Scene(pPane, 500, 330);

        btnCat.relocate(50, 50);
        btnMar.relocate(50, 150);
        btnTam.relocate(50, 250);



        pPane.getChildren().addAll(btnCat, btnMar, btnTam);

        btnCat.setOnAction((event -> {
            Stage stageCat = new Stage();
            try {
                catTela.start(stageCat);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnMar.setOnAction((event -> {
            Stage stageMar = new Stage();
            try {
                marTela.start(stageMar);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        btnTam.setOnAction((event -> {
            Stage stageTam = new Stage();
            try {
                tamTela.start(stageTam);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }));

        stage.setScene(scCeneEstoque);
        stage.setTitle("Estoque S2");
        stage.setResizable(false);
        stage.show();
    }
}
