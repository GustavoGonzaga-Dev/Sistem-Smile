package marca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
