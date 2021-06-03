package marca;

import funcionario.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaController {

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Marca Mc) {
        try (Connection conMC= DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO MARCA" +
                    "(CODIGO_MARCA, NOME_MARCA) VALUES" +
                    "(?, ?)";
            PreparedStatement stmtADD = conMC.prepareStatement(sqlADD);
            stmtADD.setInt(1, Mc.getCodigo());
            stmtADD.setString(2, Mc.getNomeMarca());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Marca pesquisarPorCodigo(int txtPesquisar) {
        try (Connection conPQM = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQM = "SELECT * FROM marca WHERE CODIGO_MARCA LIKE ?";
            PreparedStatement stmtPQM = conPQM.prepareStatement(sqlPQM);
            stmtPQM.setInt(1, txtPesquisar);
            ResultSet rsM = stmtPQM.executeQuery();
            while (rsM.next()) {
                Marca Mc = new Marca();
                Mc.setCodigo(rsM.getInt("CODIGO_MARCA"));
                Mc.setNomeMarca(rsM.getString("NOME_MARCA"));
                return Mc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Marca Mc) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM marca WHERE CODIGO_MARCA LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setInt(1, Mc.getCodigo());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Marca Mc) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE marca SET " +
                    "NOME_MARCA = ? where CODIGO_MARCA = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Mc.getNomeMarca());
            stmtAL.setInt(2, Mc.getCodigo());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean ValidarCampos (String txtCodigo, String txtNomeMarca){
        List<String> lista = new ArrayList<>();
        lista.add(0, txtCodigo);
        lista.add(1, txtNomeMarca);
        for (String l : lista) {
            if (l == null || l.equals("")) {
                lista.clear();
                return false;
            }
        }
        lista.clear();
        return true;
    }
}
