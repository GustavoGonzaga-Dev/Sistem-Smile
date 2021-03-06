package produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {
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

    public void excluir(int cod) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM PRODUTO WHERE CODIGO_PRODUTO LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setLong(1, cod);
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Produto pd) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE PRODUTO SET " +
                    "NOME_PRODUTO = ?, " +
                    "COR = ?, " +
                    "CODIGO_MARCA = ?, " +
                    "CODIGO_CATEGORIA = ?, " +
                    "CODIGO_TAMANHO = ?, " +
                    "PRECO = ?," +
                    "QUANTIDADE = ?," +
                    "DESCRICAO = ? where CODIGO_PRODUTO = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, pd.getNomeProduto());
            stmtAL.setString(2, pd.getCor());
            stmtAL.setInt(3, pd.getCodMarca());
            stmtAL.setInt(4, pd.getCodCategoria());
            stmtAL.setInt(5, pd.getCodTamanho());
            stmtAL.setDouble(6, pd.getPreco());
            stmtAL.setInt(7, pd.getQuantidade());
            stmtAL.setString(8, pd.getDescricao());
            stmtAL.setInt(9, pd.getCodProduto());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto pesquisarPorCodigo(int cod) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM PRODUTO WHERE CODIGO_PRODUTO LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, cod);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Produto Pd = new Produto();
                Pd.setCodProduto(rs.getInt("CODIGO_PRODUTO"));
                Pd.setCodTamanho(rs.getInt("CODIGO_TAMANHO"));
                Pd.setCodCategoria(rs.getInt("CODIGO_CATEGORIA"));
                Pd.setCodMarca(rs.getInt("CODIGO_MARCA"));
                Pd.setPreco((rs.getDouble("PRECO")));
                Pd.setNomeProduto(rs.getString("NOME_PRODUTO"));
                Pd.setCor(rs.getString("COR"));
                Pd.setDescricao(rs.getString("Descricao"));
                Pd.setQuantidade(rs.getInt("QUANTIDADE"));
                return Pd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
