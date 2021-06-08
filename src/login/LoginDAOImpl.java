package login;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class LoginDAOImpl implements LoginDAO{

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public List<String> validarLogin(Login Lg) {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlLg = "SELECT * FROM funcionario WHERE email LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, Lg.getEmail());
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            if (rsLg.first()) {
                if (rsLg.getString("senha").equals(Lg.getSenha())) {
                    List<String> LS = Arrays.asList(
                            Lg.getEmail(),
                            Lg.getSenha(),
                            rsLg.getString("PERMISSAO")
                    );
                    return LS;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
