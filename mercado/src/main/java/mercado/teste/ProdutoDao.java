package mercado.teste;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDao {
        public void inserir (Produto produto) throws SQLException {
            String sql = "INSERT INTO produto (id, nome, quantidade, preco) VALUES (?, ?, ?, ?)";

            try(Connection conn =
                    Conexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, produto.getId());
                ps.setString(2, produto.getNome());
                ps.setInt(3, produto.getQuantidade());
                ps.setDouble(4, produto.getPreco());

                ps.executeUpdate();
                System.out.println("produto inserido com sucesso");
            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }

        public void atualizar (Produto produto) throws SQLException {
            String sql = "UPDATE produto SET nome=?, quantidade=?, preco=? WHERE id=?";
            try(Connection conn =
                    Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, produto.getNome());
                ps.setInt(2, produto.getQuantidade());
                ps.setDouble(3, produto.getPreco());

                int linhasAfetadas = ps.executeUpdate();

                if(linhasAfetadas > 0){
                    System.out.println("Produto atualizado com sucesso");
                }else{
                    System.out.println("Erro ao atualizar produto");
                }
            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }

        public void excluir (int produto) throws SQLException {
            String sql = "DELETE FROM produto WHERE id=?";
            try(Connection conn =
                    Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql)
            ){
                ps.setInt(1, produto);

                int linhasAfetadas = ps.executeUpdate();

                if(linhasAfetadas > 0){
                    System.out.println("Produto excluido com sucesso");
                }else{
                    System.out.println("Erro ao excluir produto");
                }
            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
        }

        public Object buscarPorID(int id)throws SQLException{
            String sql = "SELECT * FROM produto WHERE id=?";

            try(Connection conn =
                    Conexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(sql);
            ){
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    int idProduto = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int quantidade = rs.getInt("quantidade");
                    double preco = rs.getDouble("preco");

                    return new Produto(idProduto, nome, quantidade, preco);
                }
            }catch (SQLException e){
                throw new SQLException(e.getMessage());
            }
            return null;
            }

            public void deletar(int id) throws SQLException {
            String sql = "DELETE FROM produto WHERE id=?";
            try (Connection conn = Conexao.getConexao();

            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, id);

                int linhasAfetadas = ps.executeUpdate();
                if(linhasAfetadas > 0){
                    System.out.println("Produto excluido com sucesso");
                }else{
                    System.out.println("Erro ao excluir produto");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            }

            public List<Produto> buscarTodos()throws SQLException{
            String sql = "SELECT * FROM produto";
            List<Produto> produtos = new ArrayList<>();

            try(Connection conn = Conexao.getConexao();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    produtos.add(new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("quantidade"),
                            rs.getDouble("preco")
                    ));
                }
                return produtos;
            }



            }
            
}
