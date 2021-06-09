package categoria;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    CategoriaDAO ctd = new CategoriaDAOImpl();

    public void adicionar(Categoria Ct) {
        ctd.adicionar(Ct);
    }

    public Categoria pesquisarPorCodigo(int txtPesquisar) {
        return ctd.pesquisarPorCodigo(txtPesquisar);
    }

    public void excluir(Categoria Ct) {
        ctd.excluir(Ct);
    }

    public void alterar(Categoria Ct) {
        ctd.alterar(Ct);
    }

    public boolean ValidarCampos (String txtCodigo, String txtCategoria){
        List<String> lista = new ArrayList<>();
        lista.add(0, txtCodigo);
        lista.add(1, txtCategoria);
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
            if (txtField.getText().length() != 0){
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
