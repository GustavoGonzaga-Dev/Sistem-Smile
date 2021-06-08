package funcionario;

import java.sql.*;

public class FuncionarioDAOImpl implements FuncionarioDAO{

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public void adicionar(Funcionario Fn) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String sqlADD = "INSERT INTO FUNCIONARIO" +
                    "(CODIGO, NOME, EMAIL, CONFIRMA_EMAIL, PERMISSAO, SENHA, CONFIRMA_SENHA) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtADD = con.prepareStatement(sqlADD);
            stmtADD.setLong(1, Fn.getCodigo());
            stmtADD.setString(2, Fn.getNome());
            stmtADD.setString(3, Fn.getEmail());
            stmtADD.setString(4, Fn.getConfEmail());
            stmtADD.setString(5, Fn.getPermissao());
            stmtADD.setString(6, Fn.getSenha());
            stmtADD.setString(7, Fn.getConfSenha());
            stmtADD.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Funcionario pesquisarPorCodigo(long codigo) {
        try (Connection conPQ = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlPQ = "SELECT * FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmtPQ = conPQ.prepareStatement(sqlPQ);
            stmtPQ.setLong(1, codigo);
            ResultSet rs = stmtPQ.executeQuery();
            while (rs.next()) {
                Funcionario Fn = new Funcionario();
                Fn.setCodigo(rs.getLong("codigo"));
                Fn.setNome(rs.getString("nome"));
                Fn.setEmail(rs.getString("email"));
                Fn.setConfEmail(rs.getString("confirma_email"));
                Fn.setPermissao(rs.getString("permissao"));
                Fn.setSenha(rs.getString("senha"));
                Fn.setConfSenha(rs.getString("confirma_senha"));
                return Fn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void excluir(Funcionario Fn) {
        try (Connection conEX = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlEX = "DELETE FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmtEX = conEX.prepareStatement(sqlEX);
            stmtEX.setLong(1, Fn.getCodigo());
            ResultSet rs = stmtEX.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar(Funcionario Fn) {
        try (Connection conAL = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sqlAL = "UPDATE FUNCIONARIO SET " +
                    "NOME = ?, " +
                    "EMAIL = ?, " +
                    "CONFIRMA_EMAIL = ?, " +
                    "PERMISSAO = ?, " +
                    "SENHA = ?, " +
                    "CONFIRMA_SENHA = ? where codigo = ?";
            PreparedStatement stmtAL = conAL.prepareStatement(sqlAL);
            stmtAL.setString(1, Fn.getNome());
            stmtAL.setString(2, Fn.getEmail());
            stmtAL.setString(3, Fn.getConfEmail());
            stmtAL.setString(4, Fn.getPermissao());
            stmtAL.setString(5, Fn.getSenha());
            stmtAL.setString(6, Fn.getConfSenha());
            stmtAL.setLong(7, Fn.getCodigo());
            int rs = stmtAL.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void admin() {
        try (Connection conAD = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmt = conAD.prepareStatement(sql);
            stmt.setLong(1, 0);
            ResultSet rs = stmt.executeQuery();
            if (!rs.first()) {
                Funcionario Fn = new Funcionario();
                Fn.setNome("ADMIN");
                Fn.setEmail("ADMIN@ADMIIN.COM");
                Fn.setConfEmail("ADMIN@ADMIIN.COM");
                Fn.setSenha("ADMIN1234");
                Fn.setConfSenha("ADMIN1234");
                Fn.setCodigo(0);
                Fn.setPermissao("MASTER");
                adicionar(Fn);
            }
        } catch (SQLException e) {

        }
    }
}
