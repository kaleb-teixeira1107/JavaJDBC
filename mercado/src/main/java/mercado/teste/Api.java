package mercado.teste;

import static spark.Spark.*;
import com.google.gson.Gson;

public class Api {
    public static void main(String[] args) {
        ProdutoDao produtoDao = new ProdutoDao();
        Gson gson = new Gson();

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.header("Access-Control-Allow-Headers", "Content-Type");
        });

        options("/*", (request, response) -> {
            response.status(200);
            return "Ok";
        });

        get("/produtos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(produtoDao.buscarTodos());
        });

        get("/produtos/:id", (request, response) -> {
            response.type("application/json");
            int id = Integer.parseInt(request.params(":id"));
            Produto p = (Produto) produtoDao.buscarPorID(id);
            return p != null ? gson.toJson(p) : "{Error produto não encontrado}";
        });

        post("/produtos", (request, response) -> {
            Produto p =  gson.fromJson(request.body(), Produto.class);
            produtoDao.inserir(p);
            return "{produtos cadastrado}";
        });

        put("/produtos", (request, response) -> {
            Produto p =  gson.fromJson(request.body(), Produto.class);
            produtoDao.atualizar(p);
            return "{produto atualizado}";
        });

        delete("/produtos/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params("id"));
            produtoDao.deletar(id);
            return "{\"ok\":true}";
        });
    }

}
