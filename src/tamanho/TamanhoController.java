package tamanho;

import marca.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TamanhoController {

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Tamanho Tm) {
        try (Connection conMC= DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO TAMANHO" +
                    "(CODIGO_TAMANHO, NOME_TAMANHO) VALUES" +
                    "(?, ?)";
            PreparedStatement stmtADD = conMC.prepareStatement(sqlADD);
            stmtADD.setInt(1, Tm.getCodigo());
            stmtADD.setString(2, Tm.getTamanho());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Tamanho pesquisarPorCodigo(int txtPesquisar) {
        try (Connection conPQT = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQT = "SELECT * FROM TAMANHO WHERE CODIGO_TAMANHO LIKE ?";
            PreparedStatement stmtPQT = conPQT.prepareStatement(sqlPQT);
            stmtPQT.setInt(1, txtPesquisar);
            ResultSet rsT = stmtPQT.executeQuery();
            while (rsT.next()) {
                Tamanho Tm = new Tamanho();
                Tm.setCodigo(rsT.getInt("CODIGO_TAMANHO"));
                Tm.setTamanho(rsT.getString("NOME_TAMANHO"));
                return Tm;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Tamanho Tm) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM TAMANHO WHERE CODIGO_TAMANHO LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setInt(1, Tm.getCodigo());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Tamanho Tm) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE TAMANHO SET " +
                    "NOME_TAMANHO = ? where CODIGO_TAMANHO = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Tm.getTamanho());
            stmtAL.setInt(2, Tm.getCodigo());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
}
