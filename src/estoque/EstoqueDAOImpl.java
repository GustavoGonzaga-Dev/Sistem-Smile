package estoque;

import funcionario.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAOImpl implements EstoqueDAO{
    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public List<Estoque> colocarValores(){
        List<Estoque> lista = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT CODIGO_PRODUTO,NOME_PRODUTO,QUANTIDADE,PRECO FROM PRODUTO";
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setString(1, "%" + nomeProduto  + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estoque est = new Estoque();
                est.setCodProduto(rs.getInt("CODIGO_PRODUTO"));
                est.setNomeProduto(rs.getString("NOME_PRODUTO"));
                est.setQuantidade(rs.getInt("QUANTIDADE"));
                est.setValor(rs.getDouble("PRECO"));

                lista.add(est);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
