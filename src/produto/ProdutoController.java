package produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoController {

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public List<String> carregarMarcas() {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT NOME_MARCA FROM MARCA";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            ResultSet rsLg = stmtLg.executeQuery();
            int i = 0;
            List<String> lista = new ArrayList<>();
            while (rsLg.next()) {
                lista.add(i, rsLg.getString(1));
                i++;
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> carregarTamanhos() {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT NOME_TAMANHO FROM TAMANHO";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            ResultSet rsLg = stmtLg.executeQuery();
            int i = 0;
            List<String> lista = new ArrayList<>();
            while (rsLg.next()) {
                lista.add(i, rsLg.getString(1));
                i++;
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> carregarCategoria() {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT NOME_CATEGORIA FROM CATEGORIA";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            ResultSet rsLg = stmtLg.executeQuery();
            int i = 0;
            List<String> lista = new ArrayList<>();
            while (rsLg.next()) {
                lista.add(i, rsLg.getString(1));
                i++;
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
