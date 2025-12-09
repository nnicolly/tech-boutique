module br.com.nicolly.lojaroupasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens br.com.nicolly.lojaroupasfx to javafx.fxml;

    exports br.com.nicolly.lojaroupasfx;
    exports br.com.nicolly.lojaroupasfx.model;
    exports br.com.nicolly.lojaroupasfx.dao;
    exports br.com.nicolly.lojaroupasfx.util;
    exports br.com.nicolly.lojaroupasfx.controller;
}
