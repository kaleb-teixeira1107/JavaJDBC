package mercado.teste;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ProdutoDao dao = new ProdutoDao();
        Produto p1 = new Produto(4, "arroz", 55, 55.5);
        dao.inserir(p1);
        Produto p2 = new Produto(5, "arroz", 55, 55.5);
        dao.inserir(p2);

        System.out.println("Digite o ID do produto: ");
        int id = sc.nextInt();
        Produto produto = (Produto) dao.buscarPorID(id);

        if (dao.buscarPorID(id) != null) {
            System.out.println("Produto encontrado");
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preco: " + produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade());
        }else {
            System.out.println("Produto não registrado");
        }
        sc.close();
    }
}

