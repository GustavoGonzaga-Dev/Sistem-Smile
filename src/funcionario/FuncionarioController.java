package funcionario;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDAO fnd = new FuncionarioDAOImpl();


    public void adicionar(Funcionario Fn) {
        fnd.adicionar(Fn);
    }

    public Funcionario pesquisarPorCodigo(long codigo) {

        return fnd.pesquisarPorCodigo(codigo);
    }

    public void excluir(Funcionario Fn) {
        fnd.excluir(Fn);
    }

    public void alterar(Funcionario Fn) {
        fnd.alterar(Fn);
    }

    public boolean validarCampos(String txtCodigo, String txtNome, String txtEmail, String txtConfEmail, String cbPermissao, String txtSenha, String txtConfSenha) {
        List<String> lista = new ArrayList<>();
        lista.add(0, txtCodigo);
        lista.add(1, txtNome);
        lista.add(2, txtEmail);
        lista.add(3, txtConfEmail);
        lista.add(4, cbPermissao);
        lista.add(5, txtSenha);
        lista.add(6, txtConfSenha);
        for (String l : lista) {
            if (l == null || l.equals("")) {
                lista.clear();
                return false;
            }
        }
        lista.clear();
        return validarEmailSenha(txtEmail, txtConfEmail, txtSenha, txtConfSenha);
    }

    public boolean validarEmailSenha(String txtEmail, String txtConfEmail, String txtSenha, String txtConfSenha) {
        return txtSenha.equals(txtConfSenha) && txtEmail.equals(txtConfEmail);
    }

    public void admin() {
        fnd.admin();
    }

    private static void tamanhoCampo(TextField txtField, Integer tamanho){
        txtField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > tamanho){
                txtField.setText(oldValue);
            }
        });
    }

    private static void posiciona(TextField txtField){
        Platform.runLater(() ->{
            if (txtField.getText().length() != 0){
                txtField.positionCaret(txtField.getText().length());
            }
        });
    }

    public static void valida(TextField txtField){
        tamanhoCampo(txtField, 20);
        txtField.lengthProperty().addListener((observable, oldValue, newValue) ->
        {
            String textoDigitado = txtField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            txtField.setText(textoDigitado);
            posiciona(txtField);
        });
    }
}
