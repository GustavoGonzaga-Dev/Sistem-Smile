package login;

import java.sql.*;

public class LoginController {

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public static boolean validarLogin(Login Lg) {
        try (Connection conLg = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println(Lg.getEmail());
            System.out.println(Lg.getSenha());
            String sqlLg = "SELECT * FROM funcionario WHERE email LIKE ?";
            PreparedStatement stmtLg = conLg.prepareStatement(sqlLg);
            stmtLg.setString(1, Lg.getEmail());
            ResultSet rsLg = stmtLg.executeQuery();
            rsLg.next();
            if (rsLg.first()) {
                if (rsLg.getString("senha").equals(Lg.getSenha())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
