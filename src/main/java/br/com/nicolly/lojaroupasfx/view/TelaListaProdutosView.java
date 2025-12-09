package br.com.nicolly.lojaroupasfx.view;

import br.com.nicolly.lojaroupasfx.HelloApplication;
import br.com.nicolly.lojaroupasfx.dao.ProdutoDAO;
import br.com.nicolly.lojaroupasfx.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import br.com.nicolly.lojaroupasfx.controller.ProdutoController;


import java.util.List;

public class TelaListaProdutosView {

    private BorderPane root;
    private TextField txtNomeFiltro;
    private TextField txtTamanhoFiltro;
    private TextField txtCorFiltro;
    private TableView<Produto> tabela;
    private ObservableList<Produto> dadosTabela;

    private final ProdutoController controller = new ProdutoController();

    public TelaListaProdutosView() {
        criarLayout();
        carregarTabela(controller.listarTodos());
    }

    private void criarLayout() {
        root = new BorderPane();

        // Título
        Label titulo = new Label("Produtos da Loja");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Filtros
        txtNomeFiltro = new TextField();
        txtNomeFiltro.setPromptText("Nome");

        txtTamanhoFiltro = new TextField();
        txtTamanhoFiltro.setPromptText("Tamanho");

        txtCorFiltro = new TextField();
        txtCorFiltro.setPromptText("Cor");

        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> onBuscar());

        HBox filtros = new HBox(10,
                new Label("Nome:"), txtNomeFiltro,
                new Label("Tamanho:"), txtTamanhoFiltro,
                new Label("Cor:"), txtCorFiltro,
                btnBuscar
        );
        filtros.setAlignment(Pos.CENTER);
        filtros.setPadding(new Insets(10));

        VBox topo = new VBox(5, titulo, filtros);
        topo.setAlignment(Pos.CENTER);
        topo.setPadding(new Insets(10));

        root.setTop(topo);

        // Tabela
        tabela = new TableView<>();

        TableColumn<Produto, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()));

        TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Produto, String> colTamanho = new TableColumn<>("Tamanho");
        colTamanho.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTamanho()));

        TableColumn<Produto, String> colCor = new TableColumn<>("Cor");
        colCor.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCor()));

        TableColumn<Produto, Number> colPreco = new TableColumn<>("Preço");
        colPreco.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().getPreco()));

        TableColumn<Produto, Number> colEstoque = new TableColumn<>("Estoque");
        colEstoque.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getEstoque()));

        tabela.getColumns().addAll(colId, colNome, colTamanho, colCor, colPreco, colEstoque);

        root.setCenter(tabela);
        BorderPane.setMargin(tabela, new Insets(10));

        // Botões
        Button btnNovo = new Button("Novo");
        btnNovo.setOnAction(e -> onNovo());

        Button btnEditar = new Button("Editar");
        btnEditar.setOnAction(e -> onEditar());

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> onExcluir());

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> HelloApplication.mostrarTelaInicial());

        HBox botoes = new HBox(10, btnNovo, btnEditar, btnExcluir, btnVoltar);
        botoes.setAlignment(Pos.CENTER);
        botoes.setPadding(new Insets(10));

        root.setBottom(botoes);
    }

    private void carregarTabela(List<Produto> lista) {
        dadosTabela = FXCollections.observableArrayList(lista);
        tabela.setItems(dadosTabela);
    }

    private void onBuscar() {
        String nome = txtNomeFiltro.getText();
        String tamanho = txtTamanhoFiltro.getText();
        String cor = txtCorFiltro.getText();

        List<Produto> lista = controller.listarPorFiltros(nome, tamanho, cor);
        carregarTabela(lista);
    }

    private Produto getSelecionado() {
        Produto p = tabela.getSelectionModel().getSelectedItem();
        if (p == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Selecione um produto na tabela.");
            alerta.showAndWait();
        }
        return p;
    }

    private void onNovo() {
        TelaCadastroProdutoView tela = new TelaCadastroProdutoView(
                HelloApplication.getPrimaryStage(),
                null,
                this
        );
        tela.showAndWait();
    }

    private void onEditar() {
        Produto selecionado = getSelecionado();
        if (selecionado != null) {
            TelaCadastroProdutoView tela = new TelaCadastroProdutoView(
                    HelloApplication.getPrimaryStage(),
                    selecionado,
                    this
            );
            tela.showAndWait();
        }
    }

    private void onExcluir() {
        Produto selecionado = getSelecionado();
        if (selecionado != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Tem certeza que deseja excluir?",
                    ButtonType.YES, ButtonType.NO);
            confirm.showAndWait().ifPresent(resposta -> {
                if (resposta == ButtonType.YES) {
                    controller.deletar(selecionado.getId());
                    atualizarLista();
                }
            });
        }
    }

    public void atualizarLista() {
        carregarTabela(controller.listarTodos());
    }

    public Parent getRoot() {
        return root;
    }
}
