package br.com.nicolly.lojaroupasfx.view;

import br.com.nicolly.lojaroupasfx.HelloApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class TelaInicialView {

    private BorderPane root;

    public TelaInicialView() {
        root = new BorderPane();

        Image logo = new Image(
                getClass().getResourceAsStream("/logotipo.png")
        );
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(250);
        logoView.setPreserveRatio(true);

        Label titulo = new Label("Tech Boutique");
        titulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label dev = new Label("Desenvolvido por Nicolly Araujo");


        VBox centro = new VBox(10, logoView, titulo, dev);
        centro.setAlignment(Pos.CENTER);
        centro.setPadding(new Insets(20));

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setOnAction(e -> HelloApplication.mostrarTelaListaProdutos());

        root.setCenter(centro);
        root.setBottom(btnEntrar);
        BorderPane.setAlignment(btnEntrar, Pos.CENTER);
        BorderPane.setMargin(btnEntrar, new Insets(10));
    }

    public Parent getRoot() {
        return root;
    }
}
