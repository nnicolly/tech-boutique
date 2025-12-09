package br.com.nicolly.lojaroupasfx;

import br.com.nicolly.lojaroupasfx.view.TelaInicialView;
import br.com.nicolly.lojaroupasfx.view.TelaListaProdutosView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Loja de Roupas");
        mostrarTelaInicial();
        stage.show();
    }

    public static void mostrarTelaInicial() {
        TelaInicialView tela = new TelaInicialView();
        Scene cena = new Scene(tela.getRoot(), 400, 300);
        primaryStage.setScene(cena);
    }

    public static void mostrarTelaListaProdutos() {
        TelaListaProdutosView tela = new TelaListaProdutosView();
        Scene cena = new Scene(tela.getRoot(), 900, 600);
        primaryStage.setScene(cena);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
