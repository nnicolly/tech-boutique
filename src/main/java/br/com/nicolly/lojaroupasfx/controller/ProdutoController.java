package br.com.nicolly.lojaroupasfx.controller;

import br.com.nicolly.lojaroupasfx.dao.ProdutoDAO;
import br.com.nicolly.lojaroupasfx.model.Produto;

import java.util.List;

public class ProdutoController {

    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public void salvar(Produto produto) {
        // aqui poderiam entrar validações de regra de negócio
        produtoDAO.salvar(produto);
    }

    public void atualizar(Produto produto) {
        produtoDAO.atualizar(produto);
    }

    public void deletar(int id) {
        produtoDAO.deletar(id);
    }

    public List<Produto> listarTodos() {
        return produtoDAO.listarTodos();
    }

    public List<Produto> listarPorFiltros(String nome, String tamanho, String cor) {
        return produtoDAO.listarPorFiltros(nome, tamanho, cor);
    }
}
