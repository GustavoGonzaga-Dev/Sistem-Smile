package vendas;

import java.sql.*;

public class VendasController {
    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public Vendas pesquisaCodigo(long codigo){
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM vendas WHERE codigo LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, codigo);
            ResultSet res = stmtPQ.executeQuery();
            while (res.next()) {
                Vendas venda = new Vendas ();
                venda.setCodigoVenda(res.getLong("Código"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
