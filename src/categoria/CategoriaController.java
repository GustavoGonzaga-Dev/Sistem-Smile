package categoria;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Categoria Ct) {
        try (Connection conMC= DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO CATEGORIA" +
                    "(CODIGO_CATEGORIA, NOME_CATEGORIA) VALUES" +
                    "(?, ?)";
            PreparedStatement stmtADD = conMC.prepareStatement(sqlADD);
            stmtADD.setInt(1, Ct.getCodigo());
            stmtADD.setString(2, Ct.getCategoria());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Categoria pesquisarPorCodigo(int txtPesquisar) {
        try (Connection conPQT = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQT = "SELECT * FROM CATEGORIA WHERE CODIGO_CATEGORIA LIKE ?";
            PreparedStatement stmtPQT = conPQT.prepareStatement(sqlPQT);
            stmtPQT.setInt(1, txtPesquisar);
            ResultSet rsT = stmtPQT.executeQuery();
            while (rsT.next()) {
                Categoria Ct = new Categoria();
                Ct.setCodigo(rsT.getInt("CODIGO_CATEGORIA"));
                Ct.setCategoria(rsT.getString("NOME_CATEGORIA"));
                return Ct;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Categoria Ct) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM CATEGORIA WHERE CODIGO_CATEGORIA LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setInt(1, Ct.getCodigo());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Categoria Ct) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE CATEGORIA SET " +
                    "NOME_CATEGORIA = ? where CODIGO_CATEGORIA = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Ct.getCategoria());
            stmtAL.setInt(2, Ct.getCodigo());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
