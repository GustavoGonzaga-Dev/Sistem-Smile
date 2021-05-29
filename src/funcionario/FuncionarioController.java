package funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public static final String URL = "jdbc:mariadb://localhost:3306/LOJA";
    public static final String USER = "root";
    public static final String PASSWORD = "";

    public Connection con;

    public Connection conectarbanco(){
        try{
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado no banco de dados");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void adicionar (Funcionario Fn){
        funcionarios.add(Fn);
        try{
            String sql = "INSERT INTO FUNCIONARIO" +
                    "(CODIGO, NOME, EMAIL, CONFIRMA_EMAIL, PERMISSAO, SENHA, CONFIRMA_SENHA) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, Fn.getCodigo());
            stmt.setString(2, Fn.getNome());
            stmt.setString(3, Fn.getEmail());
            stmt.setString(4, Fn.getConfEmail());
            stmt.setString(5, Fn.getPermissao());
            stmt.setString(6, Fn.getSenha());
            stmt.setString(7, Fn.getConfSenha());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Funcionario pesquisarPorCodigo(long codigo){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,  codigo);
            ResultSet rs = stmt.executeQuery();
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
        return  null;
    }

    public void excluir(Funcionario Fn){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "DELETE FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1,  Fn.getCodigo());
            ResultSet rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterar (Funcionario Fn){
        for (Funcionario Fn2 : funcionarios){
            if(Fn2.getCodigo() == Fn.getCodigo()){
                funcionarios.remove(Fn2);
                funcionarios.add(Fn);
            }
        }
    }

    public boolean validarCampos(String txtCodigo, String txtNome, String txtEmail, String txtConfEmail, String cbPermissao, String txtSenha, String txtConfSenha){
        List<String> lista = new ArrayList<>();
        lista.add(0, txtCodigo);
        lista.add(1, txtNome);
        lista.add(2, txtEmail);
        lista.add(3, txtConfEmail);
        lista.add(4, cbPermissao);
        lista.add(5, txtSenha);
        lista.add(6, txtConfSenha);

        for (String l : lista) {
            if (l == null || l.equals("")){
                lista.clear();
                return false;
            }
        }
        lista.clear();
        return validarEmailSenha(txtEmail, txtConfEmail, txtSenha, txtConfSenha);
    }

    public boolean validarEmailSenha(String txtEmail, String txtConfEmail, String txtSenha, String txtConfSenha){
        return txtSenha.equals(txtConfSenha) && txtEmail.equals(txtConfEmail);
    }

    public void admin() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM funcionario WHERE codigo LIKE ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, 0 );
            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.first());
            if(rs.first() == false){
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
            e.printStackTrace();
        }
    }
}
