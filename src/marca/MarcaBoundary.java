package marca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.Login;

public class MarcaBoundary extends Application {

    private TextField txtCodigo = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtPesquisar = new TextField();

    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAdd = new Button("Add");
    private Button btnRmv = new Button("Rmv");
    private Button btnAlt = new Button("Alt");

    private MarcaController marcaControl = new MarcaController();

    @Override
    public void start(Stage Stage) throws Exception {
        Pane pPane = new Pane();
        Scene scCeneMarca = new Scene(pPane, 400, 250);

        Label lblCodigo = new Label("Codigo:");
        Label lblNome = new Label("Nome:");
        Label lblQuadrado = new Label();

        lblQuadrado.relocate(35, 55);
        lblQuadrado.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        txtPesquisar.relocate(65,10);
        txtPesquisar.getStylesheets().add(MarcaBoundary.class.getResource("MarcaStyles.css").toExternalForm());

        btnPesquisar.relocate(300, 10);

        lblCodigo.relocate(50, 70);
        txtCodigo.relocate(50, 90);

        lblNome.relocate(50, 120);
        txtNome.relocate(50, 140);

        btnAdd.relocate(100, 200);

        pPane.getChildren().addAll(lblQuadrado, lblCodigo, txtCodigo, lblNome, txtNome, btnAdd);

        btnAdd.setOnAction((e) -> {
           marcaControl.adicionar(boundaryToEntity());
        });

        Stage.setScene(scCeneMarca);
        Stage.setTitle("Marca S2");
        Stage.setResizable(false);
        Stage.show();
    }

    public Marca boundaryToEntity() { //jogando tudo que ta na tela, pra entidade
        Marca Mc = new Marca();
        Mc.setCodigo(Integer.parseInt(txtCodigo.getText()));
        Mc.setNomeMarca(txtNome.getText());
        return Mc;
    }
}
