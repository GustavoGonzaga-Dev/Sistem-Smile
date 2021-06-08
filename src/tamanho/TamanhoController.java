package tamanho;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class TamanhoController {

    private TamanhoDAO tmd = new TamanhoDAOImpl();

    public void adicionar(Tamanho Tm) {
        tmd.adicionar(Tm);
    }

    public Tamanho pesquisarPorCodigo(int txtPesquisar) {
        return tmd.pesquisarPorCodigo(txtPesquisar);
    }

    public void excluir(Tamanho Tm) {
        tmd.excluir(Tm);
    }

    public void alterar(Tamanho Tm) {
        tmd.alterar(Tm);
    }

    public boolean ValidarCampos (String txtCodigo, String txtTamanho){
        List<String> lista = new ArrayList<>();
        lista.add(0, txtCodigo);
        lista.add(1, txtTamanho);
        for (String l : lista) {
            if (l == null || l.equals("")) {
                lista.clear();
                return false;
            }
        }
        lista.clear();
        return true;
    }

    private static void tamanhoCampo(TextField txtField, TextField txtField2, Integer tamanho){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > tamanho){
                txtField.setText(oldValue);
            }
        });
        txtField2.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > tamanho){
                txtField2.setText(oldValue);
            }
        }));
    }

    private static void posiciona(TextField txtField){
        Platform.runLater(() ->{
            if (txtField.getText().length() != 0 ){
                txtField.positionCaret(txtField.getText().length());
            }
        });
    }

    public static void valida(TextField txtField, TextField txtField2){
        tamanhoCampo(txtField,txtField2, 20);
        txtField.lengthProperty().addListener((observable, oldValue, newValue) ->
        {
            String textoDigitado = txtField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            txtField.setText(textoDigitado);
            posiciona(txtField);
        });
        txtField2.lengthProperty().addListener(((observable, oldValue, newValue) -> {
            String texto = txtField2.getText();
            texto = texto.replaceAll("[^0-9]","");
            txtField2.setText(texto);
            posiciona(txtField2);
        }));
    }
}
