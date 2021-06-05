package produto;

import funcionario.Funcionario;

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

    public int buscarPorNomeMarca(String cbMarcaValue){
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT CODIGO_MARCA FROM MARCA WHERE NOME_MARCA LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, cbMarcaValue);
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            return Integer.parseInt(rsLg.getString("CODIGO_MARCA"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int buscarPorNomeCategoria(String cbCategoriaValue){
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT CODIGO_CATEGORIA FROM CATEGORIA WHERE NOME_CATEGORIA LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, cbCategoriaValue);
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            return Integer.parseInt(rsLg.getString("CODIGO_CATEGORIA"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int buscarPorNomeTamanho(String cbTamanhoValue){
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT CODIGO_TAMANHO FROM TAMANHO WHERE NOME_TAMANHO LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, cbTamanhoValue);
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            return Integer.parseInt(rsLg.getString("CODIGO_TAMANHO"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void adicionar(Produto Pd) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO PRODUTO" +
                    "(CODIGO_PRODUTO, NOME_PRODUTO, COR, CODIGO_MARCA, CODIGO_CATEGORIA, CODIGO_TAMANHO," +
                    " PRECO, QUANTIDADE, DESCRICAO) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtADD = con.prepareStatement(sqlADD);
            stmtADD.setInt(1, Pd.getCodProduto());
            stmtADD.setString(2, Pd.getNomeProduto());
            stmtADD.setString(3, Pd.getCor());
            stmtADD.setInt(4, Pd.getCodMarca());
            stmtADD.setInt(5, Pd.getCodCategoria());
            stmtADD.setInt(6, Pd.getCodTamanho());
            stmtADD.setDouble(7, Pd.getPreco());
            stmtADD.setInt(8, Pd.getQuantidade());
            stmtADD.setString(9, Pd.getDescricao());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
