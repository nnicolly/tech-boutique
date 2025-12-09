package br.com.nicolly.lojaroupasfx.view;

import br.com.nicolly.lojaroupasfx.model.Produto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import br.com.nicolly.lojaroupasfx.controller.ProdutoController;


public class TelaCadastroProdutoView extends Stage {

    private TextField txtNome;
    private TextField txtTamanho;
    private TextField txtCor;
    private TextField txtPreco;
    private TextField txtEstoque;

    private Produto produtoEdicao;
    private final ProdutoController controller = new ProdutoController();
    private final TelaListaProdutosView telaLista;

    public TelaCadastroProdutoView(Window dono, Produto produto, TelaListaProdutosView telaLista) {
        this.produtoEdicao = produto;
        this.telaLista = telaLista;

        initOwner(dono);
        initModality(Modality.WINDOW_MODAL);

        setTitle(produto == null ? "Novo Produto" : "Editar Produto");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(15));

        Label lblNome = new Label("Nome:");
        txtNome = new TextField();

        Label lblTamanho = new Label("Tamanho:");
        txtTamanho = new TextField();

        Label lblCor = new Label("Cor:");
        txtCor = new TextField();

        Label lblPreco = new Label("Preço:");
        txtPreco = new TextField();

        Label lblEstoque = new Label("Estoque:");
        txtEstoque = new TextField();

        grid.add(lblNome, 0, 0);
        grid.add(txtNome, 1, 0);

        grid.add(lblTamanho, 0, 1);
        grid.add(txtTamanho, 1, 1);

        grid.add(lblCor, 0, 2);
        grid.add(txtCor, 1, 2);

        grid.add(lblPreco, 0, 3);
        grid.add(txtPreco, 1, 3);

        grid.add(lblEstoque, 0, 4);
        grid.add(txtEstoque, 1, 4);

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> onSalvar());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> close());

        HBox botoes = new HBox(10, btnSalvar, btnCancelar);
        botoes.setAlignment(Pos.CENTER);
        grid.add(botoes, 0, 5, 2, 1);

        if (produtoEdicao != null) {
            carregarDados();
        }

        Scene cena = new Scene(grid, 350, 260);
        setScene(cena);
        setResizable(false);
    }

    private void carregarDados() {
        txtNome.setText(produtoEdicao.getNome());
        txtTamanho.setText(produtoEdicao.getTamanho());
        txtCor.setText(produtoEdicao.getCor());
        txtPreco.setText(String.valueOf(produtoEdicao.getPreco()));
        txtEstoque.setText(String.valueOf(produtoEdicao.getEstoque()));
    }

    private void onSalvar() {
        try {
            String nome = txtNome.getText();
            String tamanho = txtTamanho.getText();
            String cor = txtCor.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            int estoque = Integer.parseInt(txtEstoque.getText());

            if (nome.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Nome não pode ficar vazio.", ButtonType.OK).showAndWait();
                return;
            }

            if (produtoEdicao == null) {
                Produto novo = new Produto(nome, tamanho, cor, preco, estoque);
                controller.salvar(novo);
            } else {
                produtoEdicao.setNome(nome);
                produtoEdicao.setTamanho(tamanho);
                produtoEdicao.setCor(cor);
                produtoEdicao.setPreco(preco);
                produtoEdicao.setEstoque(estoque);
                controller.atualizar(produtoEdicao);
            }

            new Alert(Alert.AlertType.INFORMATION, "Salvo com sucesso!", ButtonType.OK).showAndWait();
            telaLista.atualizarLista();
            close();

        } catch (NumberFormatException ex) {
            new Alert(
                    Alert.AlertType.ERROR,
                    "Preço e estoque precisam ser números válidos.",
                    ButtonType.OK
            ).showAndWait();
        }
    }
}
