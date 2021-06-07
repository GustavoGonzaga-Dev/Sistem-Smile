package vendas;

import java.sql.*;

public class VendasController {
    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";


    public Vendas pesquisaCodigo(int codigo){
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM VENDAS WHERE CODIGO_VENDA LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, codigo);
            ResultSet res = stmtPQ.executeQuery();
            while (res.next()) {
                Vendas vd = new Vendas ();
                vd.setCodigoVenda(res.getInt("Código"));

                return vd;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
