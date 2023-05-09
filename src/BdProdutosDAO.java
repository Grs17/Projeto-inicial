import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BdProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "");
            System.out.println("Conectado com sucesso");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }

    public Connection conn() {
        return conn;
    }

    public boolean cadastrarProduto(ProdutosDTO produto) throws SQLException {
        BdProdutosDAO bd = new BdProdutosDAO();
        bd.conectar();

        String sqlQuery = "INSERT INTO produtos (nome, valor, status)"
                + " VALUES (?,?,?)";

        try ( PreparedStatement produtos = bd.conn().prepareStatement(sqlQuery)) {
            produtos.setString(1, produto.getNome());
            produtos.setInt(2, produto.getValor());
            produtos.setString(3, produto.getStatus());
            produtos.execute();
            System.out.println(sqlQuery);
        }
        bd.desconectar();
        return true;
    }

    public List<ProdutosDTO> listarProdutos() {

  

        return null;

  

    }

}

